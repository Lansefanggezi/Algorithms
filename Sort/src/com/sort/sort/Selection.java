/*
 * 选择排序
 * 
 * */
package com.sort.sort;

import com.sort.sortInter.Sort;
public class Selection extends Sort {

	@Override
	public void sort(Comparable[] a) {
		
		//首先，找到数组中最小的那个元素将它和数组第一个元素交换位置
		//在剩下的元素中找到最小的，将它和数组第二个元素交换位置。
		//循环，直到整个数组排序完毕
		
		//不断地选择剩余元素之中的最小值
		for(int i = 0;i < a.length; i++)
		{
			int min = i;
			for(int j = i;j<a.length && super.less(a[j], a[min]);j++)
			{
				min = j;
			}
			super.exch(a, i, min);
		}
	}

}
