package com.my.Date;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DataFormatTest {
    public static void main() {
        long curTime = System.currentTimeMillis();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = new Date(curTime);
        String formatString = simpleDateFormat.format(date);
        System.out.println(formatString);
    }
}
