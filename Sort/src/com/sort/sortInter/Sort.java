package com.sort.sortInter;

public abstract class Sort {
	
	//排序具体实现
	abstract public void sort(Comparable[] a);
	
	//比较v比w小的情况下返回true
	protected static boolean less(Comparable v, Comparable w)
	{
		return v.compareTo(w)<0;
	}
	
	//交换位置
	protected static void exch(Comparable[] a, int i, int j)
	{
		Comparable mid = a[i];
		a[i] = a[j];
		a[j] = mid;
	}
	
	//判断数组元素是否有序
	protected static boolean isSrot(Comparable [] a)
	{
		for(int i = 1; i < a.length; i++)
		{
			if(less(a[i],a[i-1]) ) return false;
		}
		return true;
	}
}
