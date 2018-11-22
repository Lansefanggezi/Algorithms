package com.sort.quickSort;

public class QuickSort {

	public static void quickSort(int[] array, int start,int end)
	{
		if (end <= start)
		{
			return;
		}
		
		//�ڱ�
		int temp = array[start];

		
		int left = start;
		int right = end;
		
		while(left < right){
			while(array[right] >= temp  && left<right)
			{
				right--;
			}
			
			while (array[left] <= temp && left<right)
			{
				left++;
			}
			//������������򽻻�
			if(left < right)
			{
				int x = array[left];
				array[left] = array[right];
				array[right] = x;
			}
		}
		
		//��󽫻�׼Ϊ��i��j���λ�õ����ֽ���
		array[start] = array[left];
		array[left] = temp;
		
		//�ֶ���֮
		quickSort(array,start,left-1);
		quickSort(array,left+1,end);
	}
}
