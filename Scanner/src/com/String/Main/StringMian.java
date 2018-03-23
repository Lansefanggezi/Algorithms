package com.String.Main;

import java.util.Scanner;

public class StringMian {

	//检查一个字符串是不是回文
	public static boolean isPalindrome(String s)
	{
		int size = s.length();
		for(int i = 0; i < size/2; i++ )
		{
			if(s.charAt(i) != s.charAt(size - i -1)) return false;
		}
		
		return true;
	}
	
	//判断字符串是不是按照字母表顺序排列的
	public static boolean isSort(String s)
	{
		int size = s.length();
		for(int i = 1; i < size; i++)
			if(s.charAt(i - 1) > s.charAt(i)) return false;
		return true;
	}
	
	public static void main(String[] args) {

		Scanner read = new Scanner(System.in);
		System.out.println("请输入字符串：");
		String s = read.nextLine();
//		if(StringMian.isPalindrome(s)) System.out.println(s + "是回文。");
//		else System.out.println(s + "不是回文。");
		
		if(StringMian.isSort(s)) System.out.println(s + "是按照26字母表排列的。");
		else System.out.println(s + "不是按照26字母表排列的。");
		
	}

}
