package com.sort.select;

public class SelectSort {
	
	public static void selectSort(int [] array)
	{
		int length = array.length;
		
		//�趨���������ѭ������,��һ��������Ҫ�Ƚ�
		for(int i = 1; i<length; i++)
		{
			//1.��ȡ��������ֵ���������е����һλ
			int inserValue = array[i];
			
			//ѭ���Ƚϲ�����������������ȵ�ǰ��С����ǰ������һλ
			int j = i-1;
			for(; j>=0&&array[j]>inserValue;j--)
			{
				array[j+1] = array[j];
			}
			array[j+1] = inserValue;
		}
	}
}
