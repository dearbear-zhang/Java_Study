package com.my.leetcode;

import java.util.HashSet;

/**
 * 字符串相关算法
 */
public class StringArithmeic {
    public static void main(String[] args) {
        String a = " ";
        System.out.println(lengthOfLongestSubstring(a));
        String b = "";
        System.out.println(lengthOfLongestSubstring(b));
        String c = "aaaaaa";
        System.out.println(lengthOfLongestSubstring(c));
    }

    /**
     * 无重复字符的最长子串
     *
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring(String s) {
        HashSet<Character> set = new HashSet<>();
        int maxNum = 0;
        for (int starIdx = 0, endIdx = -1; starIdx < s.length(); starIdx++) {
            if (starIdx != 0) {
                set.remove(s.charAt(starIdx - 1));
            }
            while (endIdx + 1 < s.length() && !set.contains(s.charAt(endIdx + 1))) {
                set.add(s.charAt(endIdx + 1));
                endIdx++;
            }
            maxNum = Math.max(maxNum, set.size());
        }
        return maxNum;
    }
}
