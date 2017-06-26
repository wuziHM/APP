package com.hlib.http.request;


import android.content.Context;

/**
 * http请求回调接口
 * Created by moguangjian on 15/10/10 14:08.
 */
public interface MHttpResponseAble {

    /**
     * 请求准备
     */
    void onPrepare();

    /**
     * 请求失败
     *
     * @param statusCode
     * @param object
     */
    public void onFailure(int statusCode, Object object);
    public void onFailure(Context context, int statusCode, Object object);

    /**
     * 请求成功
     *
     * @param statusCode
     * @param object
     */
    public void onSuccess(int statusCode, Object object);

    /**
     * 请求结束
     */
    public void onFinish();

    /**
     * 请求进度
     *
     * @param bytesWritten
     * @param totalSize
     */
    public void onProgress(long bytesWritten, long totalSize);
}
