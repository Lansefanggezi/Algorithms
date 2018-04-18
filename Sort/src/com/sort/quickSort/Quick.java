package com.sort.quickSort;

public class Quick {

	public static void sort(int[] array)
	{
		int len = array.length;
		sort(array,0,len-1);
	}

	public static void sort(int[] array, int lo, int hi) {
		//设置递归返回条件
		if(hi <=lo)
			return;
		
		//第一遍排序
		int mid = partition(array,lo,hi);
		
		//递归调用,mid-1的数据不需要再比较
		sort(array, lo, mid-1);
		sort(array, mid+1, hi);
	}

	/**
	 * 对于某个数mid，array[mid]一定排定
	 * array[lo]~array[mid-1]的数都不大于array[mid]
	 * array[mid+1] ~ array[hi]的数都不小于array[mid]
	 * */
	public static int partition(int[] array, int lo, int hi) {
		
		//取定切分元素
		int temp = array[lo];
		//左指针
		int j = lo+1;
		int i = hi;
		
		//左指针和右指针不相遇
		while(j<i)
		{
			//从数组的左侧开始扫描找到一个大于等于切分元素的元素
			while(temp>=array[j]&&j<i)
				j++;
			
			//从数组右侧开始扫描找到一个小于等于切分元素的元素
			while(temp<=array[i]&&j<i)
				i--;
			
			//将两个元素交换
			if(j<i)
			{
//				System.out.println("i:" + i +"----"+ array[i]+"----j:" + j +"----"+ array[j]);
				int a = array[i];
				array[i] = array[j];
				array[j] = a;
				j++;
				i--;
//				System.out.println("i:" + i +"----"+ array[i]+"----j:" + j +"----"+ array[j]);
			}
		}
		//当左右指针相遇，交换切分元素和相遇指针
		array[lo] = array[j];
		array[j] = temp;
		return j;
	}
}
