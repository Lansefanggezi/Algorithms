package com.sort.sortInter;

public abstract class Sort {
	
	//�������ʵ��
	abstract public void sort(Comparable[] a);
	
	//�Ƚ�v��wС������·���true
	protected static boolean less(Comparable v, Comparable w)
	{
		return v.compareTo(w)<0;
	}
	
	//����λ��
	protected static void exch(Comparable[] a, int i, int j)
	{
		Comparable mid = a[i];
		a[i] = a[j];
		a[j] = mid;
	}
	
	//�ж�����Ԫ���Ƿ�����
	protected static boolean isSrot(Comparable [] a)
	{
		for(int i = 1; i < a.length; i++)
		{
			if(less(a[i],a[i-1]) ) return false;
		}
		return true;
	}
}
