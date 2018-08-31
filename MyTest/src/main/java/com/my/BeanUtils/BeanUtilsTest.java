package com.my.BeanUtils;

public class BeanUtilsTest {
    public static void main(){
        Class1 class1 = new Class1();
        class1.setName("对象1");
        class1.setSex("男");
        Class1.Teature teature = new Class1.Teature();
        teature.setT1("对象1的t1");
        teature.setT2("对象1的t2");
        class1.setTeature(teature);

        Class1 class2 = new Class1();
        class2.setName("对象2");
//        class2.setSex("女");
        Class1.Teature teature2 = new Class1.Teature();
        teature2.setT1("对象2的t1");
        teature2.setT2("对象2的t2");
        class2.setTeature(teature2);

        try {
//            BeanUtils.copyProperties(class1, class2);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(class1);

    }



}
