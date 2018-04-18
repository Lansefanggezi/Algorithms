package com.sort.merge;

public class MergeBU {

	public static int [] arrayCopy;
	
	public static void sort(int [] array)
	{
		//��ֵ����
		arrayCopy = new int[array.length];
		int len = array.length;
		//size��������Ĵ�С
		//�ж�������������Ӧ��С���ܳ���
		//ָ������1��2��4��8��16
		for(int size = 1; size<len;size = size+size)
		{
			//lo ��ʼ�ϲ�λ��
			//���һ������Ĵ�С��ֻ���������С��size��ż������ʱ����ܵ���size������һ����sizeС
			//��һ��������Ŀ�ʼλ�� lo += size + size
			for(int lo = 0; lo < len - size; lo += size + size )
			{
				//���һ����������λ��lo+size+size-1 �ֿ��ܳ��������length
//				merge(array, lo, lo+size-1, lo+size+size-1);
				merge(array, lo, lo+size-1, Math.min(lo+size+size-1, len-1));
			}
		}

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

		//����ֵ
		for(int k = low;k<=high;k++)
		{
			arrayCopy[k] = array[k];
		}
		
		//�ϲ�����
		for(int k = low;k<=high;k++)
		{
			//��λ��������
			if(i>mid)
				array[k] = arrayCopy[j++];
			
			//��λ��������
			else if(j>high)
				array[k] = arrayCopy[i++];
			
			//��λ�����λ
			else if(arrayCopy[i] > arrayCopy[j])
				array[k] = arrayCopy[j++];
			//�ϼ�����
			else
				array[k] = arrayCopy[i++];
		}
		
	}
}
