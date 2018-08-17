package com.my.xml;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.util.ArrayList;
import java.util.List;

public class XmlTest {
    public static  void main(){
        FeatureBean formatBean = new FeatureBean();
        formatBean.setRoot("feature_info");
        FeatureBean.WidgetInfoBean widgetInfoBean = new FeatureBean.WidgetInfoBean();
        List<String> attributes = new ArrayList<>();
        attributes.add("primary,logo_dc4205");
        attributes.add("secondary,logo_dc4210");
        widgetInfoBean.setAttribute(attributes);
        FeatureBean.WidgetFunctionModuleBean widgetFunctionModuleBean = new FeatureBean.WidgetFunctionModuleBean();
        List<FeatureBean.WidgetFunctionModuleBean.AttributeBean> attributeBeans = new ArrayList<>();
        FeatureBean.WidgetFunctionModuleBean.AttributeBean attributeBean = new FeatureBean.WidgetFunctionModuleBean.AttributeBean();
        attributeBean.setName("primary");
        attributeBean.setContent("1001,phone_collection,datacollector_selector,UCS,standard");
        FeatureBean.WidgetFunctionModuleBean.AttributeBean attributeBean1 = new FeatureBean.WidgetFunctionModuleBean.AttributeBean();
        attributeBean1.setName("primary");
        attributeBean1.setContent("1002,wifi_scanning_mode,wireless_scanning_selector,WCS,wcsModeSelect");

        attributeBeans.add(attributeBean);
        attributeBeans.add(attributeBean1);
        widgetFunctionModuleBean.setAttribute(attributeBeans);

        formatBean.setWidget_function_module(widgetFunctionModuleBean);

        XStream xstream = new XStream(new DomDriver());
        xstream.ignoreUnknownElements();
        xstream.processAnnotations(FeatureBean.class);

        //XML序列化
        String xml = xstream.toXML(formatBean);
        System.out.println(xml);
    }
}
