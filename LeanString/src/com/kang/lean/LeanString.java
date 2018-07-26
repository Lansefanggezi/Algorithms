package com.kang.lean;

public class LeanString {

	public static void main(String[] args) {
		String good = "Good";
		char[] ch = new char[]{'a','b','c'};
		new LeanString().change(good, ch);
		System.out.println(good + "and" + ch[0]+ch[1]+ch[2]);
		
		//String对象是不能被改变的
	}
	public void change(String str, char ch[]){
		str = "test ok";
		ch[0] = 'g';
	}
}
