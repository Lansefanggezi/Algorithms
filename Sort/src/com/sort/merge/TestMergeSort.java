package com.sort.merge;

import org.junit.Test;

public class TestMergeSort {

	@Test
	public void test() {
		int[] array = {2,4,5,1,9,6,7};
		Merge.sort(array);
		
		for(int i = 0; i<array.length; i++)
		{
			System.out.print(array[i]+ ",");
		}
	}

}
