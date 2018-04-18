package com.sort.maopao;

public class BubbleSort {
	 public int[] bubbleSort(int[] A, int n) {
	        int length = A.length;
	        for(int i = 0; i<length; i++)
	        {
	        	for(int j = 0; j<length-i-1;j++)
	        	{
	        		if(A[j]<A[j+1])
	        		{
	        			int temp = A[j+1];
	        			A[j+1] = A[j];
	        			A[j] = temp;
	        		}
	        	}
	        }
	        
	        return A;
	    }
}
