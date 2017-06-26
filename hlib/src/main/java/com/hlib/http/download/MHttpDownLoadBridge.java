package com.hlib.http.download;

import java.io.File;
import java.util.Map;

/**
 * MHttpDownLoadBridge
 * Created by moguangjian on 15/10/10 15:48.
 */
public class MHttpDownLoadBridge implements MHttpDownloadAble {

    private MHttpDownloadAble mHttpDownloadAble;

    public MHttpDownLoadBridge() {

    }

    public MHttpDownLoadBridge(MHttpDownloadAble mHttpDownloadAble) {
        this.mHttpDownloadAble = mHttpDownloadAble;
    }

    public MHttpDownloadAble MHttpDownloadAble() {
        return mHttpDownloadAble;
    }

    public void setMHttpDownloadAble(MHttpDownloadAble mHttpDownloadAble) {
        this.mHttpDownloadAble = mHttpDownloadAble;
    }

    @Override
    public void get(String url, Map<String, String> params, File saveFile, MHttpDownloadResponseAble mHttpDownloadResponseAble) {
        mHttpDownloadAble.get(url, params, saveFile, mHttpDownloadResponseAble);
    }

    @Override
    public void post(String url, Map<String, String> params, File saveFile, MHttpDownloadResponseAble mHttpDownloadResponseAble) {
        mHttpDownloadAble.post(url, params, saveFile, mHttpDownloadResponseAble);
    }
}
