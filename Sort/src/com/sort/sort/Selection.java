/*
 * ѡ������
 * 
 * */
package com.sort.sort;

import com.sort.sortInter.Sort;
public class Selection extends Sort {

	@Override
	public void sort(Comparable[] a) {
		
		//���ȣ��ҵ���������С���Ǹ�Ԫ�ؽ����������һ��Ԫ�ؽ���λ��
		//��ʣ�µ�Ԫ�����ҵ���С�ģ�����������ڶ���Ԫ�ؽ���λ�á�
		//ѭ����ֱ�����������������
		
		//���ϵ�ѡ��ʣ��Ԫ��֮�е���Сֵ
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
