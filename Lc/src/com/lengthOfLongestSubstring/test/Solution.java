package com.lengthOfLongestSubstring.test;

import java.awt.datatransfer.StringSelection;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 *	示例 1:
 *	
 *	输入: "abcabcbb"
 *	输出: 3 
 *	解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 *
 * */
public class Solution {
	public static void main(String[] args) {
		
	}
	public static int lengthOfLongestSubstring(String s) {
		if (!(null != s && s.length() >0)) {
			return 0;
		}
		char [] strArray = s.toCharArray();
		Map charMap = null;
		int maxSize = 0;
		for(int i = 0;i<strArray.length; i++){
			charMap =  new HashMap<>();
			int size = 0;
			for(int j = i; j<strArray.length; j++){
				if (charMap.containsKey(strArray[j])) {
					break;
				}else{
					charMap.put(strArray[j],strArray[j]);
					size++;
				}
			}
			if (maxSize <size) {
				maxSize = size;
			}
		}
		
		return maxSize;
	}
}

