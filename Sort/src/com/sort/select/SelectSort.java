package com.sort.select;

public class SelectSort {

	/**
	 * 
	 * 如果每次比较完都交换，那就是交换排序。如果每次循环完才交换，那就是简单选择排序。
	 * 
	 * */
	public static void selectSort(int[] array)
	{
		//遍历整个序列，将最小的数放在最前面。
		//遍历剩下的序列。将最小的数放在最前面。
		//重复第二部，直到最后一个数
		
		int length = array.length;
		for(int i = 0; i<length; i++)
		{
			int min = array[i];
			int key = i;
			for(int j = i+1; j<length; j++)
			{
				if (min > array[j]) 
				{
					min = array[j];
					key = j;
				}
			}
			
			//将最小的数放在最前面
			array[key] = array[i];
			array[i] = min;
		}
	}
	
}
