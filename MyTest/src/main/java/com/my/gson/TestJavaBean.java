package com.my.gson;

import java.io.Serializable;

public class TestJavaBean implements Serializable {
    private String name;
    private String age;
    private String set;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSet() {
        return set;
    }

    public void setSet(String set) {
        this.set = set;
    }
}
