package com.hlib.http.request;

import java.util.Map;

/**
 * http请求桥
 * Created by moguangjian on 15/10/10 15:48.
 */
public class MHttpBridge implements MHttpAble {

//    public static final String KEY_CONTENT_TYPE = "contentType";
//    public static final String CONTENT_TYPE_IMAGE = "image/jpeg";

    private MHttpAble mHttpAble;

    public MHttpBridge() {

    }

    public MHttpBridge(MHttpAble mHttpAble) {
        this.mHttpAble = mHttpAble;
    }

    public MHttpAble getMHttpAble() {
        return mHttpAble;
    }

    public void setMHttpAble(MHttpAble mHttpAble) {
        this.mHttpAble = mHttpAble;
    }

    @Override
    public Object getInstance() {
        return mHttpAble.getInstance();
    }

    @Override
    public void addHeader(String header, String value) {
        mHttpAble.addHeader(header, value);
    }

    @Override
    public void removeHeader(String header) {
        mHttpAble.removeHeader(header);
    }

    @Override
    public void removeAllHeaders() {
        mHttpAble.removeAllHeaders();
    }

    @Override
    public void get(String url, Map<String, Object> params, Class<? extends MModel> mModel, MHttpResponseAble mHttpResponseAble) {
        mHttpAble.get(url, params, mModel, mHttpResponseAble);
    }

    @Override
    public void post(String url, Map<String, Object> params, Class<? extends MModel> mModel, MHttpResponseAble mHttpResponseAble) {
        mHttpAble.post(url, params, mModel, mHttpResponseAble);
    }

    @Override
    public void put(String url, Map<String, Object> params, Class<? extends MModel> mModel, MHttpResponseAble mHttpResponseAble) {
        mHttpAble.put(url, params, mModel, mHttpResponseAble);
    }

    @Override
    public void delete(String url, Map<String, Object> params, Class<? extends MModel> mModel, MHttpResponseAble mHttpResponseAble) {
        mHttpAble.delete(url, params, mModel, mHttpResponseAble);
    }
}
