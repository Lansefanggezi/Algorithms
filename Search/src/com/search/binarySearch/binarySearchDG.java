package com.search.binarySearch;

import java.util.Arrays;
import java.util.Scanner;

public class binarySearchDG {

	public static int rank(int[] list, int left, int right, int key)
	{
		if(left > right) return -1;
		
		int mid = (left + right)/2;
		
		if (key < list[mid] ) return rank(list,left,mid,key );
		else if(key > list[mid]) return rank(list,mid, right, key);
		else return mid;
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
		System.out.println(binarySearchDG.rank(list, 0,size, key) + 1);
	}

}
