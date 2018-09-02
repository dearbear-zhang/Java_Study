package com.my.xml;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class XmlConvertUtil {
    public static <T extends Object> T toJavaBean(String xmlString, Class cls) {
        T javaBean = null;
        XStream xstream = new XStream(new DomDriver());
        XStream.setupDefaultSecurity(xstream);
        xstream.allowTypes(new Class[]{cls});
        xstream.ignoreUnknownElements();
        xstream.processAnnotations(cls);
        try {
            javaBean = (T) xstream.fromXML(xmlString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return javaBean;
    }
}
