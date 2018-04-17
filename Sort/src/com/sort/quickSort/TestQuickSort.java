package com.sort.quickSort;

import org.junit.Test;

public class TestQuickSort {

	@Test
	public void test() {
		int[] array = {2,4,1,6,4,8,9};
		QuickSort.quickSort(array,0,array.length);
		
		for(int i:array)
		{
			System.out.print(array[i]+ ",");
		}
	}

}
