package com.hlib.http.request;

import java.util.Map;

/**
 * MHttpAsync 功能接口
 * Created by moguangjian on 15/10/10 13:54.
 */
public interface MHttpAble {

    String IS_SHOW_PROGRESS = "is_show_progress";

    /**
     * 获取请求库实例
     *
     * @return
     */
    Object getInstance();

    /**
     * 添加头部
     *
     * @param header 头部名字
     * @param value 头部值
     */
    void addHeader(String header, String value);

    /**
     * 删除指定头部
     *
     * @param header 头部名字
     */
    void removeHeader(String header);

    /**
     * 删除所有头部
     */
    void removeAllHeaders();

    /**
     * get请求
     *
     * @param url 请求地址
     * @param params 请求参数
     * @param mModel 解析的数据模型
     * @param mHttpResponseAble 请求回调
     */
    void get(String url, Map<String, Object> params, Class<? extends MModel> mModel, MHttpResponseAble mHttpResponseAble);

    void post(String url, Map<String, Object> params, Class<? extends MModel> mModel, MHttpResponseAble mHttpResponseAble);

    void put(String url, Map<String, Object> params, Class<? extends MModel> mModel, MHttpResponseAble mHttpResponseAble);

    void delete(String url, Map<String, Object> params, Class<? extends MModel> mModel, MHttpResponseAble mHttpResponseAble);


}
