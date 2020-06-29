package com.my.leetcode;

import junit.framework.TestCase;

public class NumAddTest extends TestCase {
    private NumAdd mNumObject;

    public void setUp() throws Exception {
        mNumObject = new NumAdd();
    }

    public void testRomanToInt() {
        System.out.println(mNumObject.romanToInt("XIV"));
    }
}