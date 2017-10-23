package com.hlib.http.request;

import android.content.Context;

import com.google.gson.Gson;
import com.hlib.app.HActivityAble;
import com.hlib.util.MLogUtil;
import com.hlib.util.MStringUtil;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.MySSLSocketFactory;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.impl.client.BasicCookieStore;


/**
 * http请求。使用android-async-http请求http。
 * Created by moguangjian on 15/10/10 11:13.
 */
public class MHttpAsync implements MHttpAble {

    private static final String TAG = MHttpAsync.class.getSimpleName();
    private AsyncHttpClient asyncHttpClient;
    protected Context context;
    protected boolean isHandleParentServerResponse = true;

    private List<Boolean> listProgressStatus;

    public synchronized AsyncHttpClient getHttpInstance() {
        if (asyncHttpClient == null) {
            asyncHttpClient = new AsyncHttpClient();
            asyncHttpClient.setTimeout(60 * 1000);
            asyncHttpClient.setSSLSocketFactory(MySSLSocketFactory.getFixedSocketFactory());
            asyncHttpClient.setCookieStore(new BasicCookieStore());//new PersistentCookieStore(context);
            asyncHttpClient.addHeader("Accept", "application/json;");
            asyncHttpClient.addHeader("Connection", "keep-alive");
        }

        return asyncHttpClient;
    }

    public static synchronized void destroy() {
       /* if (asyncHttpClient != null) {
            asyncHttpClient.cancelAllRequests(true);
            asyncHttpClient = null;
        }*/
    }

    public MHttpAsync(Context context) {
        this.context = context;
        init();
    }

    private void init() {
        listProgressStatus = new ArrayList<>();
    }

    @Override
    public Object getInstance() {
        return getHttpInstance();
    }

    @Override
    public void addHeader(String header, String value) {
        if (header != null && value != value) {
            getHttpInstance().addHeader(header, value);
        }
    }

    @Override
    public void removeHeader(String header) {
        getHttpInstance().removeHeader(header);
    }

    @Override
    public void removeAllHeaders() {
        getHttpInstance().removeAllHeaders();
    }

    @Override
    public void get(String url, Map<String, Object> params, Class<? extends MModel> mModel, MHttpResponseAble mHttpResponseAble) {
        requestGet(url, params, mModel, mHttpResponseAble);
    }

    protected void requestGet(String url, Map<String, Object> params, Class<? extends MModel> mModel, MHttpResponseAble mHttpResponseAble) {
        addProgressStatus(params);
        getHttpInstance().get(url, mapToRequestParams(params), new MTextHttpResponseHandler(mModel, mHttpResponseAble, params));
    }

    @Override
    public void post(String url, Map<String, Object> params, Class<? extends MModel> mModel, MHttpResponseAble mHttpResponseAble) {
        requestPost(url, params, mModel, mHttpResponseAble);
    }

    protected void requestPost(String url, Map<String, Object> params, Class<? extends MModel> mModel, MHttpResponseAble mHttpResponseAble) {
        addProgressStatus(params);
        getHttpInstance().post(url, mapToRequestParams(params), new MTextHttpResponseHandler(mModel, mHttpResponseAble, params));
    }

    @Override
    public void put(String url, Map<String, Object> params, Class<? extends MModel> mModel, MHttpResponseAble mHttpResponseAble) {
        addProgressStatus(params);
        getHttpInstance().put(url, mapToRequestParams(params), new MTextHttpResponseHandler(mModel, mHttpResponseAble, params));
    }

    @Override
    public void delete(String url, Map<String, Object> params, Class<? extends MModel> mModel, MHttpResponseAble mHttpResponseAble) {
        addProgressStatus(params);
        getHttpInstance().delete(url, mapToRequestParams(params), new MTextHttpResponseHandler(mModel, mHttpResponseAble, params));
    }

    private void addProgressStatus(Map<String, Object> params) {
        Object object = params.get(IS_SHOW_PROGRESS);
        boolean isShowProgress = true;
        if (!MStringUtil.isObjectNull(object)) {
            isShowProgress = (boolean) params.get(IS_SHOW_PROGRESS);
        }

        if (isShowProgress) {
            listProgressStatus.add(true);
        }
    }

    private class MTextHttpResponseHandler extends TextHttpResponseHandler {

        private MHttpResponseAble mHttpResponseAble;
        private Class<? extends MModel> mModel;
        private RequestParams params;

