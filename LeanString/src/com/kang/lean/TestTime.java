package com.kang.lean;

import java.text.DecimalFormat;

public class TestTime {

	public static void main(String [] args){
		
		DecimalFormat dFormat = new DecimalFormat("#,###,###,###,##0.00");
		double zero = 0.00;
		int zero1 = 0;
		if (Double.valueOf("0.00") == 0) {
			System.out.println("11111111");
		}else{
			System.out.println("2222222222222");
		}
		System.out.println(dFormat.format(Double.valueOf("1")));
	}
}
