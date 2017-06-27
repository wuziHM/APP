package allenhu.app.net;

import android.content.Context;

import com.google.gson.Gson;
import com.hlib.http.request.MHttpAsync;
import com.hlib.http.request.MHttpResponseAble;
import com.hlib.http.request.MModel;
import com.hlib.util.MLogUtil;

import java.util.Map;

/**
 * Author：燕青 $ on 17/6/26 19:22
 * E-mail：359222347@qq.com
 * <p>
 * use to...
 */

public class GHttpAsync extends MHttpAsync {
    public GHttpAsync(Context context) {
        super(context);
        isHandleParentServerResponse = false;
    }

    @Override
    public void get(String url, Map<String, Object> params, Class<? extends MModel> mModel, MHttpResponseAble mHttpResponseAble) {
        //做一个对有没有token的判断和处理，每次请求的时候都去判断一下有没有token，如果没有的话就先去拿token
//        syncServerTime("get", url, params, mModel, mHttpResponseAble);
        requestGet(url, params, mModel, mHttpResponseAble);
    }

    @Override
    public void post(final String url, final Map<String, Object> params, final Class<? extends MModel> mModel, final MHttpResponseAble mHttpResponseAble) {
//        syncServerTime("post", url, params, mModel, mHttpResponseAble);
        requestPost(url, params, mModel, mHttpResponseAble);
    }

    @Override
    protected void onServerResponse(int statusCode, Class<? extends MModel> mModel, Gson gson, String response, MHttpResponseAble mHttpResponseAble) {
        super.onServerResponse(statusCode, mModel, gson, response, mHttpResponseAble);
        MLogUtil.e("response:" + response);
        mHttpResponseAble.onSuccess(statusCode, response);
    }
}