        public MTextHttpResponseHandler(Class<? extends MModel> mModel, MHttpResponseAble mHttpResponseAble, Map<String, Object> params) {
            this.mModel = mModel;
            this.mHttpResponseAble = mHttpResponseAble;
            this.params = mapToRequestParams(params);
        }

        @Override
        public void onStart() {
            showProgressView();
            mHttpResponseAble.onPrepare();
            super.onStart();
        }

        @Override
        public void onFinish() {
            super.onFinish();
            if (!listProgressStatus.isEmpty()) {
                listProgressStatus.remove(0);
            }
            mHttpResponseAble.onFinish();
            hideProgressView();
        }

        @Override
        public void onProgress(long bytesWritten, long totalSize) {
            super.onProgress(bytesWritten, totalSize);
            mHttpResponseAble.onProgress(bytesWritten, totalSize);
        }

        @Override
        public void onFailure(int statusCode, Header[] headers, String response, Throwable throwable) {
            mHttpResponseAble.onFailure(context, statusCode, "网络不给力");

            MLogUtil.i(TAG, "url : " + getRequestURI() + "?" + params.toString());
            MLogUtil.e(TAG, "MTextHttpResponseHandler on failure:");
            MLogUtil.e(TAG, "message:" + response);
            MLogUtil.e(TAG, "statusCode:" + statusCode + " message:" + response + " throwable:" + throwable.getMessage());
        }

        @Override
        public void onSuccess(int statusCode, Header[] headers, String response) {
            MLogUtil.i(TAG, "url : " + getRequestURI() + "?" + params.toString());
            MLogUtil.i(TAG, "MTextHttpResponseHandler on onSuccess:");
            MLogUtil.i(TAG, ((mModel == null) ? "mModel" : mModel.getSimpleName()) + " message:");

            try {
                Gson gson = new Gson();
//                JsonElement jsonElement = new JsonParser().parse(response);
                MLogUtil.w(TAG, "response:" + response);

                if (isHandleParentServerResponse) {
                    MStatusModel mStatusModel = gson.fromJson(response, MStatusModel.class);
                    dealSlowly(statusCode, mStatusModel, gson, response);
//                dealFight(statusCode, mStatusModel, gson, response);
//                dealMacaoCar(statusCode, mStatusModel, gson, response);
//                dealWuyouShop(statusCode, mStatusModel, gson, response);

                } else {
                    onServerResponse(statusCode, this.mModel, gson, response, mHttpResponseAble);
                }


            } catch (Exception e) {
                mHttpResponseAble.onFailure(context, statusCode, "解析数据异常");
//                MToastUtil.show(context, "解析数据异常");

                MLogUtil.e(TAG, "MTextHttpResponseHandler onSuccess is exception:" + response);
                e.printStackTrace();
            }
        }

        private void dealSlowly(int statusCode, MStatusModel mStatusModel, Gson gson, String response) {
            if (statusCode == 200) {
                MModel mModel = gson.fromJson(response, this.mModel);
                mHttpResponseAble.onSuccess(statusCode, mModel);

            } else {
//                String msg = mStatusModel.getMsg();
//                if (MStringUtil.isEmpty(msg)) {
//                    msg = "网络不给力";
//                }
//                MToastUtil.show(context, msg);

                mHttpResponseAble.onFailure(context, statusCode, "网络不给力");
            }
        }
    }

    /**
     * map转RequestParams
     *
     * @param map
     * @return
     */
    public static RequestParams mapToRequestParams(Map<String, Object> map) {
        RequestParams params = new RequestParams();

        try {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                String key = entry.getKey();
                if (key == null) {
                    key = "";
                    continue;
                }

                Object object = entry.getValue();
                if (object == null) {
                    object = "";
                }

                if (object instanceof File) {
//                    params.put(key, (File) object);
                    params.put(key, (File) object, "image/jpeg", key);

                } else if (object instanceof File[]) {
                    params.put(key, (File[]) object);

                } else {
                    params.put(key, object);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return params;
    }

    private void showProgressView() {
        if (!MStringUtil.isObjectNull(context) && !listProgressStatus.isEmpty()) {
            if (context instanceof HActivityAble) {
                ((HActivityAble) context).showProgress();
            }
        }
    }

    private void hideProgressView() {
        if (!MStringUtil.isObjectNull(context) && listProgressStatus.isEmpty()) {
            if (context instanceof HActivityAble) {
                ((HActivityAble) context).hideProgress();
            }
        }
    }

    protected void onServerResponse(int statusCode, Class<? extends MModel> mModel, Gson gson, String response, MHttpResponseAble mHttpResponseAble) {
    }
}
