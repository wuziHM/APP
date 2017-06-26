package com.hlib.http.download;

import java.io.File;

/**
 * Created by moguangjian on 15/10/10 16:01.
 */
public abstract class MHttpDownloadResponseImpl implements MHttpDownloadResponseAble {

    @Override
    public void onFailure(int statusCode, Object object) {
        onFailureResult(statusCode, object);
    }

    @Override
    public void onSuccess(int statusCode, File saveFile) {
        onSuccessResult(statusCode, saveFile);
    }

    @Override
    public void onFinish() {
        onFinishResult();
    }

    @Override
    public void onProgress(long bytesWritten, long totalSize) {
        onProgressResult(bytesWritten, totalSize);
    }

    public void onFailureResult(int statusCode, Object object) {
    }

    public abstract void onSuccessResult(int statusCode, File saveFile);

    public abstract void onProgressResult(long bytesWritten, long totalSize);

    public abstract void onFinishResult();
}
