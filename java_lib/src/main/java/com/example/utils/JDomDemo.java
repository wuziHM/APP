package com.example.utils;


import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Author：燕青 $ on 16/12/24 14:53
 * E-mail：359222347@qq.com
 * <p>
 * use to... 解析xml
 */
public class JDomDemo implements XmlDocument {

    private final static String dpTemplate = "<dimen name=\"{0}\">{1}</dimen>\n";
    private final static String spTemplate = "<dimen name=\"{0}\">{1}sp</dimen>\n";

    public void createXml(String fileName) {
        Document document;
        Element root;
        root = new Element("employees");
        document = new Document(root);
        Element employee = new Element("employee");
        root.addContent(employee);
        Element name = new Element("name");
        name.setText("ddvip");
        employee.addContent(name);
        Element sex = new Element("sex");
        sex.setText("m");
        employee.addContent(sex);
        Element age = new Element("age");
        age.setText("23");
        employee.addContent(age);
        XMLOutputter XMLOut = new XMLOutputter();
        try {
            XMLOut.output(document, new FileOutputStream(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void parserXml(String fileName) {


        StringBuffer sbForWidth = new StringBuffer();
        sbForWidth.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");
        sbForWidth.append("<resources>");


        SAXBuilder builder = new SAXBuilder(false);
        try {
            Document document = builder.build(fileName);
//            wDocument =
            Element employees = document.getRootElement();
            List employeeList = employees.getChildren("dimen");
            int length = employeeList.size();
            System.out.println("长度:" + length);
            for (int i = 0; i < length; i++) {
                Element employee = (Element) employeeList.get(i);
                String attr = employee.getAttributeValue("name");
                if (attr.startsWith("sp_")) {
                    String value = employee.getValue();
                    value = value.substring(0, value.length() - 2);
                    int iValue = Integer.parseInt(value);
                    if (iValue < 20 && iValue > 10) {
                        iValue -= 1;
                    } else if (iValue >= 20) {
                        iValue -= 2;
                    }
                    sbForWidth.append(spTemplate.replace("{0}", attr).replace("{1}", iValue + ""));
                } else if (attr.startsWith("dp_")) {
                    String value = employee.getValue();
                    sbForWidth.append(dpTemplate.replace("{0}", attr).replace("{1}", value + ""));
                }

                System.out.println("name:" + employee.getName() + "   AttributeValue:" + employee.getAttributeValue("name") + "   value:" + employee.getValue());
            }
            sbForWidth.append("</resources>");
            File layxFile = new File("/Users/minhu/Documents/E/weyee/program/POS/app/src/main/res/values", "test.xml");
            PrintWriter pw = new PrintWriter(new FileOutputStream(layxFile));
            pw.print(sbForWidth.toString());

            pw.close();

        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

