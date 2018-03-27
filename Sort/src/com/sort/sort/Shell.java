/*
 * ϣ������
 * */
package com.sort.sort;

import com.sort.sortInter.Sort;

public class Shell extends Sort {

	@Override
	public void sort(Comparable[] a) {
		
		// �����鰴����������
		int N = a.length;
		int h = 1;
		
		//ʹ����С����
		while(h < N/3)
			h = 3*h + 1;
		
		while(h>=1)
		{
			//�������Ϊh����
			
			//�ӵ�һ��hԪ�ؿ�ʼ����
			for(int i = h; i <N; i++)
			{
				//��0Ԫ�ؿ�ʼ
				for(int j = i;j >= h;j -= h)
				{
					if(less(a[j],a[j-h]))
						exch(a,j,j-h);
				}
			}
		}

	}

}
