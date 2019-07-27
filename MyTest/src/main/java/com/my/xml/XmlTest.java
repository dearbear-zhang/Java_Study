package com.my.xml;

//import com.my.xml.project.DC_4205;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class XmlTest {
    public static void main(String[] args) {
//        FeatureBean formatBean = AS_2000.getFeatureBean();
//        FeatureBean formatBean = DC_4201.getFeatureBean();
//        FeatureBean formatBean = DC_4202.getFeatureBean();
//        FeatureBean formatBean = DC_4205_NOT_IDC.getFeatureBean();
//        FeatureBean formatBean = DC_4205.getFeatureBean();
//        FeatureBean formatBean = DC_4205G_Develop.getFeatureBean();
//        FeatureBean formatBean = DC_4205G.getFeatureBean();
//        FeatureBean formatBean = DC_4205M.getFeatureBean();
//        FeatureBean formatBean = DC_4205S.getFeatureBean();
//        FeatureBean formatBean = DC_4210_RK29.getFeatureBean();
//        FeatureBean formatBean = DC_4210.getFeatureBean();
//        FeatureBean formatBean = NDC_4201.getFeatureBean();
//        FeatureBean formatBean = XDH_1800A.getFeatureBean();
//        FeatureBean formatBean = XDH_1810A.getFeatureBean();
//        FeatureBean formatBean = XDH_1900A_NOT_IDC.getFeatureBean();
//        FeatureBean formatBean = XDH_1900A.getFeatureBean();
//        FeatureBean formatBean = XDH_1900B.getFeatureBean();

//        List<FeatureBean> datas = new ArrayList<FeatureBean>();
//        datas.add(formatBean);
        //XML序列化
//        String xml = XmlConvertUtil.toXml(formatBean, FeatureBean.class);
//        System.out.println(xml);
        // 写入xml文件
//        String xmlFileName = "/xml/AS-2000.xml";
//        String xmlFileName = "/xml/DC-4201.xml";
//        String xmlFileName = "/xml/DC-4202.xml";
//        String xmlFileName = "/xml/DC-4205(NOT_IDC).xml";
        String xmlFileName = "/xml/DC-4205.xml";
//        String xmlFileName = "/xml/DC-4205G(Develop).xml";
//        String xmlFileName = "/xml/DC-4205G.xml";
//        String xmlFileName = "/xml/DC-4205M.xml";
//        String xmlFileName = "/xml/DC-4205S.xml";
//        String xmlFileName = "/xml/DC-4210(RK29).xml";
//        String xmlFileName = "/xml/DC-4210.xml";
//        String xmlFileName = "/xml/NDC-4201.xml";
//        String xmlFileName = "/xml/XDH-1800A.xml";
//        String xmlFileName = "/xml/XDH-1810A.xml";
//        String xmlFileName = "/xml/XDH-1900A(NOT_IDC).xml";
//        String xmlFileName = "/xml/XDH-1900A.xml";
//        String xmlFileName = "/xml/XDH-1900B.xml";
//        writeXmlFile(xmlFileName, xml);
//        FeatureBean featureBean = XmlConvertUtil.toJavaBean(xml, FeatureBean.class);
//        System.out.println(featureBean);
//        String xml2 = XmlConvertUtil.toXml(featureBean, FeatureBean.class);
//        System.out.println(xml2);
    }

    private static void writeXmlFile(String filePath, String msg) {
        File file = new File(filePath);
        // 父目录不存在,则创建
        System.out.println(file.getAbsolutePath());
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        try {
            FileOutputStream outputStream = new FileOutputStream(file, false);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            writer.newLine();
            writer.write(msg);
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
