package com.sort.merge;

public class Merge {

	public static int [] arrayCopy;
	
	public static void sort(int [] array)
	{
		//��ֵ����
		arrayCopy = new int[array.length];
		sort(array,0,array.length-1);
		

	}
	
	public static void sort(int[] array,int low,int high)
	{
		//������array��low��higt����
		if(high <=low)
		{
			return;
		}
		int mid = low + (high - low)/2;
		
		sort(array,low,mid);
		sort(array,mid+1,high);
		merge(array, low, mid, high);
	}
	
	/**
	 * @param array ����
	 * @param low ��λ
	 * @param high ��λ
	 * @param mid ��
	 * 
	 * */
	public static void merge(int[] array,int low,int mid,int high)
	{
		//��λָ��
		int i = low;
		
		//��λָ��
		int j = mid+1;

		for(int k = 0; k<array.length; k++)
		{
			arrayCopy[k] = array[k];
		}
		//�ϲ�����
		for(int k = low; k<=high; k++)
		{
			//��λ��������
			if(i>mid)
				array[k] = arrayCopy[j++];
			
			//��λ��������
			else if (j>high)
				array[k] = arrayCopy[i++];
			
			//��λ�����λ
			else if(arrayCopy[j] > arrayCopy[i])
				array[k] = arrayCopy[i++];
			//�ϼ�����
			else 
				array[k] = arrayCopy[j++];
			
		}
		
	}
}
