package com.my.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MatcherUtilsTest {
    public static void main(String[] args) {
        String msg = "HUAWEI_MHA-AL00_201812179423";
        match(msg);
    }

    private static void match(String msg) {
        String patternRules = "(.*)(_.*)";
        Pattern pattern = Pattern.compile(patternRules);
        Matcher matcher = pattern.matcher(msg);
        if (matcher.find()) {
            String xx = matcher.group(1);
            System.out.println(xx);
        }
    }
}
