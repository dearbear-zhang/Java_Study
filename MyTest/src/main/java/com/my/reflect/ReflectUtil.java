package com.my.reflect;

import com.my.annotation.AnnotationUtil;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

public class ReflectUtil {
    /***
     * 基本类型,String类型和List类型,to对应字段为null,直接判断为非null直接进行from赋值;当变量为类对象且不为null则迭代调用本函数
     * @param to
     * @param from
     */
    public static void javaBeanCopy(Object to, Object from) {
        Class cl = to.getClass();

        Field[] fields = cl.getDeclaredFields();
        for (Field field : fields) {
//            System.out.println(field);
            Class fieldClass = field.getType();
            Object fromValue = null;
            Object toValue = null;
            field.setAccessible(true);
            try {
                fromValue = field.get(from);
                toValue = field.get(to);

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            // from值为null,当前变量跳过
            if (fromValue == null) {
                continue;
            }
            // 基本类型,String类型和List类型,to对应字段为null,直接判断为非null则赋值;变量为类对象且都不为null则迭代调用本函数
            if (fieldClass.isPrimitive() || fieldClass == String.class || fieldClass.isArray() || toValue == null) {
                try {
                    field.set(to, fromValue);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            } else {
                javaBeanCopy(toValue, fromValue);
            }
        }
    }

    public static Set<String> getVariableNames(Object object, String primaryName) {
        return getVariableNames(AnnotationUtil.getFeatureItemValue(object, primaryName, null));
    }

    /***
     * 获取变量下xstream注解的变量名
     * @param object
     * @return
     */
    private static Set<String> getVariableNames(Object object) {
        Set<String> nameSet = new HashSet<String>();
        Class cl = object.getClass();
        Field[] fields = cl.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object value = field.get(object);
                if (value != null) {
                    // 判断XStreamAlias注解
                    XStreamAlias animation1 = field.getAnnotation(XStreamAlias.class);
                    if (animation1 != null) {
                        nameSet.add(animation1.value());
                    }
                    // 判断XStreamImplicit注解,该注解用于注解xml的List对象
                    XStreamImplicit animation2 = field.getAnnotation(XStreamImplicit.class);
                    if (animation2 != null) {
                        nameSet.add(animation2.itemFieldName());
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return nameSet;
    }
}
