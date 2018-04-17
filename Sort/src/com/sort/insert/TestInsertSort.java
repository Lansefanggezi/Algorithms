package com.sort.insert;

import org.junit.Test;

public class TestInsertSort {

	@Test
	public void test() {
		int[] array = {2,4,1,6,4,8,9};
		InsertSort.selectSort(array);
		
		for(int i = 0; i<array.length; i++)
		{
			System.out.print(array[i]+ ",");
		}
	}

}
