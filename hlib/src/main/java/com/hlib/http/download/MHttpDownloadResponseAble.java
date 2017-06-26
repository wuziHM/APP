package com.hlib.http.download;


import java.io.File;

/**
 * http下载文件请求回调接口
 * Created by moguangjian on 15/10/10 14:08.
 */
public interface MHttpDownloadResponseAble {

    /**
     * 请求失败
     *
     * @param statusCode
     * @param object
     */
    public void onFailure(int statusCode, Object object);

    /**
     * 请求成功
     *
     * @param statusCode
     * @param saveFile
     */
    public void onSuccess(int statusCode, File saveFile);

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
