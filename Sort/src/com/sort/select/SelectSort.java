package com.sort.select;

public class SelectSort {

	/**
	 * 
	 * ���ÿ�αȽ��궼�������Ǿ��ǽ����������ÿ��ѭ����Ž������Ǿ��Ǽ�ѡ������
	 * 
	 * */
	public static void selectSort(int[] array)
	{
		//�����������У�����С����������ǰ�档
		//����ʣ�µ����С�����С����������ǰ�档
		//�ظ��ڶ�����ֱ�����һ����
		
		int length = array.length;
		for(int i = 0; i<length; i++)
		{
			int min = array[i];
			int key = i;
			for(int j = i+1; j<length; j++)
			{
				if (min > array[j]) 
				{
					min = array[j];
					key = j;
				}
			}
			
			//����С����������ǰ��
			array[key] = array[i];
			array[i] = min;
		}
	}
	
}
