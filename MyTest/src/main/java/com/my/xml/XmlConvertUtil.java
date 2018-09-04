package com.my.xml;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class XmlConvertUtil {
    /***
     * xml转javaBean
     * @param xmlString
     * @param cls
     * @param <T>
     * @return
     */
    public static <T extends Object> T toJavaBean(String xmlString, Class cls) {
        T javaBean = null;
        XStream xstream = new XStream(new DomDriver());
//        XStream.setupDefaultSecurity(xstream);
//        xstream.allowTypes(new Class[]{cls});
        xstream.ignoreUnknownElements();
        xstream.processAnnotations(cls);
        try {
            javaBean = (T) xstream.fromXML(xmlString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return javaBean;
    }

    /***
     * JavaBean 转 xml字符串
     * @param object
     * @param cls
     * @return
     */
    public static String toXml(Object object, Class cls) {
        String xml = null;
        XStream xstream = new XStream(new DomDriver());
        xstream.ignoreUnknownElements();
        xstream.processAnnotations(cls);
        xml = xstream.toXML(object);
        return xml;
    }
}
