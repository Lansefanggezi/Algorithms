package com.kang.lean;

public class LeanInt {

	public static void main(String[] args) {
		
		//F����15==1111  i�Ĳ���: 1111 1111 1111 1111 1111 1111 1111 0001
		//Դ��:1000 0000 0000 0000 0000 0000 0000 1111 ��λ���䣬��λȡ���ټ�һ
		int i = 0xFFFFFFF1;
		
		//������õ��ǶԶ��������ֱ�Ӽ���ȡ��
		//j�ı���������ǣ�0000 0000 0000 0000 0000 1110
		int j = ~i;
		System.out.println(i);
		System.out.println(j);
		System.out.println(Integer.parseInt("12"));
		System.out.println(Integer.valueOf("12").intValue());
	}
}
