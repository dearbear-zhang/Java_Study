package com.my.file;

import java.io.File;

public class FileTest {
    public static void main(String[] args) {
        String xx = "mnt/sdcard2/Case/huang211332789/tfkasdlf.db";
        String test = "mnt/sdcard/MYReader目录/" + xx.substring(xx.indexOf("Case") + 5).replace(".db", ".dmf");

        String path = "D:\\test\\afjasl\\werafsdf\\test.txt";
        File file = new File(path);
        file.getParentFile().mkdirs();
        System.out.println(test);
        String xx1 = "1234";
        byte[] xxx = xx1.getBytes();
        if (new File("E:\\test\\xx").exists()) {
            System.out.println("1");
        } else {
            System.out.println("1");

        }
    }


}
