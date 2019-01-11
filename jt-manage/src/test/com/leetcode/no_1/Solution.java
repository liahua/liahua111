package com.leetcode.no_1;

//abcabcbb

import org.junit.Test;

public class Solution {
    @Test
    public void test() {
        String str = "ac";
        System.out.println(longestPalindrome(str));

    }


    /**
     * 暴力1
     * 优化
     * 单点双向,点位数量为2n-1个
     *
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        int len = s.length();
        int max = 0;
        String result = s;
        if (s == null || s.length() <= 1) return s;
        int start = 0, end = 0;
        for (int i = 0; i < len; i++) {
            int len1 = getPalindromicLength(s, i, i);
            int len2 = getPalindromicLength(s, i, i + 1);
            int reaLen = len1 > len2 ? len1 : len2;
            /**
             * 当且仅当回文长度为目前为止最长长度时,才将长度赋值给max,否则不赋值,并记录此时的起始,结束元素位置.
             */
            if (reaLen > max) {
                max = reaLen;
                start = i - ((reaLen - 1) >> 1);
                end = i + (reaLen >> 1);
            }
            /**
             * 当max长度超过剩余字符串长度时,说明不可能存在比当前max所记录的回文长度更长的字符串,因此直接跳出循环,并返回最长字符串的起始末尾元素的subString
             */
            if (max >= 2 * (len - i - 1)) {
                return s.substring(start, end + 1);
            }
        }
        return result;
    }

    public int getPalindromicLength(String str, int i, int j) {
        int length = str.length();
        while (i >= 0 && j < length && str.charAt(i) == str.charAt(j)) {
            i--;
            j++;
        }
        return j - i - 1;
    }


    /*public boolean isPalindromic(String str, int i, int j) {
        int len = str.length();
        j = j - 1;
        while (i <= j) {
            if (str.charAt(i) == str.charAt(j)) {
                i++;
                j--;
                continue;
            }
            return false;
        }
        return true;
    }*/


}

