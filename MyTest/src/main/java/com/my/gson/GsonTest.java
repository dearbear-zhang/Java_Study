package com.my.gson;

import com.google.gson.Gson;

public class GsonTest {
    public static void main(String[] args) {
        TestJavaBean javaBean = new TestJavaBean();
        javaBean.setName("张三");
        javaBean.setAge("23");

        String json = new Gson().toJson(javaBean);
        System.out.println(json);
    }
}
