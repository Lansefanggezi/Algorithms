package com.sort.shellSort;

public class ShellSort {
    public int[] shellSort(int[] A, int n) 
    {
    	int len = A.length;
    	int h = 1;
    	
    	//ȷ������
    	while(h<len/3)
    		h= h*3+1;
    	
    	while(h>=1)
    	{
    		//�ӵ�һ��������ʼ
    		for(int i = h; i<len; i++)
    		{
    			//�������һ��������ʼ�Ƚϣ�
    			for(int j = i;(j>=h)&&(A[j]<A[j-h]);j-=h)
    			{
    				int temp = A[j-h];
    				A[j-h] = A[j];
    				A[j] = temp;
    			}
    		}
    		
    		h= h/3;
    	}
    	return A;
    }
}
