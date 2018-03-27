/*
 * 希尔排序
 * */
package com.sort.sort;

import com.sort.sortInter.Sort;

public class Shell extends Sort {

	@Override
	public void sort(Comparable[] a) {
		
		// 把数组按照升序排序
		int N = a.length;
		int h = 1;
		
		//使用缩小增量
		while(h < N/3)
			h = 3*h + 1;
		
		while(h>=1)
		{
			//将数组变为h有序
			
			//从第一个h元素开始递增
			for(int i = h; i <N; i++)
			{
				//从0元素开始
				for(int j = i;j >= h;j -= h)
				{
					if(less(a[j],a[j-h]))
						exch(a,j,j-h);
				}
			}
		}

	}

}
