package com.sort.shellSort;

public class ShellSort {
    public int[] shellSort(int[] A, int n) 
    {
    	int len = A.length;
    	int h = 1;
    	
    	//确定步长
    	while(h<len/3)
    		h= h*3+1;
    	
    	while(h>=1)
    	{
    		//从第一个布长开始
    		for(int i = h; i<len; i++)
    		{
    			//当从最后一个不畅开始比较？
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
