package com.kang.lean;

public class FindStr {

	/**
	 * ��������һ���ַ�������һ���ַ����е�λ��
	 */
	public static void main(String[] args) {
		System.out.println(FindStr.findStr("Spring", "ng"));
	}

	public static int findStr(String S, String T) {
		// ָ���������Ƚϵ�λ��
		int i = 0;

		// ָ���ִ�,�ȽϵĴ���
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
