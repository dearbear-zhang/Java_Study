package com.my.zip;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.progress.ProgressMonitor;

import java.io.File;

public class ZipUtils {
    public static void unZipFileWithProgress(File zipFile, String filePath) throws ZipException {
        ZipFile zFile = new ZipFile(zipFile);
        if (!zFile.isValidZipFile()) {
            throw new ZipException("exception");
        }
        File destDir = new File(filePath);
        if (destDir.isDirectory() && !destDir.exists()) {
            destDir.mkdir();
        }
        if (zFile.isEncrypted()) {
            zFile.setPassword(""); // 设置解压密码
        }
        final ProgressMonitor monitor = zFile.getProgressMonitor();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (monitor.getPercentDone() < 100) {
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(monitor.getPercentDone());
                }
                System.out.println(monitor.getPercentDone());
            }
        }).start();
        zFile.extractAll(filePath);
    }

    public static void main(String[] args) {
        File file = new File("C:\\WeiYun\\329330316\\Job\\BKDemo.zip");
        String path = "C:\\WeiYun\\329330316\\Job";
        try {
            unZipFileWithProgress(file, path);
        } catch (ZipException e) {
            e.printStackTrace();
        }
    }
}
