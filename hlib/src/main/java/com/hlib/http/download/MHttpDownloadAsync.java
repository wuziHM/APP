package com.hlib.http.download;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.FileAsyncHttpResponseHandler;

import java.io.File;
import java.util.Map;

import cz.msebera.android.httpclient.Header;

/**
 * http请求下载文件。使用android-async-http请求http。
 * Created by moguangjian on 15/10/16 14:38.
 */
public class MHttpDownloadAsync implements MHttpDownloadAble {

    private static final String TAG = MHttpDownloadAsync.class.getSimpleName();
    private Context context;
    private AsyncHttpClient asyncHttpClient;

    public MHttpDownloadAsync(Context context) {
        this.context = context;
        init();
    }

    private void init() {
        asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.setTimeout(30 * 60 * 1000);
    }

    @Override
    public void get(String url, Map<String, String> params, File saveFile, MHttpDownloadResponseAble mHttpDownloadResponseAble) {
        asyncHttpClient.get(url, new MFileAsyncHttpResponseHandler(saveFile, mHttpDownloadResponseAble));
    }

    @Override
    public void post(String url, Map<String, String> params, File saveFile, MHttpDownloadResponseAble mHttpDownloadResponseAble) {
        asyncHttpClient.post(url, new MFileAsyncHttpResponseHandler(saveFile, mHttpDownloadResponseAble));
    }

    private class MFileAsyncHttpResponseHandler extends FileAsyncHttpResponseHandler {

        private MHttpDownloadResponseAble mHttpDownloadResponseAble;

        public MFileAsyncHttpResponseHandler(File file, MHttpDownloadResponseAble mHttpDownloadResponseAble) {
            super(file);

            this.mHttpDownloadResponseAble = mHttpDownloadResponseAble;
        }

        @Override
        public void onFailure(int statusCode, Header[] headers, Throwable throwable, File file) {
            mHttpDownloadResponseAble.onFailure(statusCode, throwable);
        }

        @Override
        public void onSuccess(int statusCode, Header[] headers, File file) {
            mHttpDownloadResponseAble.onSuccess(statusCode, file);
        }

        @Override
        public void onProgress(long bytesWritten, long totalSize) {
            super.onProgress(bytesWritten, totalSize);
            mHttpDownloadResponseAble.onProgress(bytesWritten, totalSize);
        }

        @Override
        public void onFinish() {
            super.onFinish();
            mHttpDownloadResponseAble.onFinish();
        }
    }
}
