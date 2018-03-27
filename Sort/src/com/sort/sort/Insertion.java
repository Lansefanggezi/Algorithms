/*
 * 插入排序
 * 
 * */
package com.sort.sort;

import com.sort.sortInter.Sort;

public class Insertion extends Sort {

	@Override
	public void sort(Comparable[] a) {
		
		//当前索引左边的元素是有序的
		//在插入之前需要把所有有序元素右移一位
		int N = a.length;
		for(int i = 0; i< N; i++)
		{
			for(int j = i; j>0 ; j--)
			{
				if(super.less(a[j], a[j-1]))
					super.exch(a, j, j-1);
			}
		}
		

	}

}
