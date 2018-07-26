package com.kang.lean;

public class LeanInt {

	public static void main(String[] args) {
		
		//F代表15==1111  i的补码: 1111 1111 1111 1111 1111 1111 1111 0001
		//源码:1000 0000 0000 0000 0000 0000 0000 1111 首位不变，各位取反再加一
		int i = 0xFFFFFFF1;
		
		//这里采用的是对二进制码的直接计算取返
		//j的保存二进制是：0000 0000 0000 0000 0000 1110
		int j = ~i;
		System.out.println(i);
		System.out.println(j);
		System.out.println(Integer.parseInt("12"));
		System.out.println(Integer.valueOf("12").intValue());
	}
}
