package com.kang.lean;

public class FindStr {

	/**
	 * 暴力查找一个字符串在另一个字符串中的位置
	 */
	public static void main(String[] args) {
		System.out.println(FindStr.findStr("Spring", "ng"));
	}

	public static int findStr(String S, String T) {
		// 指向主串，比较的位置
		int i = 0;

		// 指向字串,比较的次数
		int j = 0;
		while ((i < S.length()) && (j < T.length())) {
			if (S.charAt(i) == T.charAt(j)) {
				j++;
				i++;
			} else {
				j = 0;
				i = i - j + 1;
			}
		}
		if (j == T.length()) {
			return i - j + 1;
		} else {
			return -1;
		}
	}
}
