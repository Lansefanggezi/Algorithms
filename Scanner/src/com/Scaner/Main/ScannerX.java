package com.Scaner.Main;

import java.util.*;
public class ScannerX {

	public static void main(String[] args) {
		
		Scanner s=new Scanner(System.in);
		
		System.out.println("��������������������");
		
		int x=s.nextInt();
		
		int y=s.nextInt();
		
		int [][]awarry=new int[x][y];//��ʼ������
		
		System.out.println("����������Ԫ��");
		
		for(int i=0;i<x;i++)//ѭ������
			for(int j=0;j<y;j++)
				awarry[i][j]=s.nextInt();
		
		System.out.println("�����������Ϊ");
		for(int i=0;i<x;i++){//ѭ�����
			for(int j=0;j<y;j++)
				System.out.print(awarry[i][j]+"\t");
			System.out.println();
		}
	}

}
