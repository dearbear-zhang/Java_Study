package com.my.xml;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.util.ArrayList;
import java.util.List;

public class XmlTest {
    public static  void main(){
        XmlJavaBean xmlJavaBean = new XmlJavaBean();
        xmlJavaBean.setName("feature");
        List<XmlJavaBean.Child> children = new ArrayList<>();
        XmlJavaBean.Child child = new XmlJavaBean.Child();
        XmlJavaBean.Child child1 = new XmlJavaBean.Child();
        child.setName("子");
        child.setValue("具体值");
        child1.setName("子");
        child1.setValue("具体值");
        children.add(child);
        children.add(child1);
        xmlJavaBean.setAttrets(children);

        XStream xstream = new XStream(new DomDriver());
        xstream.ignoreUnknownElements();
        xstream.processAnnotations(XmlJavaBean.class);

        //XML序列化
        String xml = xstream.toXML(xmlJavaBean);
        System.out.println(xml);
    }
}
