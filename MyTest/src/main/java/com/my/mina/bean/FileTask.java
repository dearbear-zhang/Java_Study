package com.my.mina.bean;

import java.util.concurrent.atomic.AtomicInteger;

public class FileTask {
    public boolean running = true;
    public long id;
    public String fileName;
    public String filePath;
    public String md5;
    public long startTime;
    public long startTime2; //网络计时
    public long size;
    public int fileSegmentSize;
    public AtomicInteger partId = new AtomicInteger(0);
}
