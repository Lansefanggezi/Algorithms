package com.sort.insert;

public class InsertionSort {
    public int[] insertionSort(int[] A, int n) {
    	
    	int length = A.length;
    	for(int i = 1; i<length; i++)
    	{
    		int j = i-1;
    		int position = A[i];
    		for(; (j>=0 )&& (position<A[j]);j--)
    		{
    			A[j+1] = A[j];
    		}
    		A[j+1] = position;
    	}
    	
    	return A;
    }
}
