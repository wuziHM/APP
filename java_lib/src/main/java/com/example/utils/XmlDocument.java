package com.example.utils;

/**
 * Author：燕青 $ on 16/12/24 14:53
 * E-mail：359222347@qq.com
 * <p>
 * use to...
 */
public interface XmlDocument {
    /**
     * 建立XML文档
     *
     * @param fileName 文件全路径名称
     */
    public void createXml(String fileName);

    /**
     * 解析XML文档
     *
     * @param fileName 文件全路径名称
     */
    public void parserXml(String fileName);
}
