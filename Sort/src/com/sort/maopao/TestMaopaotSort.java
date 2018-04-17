package com.sort.maopao;

import org.junit.Test;

public class TestMaopaotSort {

	@Test
	public void test() {
		int[] array = {2,4,1,6,4,8,9};
		Maopao.maoPaoSrot(array);
		
		for(int i = 0; i<array.length; i++)
		{
			System.out.print(array[i]+ ",");
		}
	}

}
