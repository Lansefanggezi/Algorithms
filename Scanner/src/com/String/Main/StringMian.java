package com.String.Main;

import java.util.Scanner;

public class StringMian {

	//���һ���ַ����ǲ��ǻ���
	public static boolean isPalindrome(String s)
	{
		int size = s.length();
		for(int i = 0; i < size/2; i++ )
		{
			if(s.charAt(i) != s.charAt(size - i -1)) return false;
		}
		
		return true;
	}
	
	//�ж��ַ����ǲ��ǰ�����ĸ��˳�����е�
	public static boolean isSort(String s)
	{
		int size = s.length();
		for(int i = 1; i < size; i++)
			if(s.charAt(i - 1) > s.charAt(i)) return false;
		return true;
	}
	
	public static void main(String[] args) {

		Scanner read = new Scanner(System.in);
		System.out.println("�������ַ�����");
		String s = read.nextLine();
//		if(StringMian.isPalindrome(s)) System.out.println(s + "�ǻ��ġ�");
//		else System.out.println(s + "���ǻ��ġ�");
		
		if(StringMian.isSort(s)) System.out.println(s + "�ǰ���26��ĸ�����еġ�");
		else System.out.println(s + "���ǰ���26��ĸ�����еġ�");
		
	}

}
