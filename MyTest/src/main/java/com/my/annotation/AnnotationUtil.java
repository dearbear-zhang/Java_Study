package com.my.annotation;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.lang.reflect.Field;

public class AnnotationUtil {
    public static <T extends Object> T getFeatureItemValue(Object input, String primaryName, String secondaryName) {
        T value = null;
        if (input == null) {
            return null;
        }
        Class cl = input.getClass();
        Field[] fields = cl.getDeclaredFields();
        for (Field field : fields) {
            // 判断XStreamAlias注解
            XStreamAlias animation1 = field.getAnnotation(XStreamAlias.class);
            if (animation1 != null && animation1.value().equals(primaryName)) {
                try {
                    field.setAccessible(true);
                    Object object = field.get(input);
                    if (secondaryName == null) {
                        value = (T) object;
                    } else {
                        value = getFeatureItemValue(object, secondaryName, null);
                    }
                    break;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            // 判断XStreamImplicit注解,该注解用于注解xml的List对象
            XStreamImplicit animation2 = field.getAnnotation(XStreamImplicit.class);
            if (animation2 != null && animation2.itemFieldName().equals(primaryName)) {
                try {
                    field.setAccessible(true);
                    Object object = field.get(input);
                    if (secondaryName == null) {
                        value = (T) object;
                    } else {
                        value = getFeatureItemValue(object, secondaryName, null);
                    }
                    break;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return value;
    }
}
