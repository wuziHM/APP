package com.hlib.http.request;

import android.content.Context;

import com.hlib.BuildConfig;
import com.hlib.util.MLogUtil;
import com.hlib.util.MStringUtil;

import java.util.Map;

/**
 * Created by moguangjian on 15/10/14 17:51.
 */
public abstract class MAPI {

    private static final String TAG = MAPI.class.getSimpleName();

    private static final int HTTP_METHOD_POST = 0;
    private static final int HTTP_METHOD_GET = 1;
    private static final int HTTP_METHOD_PUT = 2;
    private static final int HTTP_METHOD_DELETE = 3;

    protected Context context;
    private MHttpBridge mHttpBridge;

    public MAPI(Context context) {
        this.context = context;

        initHttpBridge();
    }

    protected abstract String getApiServerPrefixDebug();

    protected abstract String getApiServerPrefix();

    protected abstract MHttpAble getMHttpAble();

    protected abstract void requestHttpPrepare(int method, String url, Map<String, Object> params, Class<? extends MModel> mModel, MHttpResponseAble mHttpResponseAble);

    private void initHttpBridge() {
        MHttpAble mHttpAble = getMHttpAble();
        if (MStringUtil.isObjectNull(mHttpAble)) {
            mHttpAble = new MHttpAsync(context);
        }

        mHttpBridge = new MHttpBridge();
        mHttpBridge.setMHttpAble(mHttpAble);
    }

    protected MHttpBridge getHttpBridge() {
        return mHttpBridge;
    }

    protected String getApiServer() {
        String apiServer;
        if (BuildConfig.DEBUG) {
            apiServer = getApiServerPrefixDebug();
        } else {
            apiServer = getApiServerPrefix();
        }
        if (MStringUtil.isEmpty(apiServer)) {
            apiServer = "";
            MLogUtil.e(TAG, "api server address is null");
        }
        return apiServer;
    }

    protected void post(String url, Map<String, Object> params, Class<? extends MModel> mModel, MHttpResponseAble mHttpResponseAble) {
        requestHttp(HTTP_METHOD_POST, url, params, mModel, mHttpResponseAble);
    }

    protected void get(String url, Map<String, Object> params, Class<? extends MModel> mModel, MHttpResponseAble mHttpResponseAble) {
        requestHttp(HTTP_METHOD_GET, url, params, mModel, mHttpResponseAble);
    }

    protected void put(String url, Map<String, Object> params, Class<? extends MModel> mModel, MHttpResponseAble mHttpResponseAble) {
        requestHttp(HTTP_METHOD_PUT, url, params, mModel, mHttpResponseAble);
    }

    protected void delete(String url, Map<String, Object> params, Class<? extends MModel> mModel, MHttpResponseAble mHttpResponseAble) {
        requestHttp(HTTP_METHOD_DELETE, url, params, mModel, mHttpResponseAble);
    }

    private void requestHttp(int method, String url, Map<String, Object> params, Class<? extends MModel> mModel, MHttpResponseAble mHttpResponseAble) {
        if (MStringUtil.isEmpty(url)) {
            url = "";
        }
        url = getApiServer() + url;
        requestHttpPrepare(method, url, params, mModel, mHttpResponseAble);

        switch (method) {
            case HTTP_METHOD_POST:
                mHttpBridge.post(url, params, mModel, mHttpResponseAble);
                break;

            case HTTP_METHOD_GET:
                mHttpBridge.get(url, params, mModel, mHttpResponseAble);
                break;

            case HTTP_METHOD_PUT:
                mHttpBridge.put(url, params, mModel, mHttpResponseAble);
                break;

            case HTTP_METHOD_DELETE:
                mHttpBridge.delete(url, params, mModel, mHttpResponseAble);
                break;
        }
    }

}
