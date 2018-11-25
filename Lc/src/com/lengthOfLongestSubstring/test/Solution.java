package com.lengthOfLongestSubstring.test;

import java.awt.datatransfer.StringSelection;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * ����һ���ַ����������ҳ����в������ظ��ַ��� ��Ӵ� �ĳ��ȡ�
 *
 *	ʾ�� 1:
 *	
 *	����: "abcabcbb"
 *	���: 3 
 *	����: ��Ϊ���ظ��ַ�����Ӵ��� "abc"�������䳤��Ϊ 3��
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

