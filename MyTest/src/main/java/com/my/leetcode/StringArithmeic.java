package com.my.leetcode;

import java.util.HashSet;

/**
 * 字符串相关算法
 */
public class StringArithmeic {

    /**
     * 无重复字符的最长子串(窗口滑动的实现方式)
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
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

    /**
     * ;最长回文子串
     * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
     * <p>
     * 示例 1：
     * <p>
     * 输入: "babad"
     * 输出: "bab"
     * 注意: "aba" 也是一个有效答案。
     * 示例 2：
     * <p>
     * 输入: "cbbd"
     * 输出: "bb"
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/longest-palindromic-substring
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        int maxLength = 1;
        int length = s.length();
        char[] array = s.toCharArray();
        int start = 0;

        for (int i = 0; i < length - 1; i++) {
            for (int j = i + 1; j < length; j++) {
                if (isPalindrome(array, i, j) && (j - i + 1) > maxLength) {
                    maxLength = j - i + 1;
                    start = i;
                }
            }
        }
        return s.substring(start, start + maxLength);
    }

    /**
     * @param subString 判断当前字符串数组是否为回文串
     * @param start
     * @param end
     * @return
     */
    private boolean isPalindrome(char[] subString, int start, int end) {
        boolean res = true;
        while (start < end) {
            if (subString[start] != subString[end]) {
                res = false;
                break;
            }
            start++;
            end--;
        }
        return res;
    }

    /**
     * 动态规划的方式实现最大回文子串
     *
     * @param s
     * @return
     */
    public String longestPalindromeEx(String s) {
        int length = s.length();
        boolean[][] dp = new boolean[length][length];
        char[] charArray = s.toCharArray();
        int startIdx = 0, maxLength = 0;
        if (length == 0 || length == 1) {
            return s;
        }
        for (int i = 0; i < length - 1; i++) {
            for (int j = length - 1; j > i; j--) {
                dp[i][j] = dp[i + 1][j - 1] && (charArray[i] == charArray[j]);
                if (dp[i][j] && (j - i + 1 > maxLength)) {
                    startIdx = i;
                }
            }
        }
        return s.substring(startIdx, maxLength - 1);
    }

    /**
     * Z型变换,模拟法
     *
     * @param s
     * @param numRows
     * @return
     */
    public String convert(String s, int numRows) {
        int length = s.length();
        StringBuffer[] changeArray = new StringBuffer[numRows];
        for (int i = 0; i < numRows; i++) {
            changeArray[i] = new StringBuffer();
        }
        char[] array = s.toCharArray();
        boolean upToDown = false;
        int loc = 0;
        if (numRows == 1){
            return s;
        }
        for (int i = 0; i < length; i++) {
            changeArray[loc].append(array[i]);
            if (loc == 0 || loc == numRows - 1) {
                upToDown = !upToDown;
            }
            loc += upToDown ? 1 : -1;
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            result.append(changeArray[i]);
        }
        return result.toString();
    }

}
