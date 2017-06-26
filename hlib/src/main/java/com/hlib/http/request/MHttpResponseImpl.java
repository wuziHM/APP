package com.hlib.http.request;

import android.content.Context;

import com.hlib.util.MToastUtil;


/**
 * Created by moguangjian on 15/10/10 16:01.
 */
public abstract class MHttpResponseImpl implements MHttpResponseAble {


    @Override
    public void onFailure(int statusCode, Object object) {
        onFailureResult(statusCode, object);
    }

    @Override
    public void onFailure(Context context, int statusCode, Object object) {
        if (object instanceof String) {
            MToastUtil.show(context, object.toString());
        } else {
            MToastUtil.show(context, "未知异常");
        }
        onFailure(statusCode, object);
    }

    @Override
    public void onSuccess(int statusCode, Object object) {
        onSuccessResult(statusCode, object);
    }

    @Override
    public void onFinish() {
        onFinishResult();
    }

    @Override
    public void onProgress(long bytesWritten, long totalSize) {

    }

    @Override
    public void onPrepare() {

    }

    public void onFailureResult(int statusCode, Object object) {
    }

    public abstract void onSuccessResult(int statusCode, Object object);

    public void onFinishResult() {
    }

    ;
}
