package com.sort.select;

public class SelectSort {
	
	public static void selectSort(int [] array)
	{
		int length = array.length;
		
		//设定插入此数即循环此数,第一个数不需要比较
		for(int i = 1; i<length; i++)
		{
			//1.获取插入数的值、有序数列的最后一位
			int inserValue = array[i];
			
			//循环比较插入数，如果插入数比当前数小。当前数后移一位
			int j = i-1;
			for(; j>=0&&array[j]>inserValue;j--)
			{
				array[j+1] = array[j];
			}
			array[j+1] = inserValue;
		}
	}
}
