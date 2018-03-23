package com.search.binarySearch;

import java.util.Arrays;
import java.util.Scanner;

public class BiearySearch {

	//二分法查询
	public static int rank(int key, int[] a)
	{
		//左边下标
		int left = 0;
		
		//右边下标
		int right = a.length;
		while(left <= right)
		{
			//TODO
			int mid = (left + right)/2;
			
			if(key < a[mid]) right = mid;
			else if(key > a[mid]) left = mid;
			else return mid;
		}
		return -1;
	}
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner read = new Scanner(System.in);
		System.out.println("请输入数组大小：");
		int size = read.nextInt();
		int [] list = new int[size];
		for(int i = 0; i< size; i++)
		{
			list[i] = read.nextInt();	
		}
		Arrays.sort(list);
		for(int i = 0; i< size; i++)
		{
			System.out.println(list[i]);	
		}
		System.out.println("输入key：");
		int key = read.nextInt();
		System.out.println(BiearySearch.rank(key, list) + 1);
	}

}
