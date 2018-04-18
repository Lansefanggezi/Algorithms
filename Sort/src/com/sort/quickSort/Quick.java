package com.sort.quickSort;

public class Quick {

	public static void sort(int[] array)
	{
		int len = array.length;
		sort(array,0,len-1);
	}

	public static void sort(int[] array, int lo, int hi) {
		//���õݹ鷵������
		if(hi <=lo)
			return;
		
		//��һ������
		int mid = partition(array,lo,hi);
		
		//�ݹ����,mid-1�����ݲ���Ҫ�ٱȽ�
		sort(array, lo, mid-1);
		sort(array, mid+1, hi);
	}

	/**
	 * ����ĳ����mid��array[mid]һ���Ŷ�
	 * array[lo]~array[mid-1]������������array[mid]
	 * array[mid+1] ~ array[hi]��������С��array[mid]
	 * */
	public static int partition(int[] array, int lo, int hi) {
		
		//ȡ���з�Ԫ��
		int temp = array[lo];
		//��ָ��
		int j = lo+1;
		int i = hi;
		
		//��ָ�����ָ�벻����
		while(j<i)
		{
			//���������࿪ʼɨ���ҵ�һ�����ڵ����з�Ԫ�ص�Ԫ��
			while(temp>=array[j]&&j<i)
				j++;
			
			//�������Ҳ࿪ʼɨ���ҵ�һ��С�ڵ����з�Ԫ�ص�Ԫ��
			while(temp<=array[i]&&j<i)
				i--;
			
			//������Ԫ�ؽ���
			if(j<i)
			{
//				System.out.println("i:" + i +"----"+ array[i]+"----j:" + j +"----"+ array[j]);
				int a = array[i];
				array[i] = array[j];
				array[j] = a;
				j++;
				i--;
//				System.out.println("i:" + i +"----"+ array[i]+"----j:" + j +"----"+ array[j]);
			}
		}
		//������ָ�������������з�Ԫ�غ�����ָ��
		array[lo] = array[j];
		array[j] = temp;
		return j;
	}
}
