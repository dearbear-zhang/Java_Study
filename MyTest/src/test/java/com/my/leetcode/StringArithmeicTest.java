package com.my.leetcode;

import junit.framework.TestCase;


public class StringArithmeicTest extends TestCase {
    private StringArithmeic a;


    public void testLengthOfLongestSubstring() {
        String a = " ";
        StringArithmeic arithmeic = new StringArithmeic();

        System.out.println(arithmeic.lengthOfLongestSubstring(a));
        String b = "";
        System.out.println(arithmeic.lengthOfLongestSubstring(b));
        String c = "aaaaaa";
        System.out.println(arithmeic.lengthOfLongestSubstring(c));
    }

    public void testLongestPalindrome() {
        StringArithmeic arithmeic = new StringArithmeic();
        String res = arithmeic.longestPalindrome("hello");
        System.out.println(res);
    }

    public void testLongestPalindromeEx() {
        StringArithmeic a = new StringArithmeic();
        System.out.println(a.longestPalindromeEx("dfasadg"));
    }


    public void testConvert() {
        System.out.println(a.convert("LEETCODEISHIRING", 1));
    }

    public void setUp() throws Exception {
        a = new StringArithmeic();
    }
}