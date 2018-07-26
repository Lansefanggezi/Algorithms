package com.kang.lean;

public class abc {

	public static void main(String[] args) {
		int s = 0;
		int a[]  = {10,20,30,40,50,60,70,80,90};
		for(int x:a){
			if (x%3 == 0) {
				s+=x;
			}
		}
		System.out.println(s);
	}

}
