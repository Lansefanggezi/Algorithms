package com.sort.select;

public class SelectionSort {
    public int[] selectionSort(int[] A, int n) {

    	int length = A.length;
    	for(int i = 0; i<length; i++)
    	{
    		int min = A[i];
    		int key = i;
    		for(int j = i+1; j<length; j++)
    		{
    			if(min > A[j])
    			{
    				min = A[j];
    				key = j;
    			}
    		}
			
			A[key] = A[i];
			A[i] = min;
    	}
    	return A;
    }
}
