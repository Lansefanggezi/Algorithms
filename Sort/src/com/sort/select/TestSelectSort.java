package com.sort.select;

import org.junit.Test;

public class TestSelectSort {

	@Test
	public void test() {
		int[] array = {2,4,1,6,4,8,9};
		SelectSort.selectSort(array);
		
		for(int i = 0; i<array.length; i++)
		{
			System.out.print(array[i]+ ",");
		}
	}

}
