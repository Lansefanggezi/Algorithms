package com.sort.maopao;

public class Maopao {

	public static void maoPaoSrot(int[] array)
	{
		int length = array.length;
		for(int i = 0; i<length ; i++)
		{
			for(int j = 0 ; j < length - i - 1; j++)
			{
				if (array[j] > array[j+1]) 
				{
					int temp = array[j];
					array[j] = array[j+1];
					array[j+1] = temp;
				}
			}
		}
	}
}
