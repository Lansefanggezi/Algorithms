package com.sort.merge;

public class MergeBU {

	public static int [] arrayCopy;
	
	public static void sort(int [] array)
	{
		//赋值备份
		arrayCopy = new int[array.length];
		int len = array.length;
		//size是子数组的大小
		//判断条件：子数组应该小于总长度
		//指数增长1、2、4、8、16
		for(int size = 1; size<len;size = size+size)
		{
			//lo 开始合并位置
			//最后一个数组的大小，只有再数组大小是size的偶数倍的时候才能等于size，否则一定比size小
			//下一个子数组的开始位置 lo += size + size
			for(int lo = 0; lo < len - size; lo += size + size )
			{
				//最后一个数组的最高位当lo+size+size-1 又可能超出数组的length
//				merge(array, lo, lo+size-1, lo+size+size-1);
				merge(array, lo, lo+size-1, Math.min(lo+size+size-1, len-1));
			}
		}

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

		//保存值
		for(int k = low;k<=high;k++)
		{
			arrayCopy[k] = array[k];
		}
		
		//合并数组
		for(int k = low;k<=high;k++)
		{
			//低位数组用完
			if(i>mid)
				array[k] = arrayCopy[j++];
			
			//高位数组用完
			else if(j>high)
				array[k] = arrayCopy[i++];
			
			//低位大与高位
			else if(arrayCopy[i] > arrayCopy[j])
				array[k] = arrayCopy[j++];
			//上记以外
			else
				array[k] = arrayCopy[i++];
		}
		
	}
}
