package com.my.main;

import com.my.file.FileTest;
import com.my.xml.PersionBean;
import com.my.xml.XmlTest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.text.Collator;
import java.util.*;

public class MainAcitivity {
    public static void main(String[] args) {
//        Observable.just("1", "2", "3")
//                .subscribe(new Consumer<String>() {
//                    @Override
//                    public void accept(String s) throws Exception {
//                        System.out.println(s + ": helloword");
//                    }
//                });
//        XmlTest.main();
//        ReflectTest.main();
//        AnnotationTest.main();
//        GsonTest.main();
//        DataFormatTest.main();
//        RxjavaTest.main();
//        BeanUtilsTest.main();
        String msg = "//dataforamt";
        if (msg.startsWith("//")){
            System.out.println("fund");
        }
        Comparator<PersionBean> CHINA_COMPARE1 = new Comparator<PersionBean>() {
            @Override
            public int compare(PersionBean o1, PersionBean o2) {
                Comparator<Object> CHINA_COMPARE = Collator.getInstance(java.util.Locale.CHINA);
                return ((Collator) CHINA_COMPARE).compare(o1.getName(), o2.getName());
            }
        };

        PersionBean zhang = new PersionBean();
        zhang.setName("张三");
        PersionBean lisi = new PersionBean();
        lisi.setName("李四");
        PersionBean wang = new PersionBean();
        wang.setName("王五");
        List<PersionBean> list = new ArrayList<>();
        list.add(zhang);
        list.add(lisi);
        list.add(wang);

        Collections.sort(list, CHINA_COMPARE1);
        System.out.println(list);
        File file = new File("");
        try {
            FileOutputStream stream = new FileOutputStream(file);
//            stream.getChannel().map(FileChannel.MapMode.READ_WRITE);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
