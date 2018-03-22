package com.Scaner.Main;

import java.util.*;
public class ScannerX {

	public static void main(String[] args) {
		
		Scanner s=new Scanner(System.in);
		
		System.out.println("请输入数组行数和列数");
		
		int x=s.nextInt();
		
		int y=s.nextInt();
		
		int [][]awarry=new int[x][y];//初始化数组
		
		System.out.println("请输入数组元素");
		
		for(int i=0;i<x;i++)//循环输入
			for(int j=0;j<y;j++)
				awarry[i][j]=s.nextInt();
		
		System.out.println("你输入的数组为");
		for(int i=0;i<x;i++){//循环输出
			for(int j=0;j<y;j++)
				System.out.print(awarry[i][j]+"\t");
			System.out.println();
		}
	}

}
