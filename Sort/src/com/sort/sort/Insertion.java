/*
 * ��������
 * 
 * */
package com.sort.sort;

import com.sort.sortInter.Sort;

public class Insertion extends Sort {

	@Override
	public void sort(Comparable[] a) {
		
		//��ǰ������ߵ�Ԫ���������
		//�ڲ���֮ǰ��Ҫ����������Ԫ������һλ
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
