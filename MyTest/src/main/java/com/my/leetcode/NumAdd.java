package com.my.leetcode;

public class NumAdd {
    public static void main(String[] args) {

    }

    public static int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length - 1; i++) {
            int a = nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                int b = nums[j];
                if (a + b == target) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    /**
     * @param s 罗马数字转整数
     * @return
     */
    public int romanToInt(String s) {
        int num = 0, preNum = 0;
        preNum = getValue(s.charAt(0));
        for (int i = 1; i < s.length(); i++) {
            int curNum = getValue(s.charAt(i));
            if (preNum < curNum) {
                num -= preNum;
            } else {
                num += preNum;
            }
            preNum = curNum;
        }
        num += preNum;
        return num;
    }

    /**
     * @param s 单个罗马字符转数字
     * @return
     */
    private int getValue(char s) {
        switch (s) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            default:
                return 0;
        }
    }
}
