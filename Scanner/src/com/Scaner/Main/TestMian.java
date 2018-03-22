package com.Scaner.Main;

import java.util.Scanner;

public class TestMian {

	public static void main(String[] args) {
		Scanner read =new Scanner(System.in);
		
		System.out.println("请输入数组行数和列数");
		
		String s= read.nextLine();
		
		String t= read.nextLine();
		
		int count = 0 ;
		for (int i = 0; i<s.length();i++)
		{
			for(int j = 0; j<t.length(); j++)
			{
				if((i + t.length()) <= s.length()){
				if(s.charAt(i + j) == t.charAt(j))
				{
					
				}else
				{
					count++;
				}
				}
			}
		}
		System.out.print(count);
	}

}
