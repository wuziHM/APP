package com.hlib.http.download;

import java.io.File;
import java.util.Map;

/**
 * MHttpDownloadAsync 功能接口
 * Created by moguangjian on 15/10/10 13:54.
 */
public interface MHttpDownloadAble {

    void get(String url, Map<String, String> params, File saveFile, MHttpDownloadResponseAble mHttpDownloadResponseAble);

    void post(String url, Map<String, String> params, File saveFile, MHttpDownloadResponseAble mHttpDownloadResponseAble);

}
