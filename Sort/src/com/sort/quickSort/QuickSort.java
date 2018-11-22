package com.sort.quickSort;

public class QuickSort {

	public static void quickSort(int[] array, int start,int end)
	{
		if (end <= start)
		{
			return;
		}
		
		//哨兵
		int temp = array[start];

		
		int left = start;
		int right = end;
		
		while(left < right){
			while(array[right] >= temp  && left<right)
			{
				right--;
			}
			
			while (array[left] <= temp && left<right)
			{
				left++;
			}
			//如果满足条件则交换
			if(left < right)
			{
				int x = array[left];
				array[left] = array[right];
				array[right] = x;
			}
		}
		
		//最后将基准为与i和j相等位置的数字交换
		array[start] = array[left];
		array[left] = temp;
		
		//分而治之
		quickSort(array,start,left-1);
		quickSort(array,left+1,end);
	}
}
