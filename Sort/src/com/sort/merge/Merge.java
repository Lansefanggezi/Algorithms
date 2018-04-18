package com.sort.merge;

public class Merge {

	public static int [] arrayCopy;
	
	public static void sort(int [] array)
	{
		//赋值备份
		arrayCopy = new int[array.length];
		sort(array,0,array.length-1);
		

	}
	
	public static void sort(int[] array,int low,int high)
	{
		//将数组array从low到higt排序
		if(high <=low)
		{
			return;
		}
		int mid = low + (high - low)/2;
		
		sort(array,low,mid);
		sort(array,mid+1,high);
		merge(array, low, mid, high);
	}
	
	/**
	 * @param array 数组
	 * @param low 低位
	 * @param high 高位
	 * @param mid 中
	 * 
	 * */
	public static void merge(int[] array,int low,int mid,int high)
	{
		//低位指针
		int i = low;
		
		//高位指针
		int j = mid+1;

		for(int k = 0; k<array.length; k++)
		{
			arrayCopy[k] = array[k];
		}
		//合并数组
		for(int k = low; k<=high; k++)
		{
			//低位数组用完
			if(i>mid)
				array[k] = arrayCopy[j++];
			
			//高位数组用完
			else if (j>high)
				array[k] = arrayCopy[i++];
			
			//低位大与高位
			else if(arrayCopy[j] > arrayCopy[i])
				array[k] = arrayCopy[i++];
			//上级意外
			else 
				array[k] = arrayCopy[j++];
			
		}
		
	}
}
