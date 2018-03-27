package com.UnionFind.QuickUnion;

import java.util.Scanner;
import java.util.Stack;

public class Evaluate {

	public static void evaluate(char [] s)
	{
		Stack<Character> options = new Stack<Character>();
		Stack<Double> values = new Stack<Double>();
		
		for(int i = 0; i < s.length; i++)
		{
			if(s[i] == '(') options.push(s[i]);
			else if(s[i] == '+') options.push(s[i]);
			else if(s[i] == '-') options.push(s[i]);
			else if(s[i] == '*') options.push(s[i]);
			else if(s[i] == '/') options.push(s[i]);
			else if(s[i] == ')')
			{
				double value = values.pop();
				char option = options.pop();
				if(option == '+') value = value + values.pop();
				else if(option == '-') value =  values.pop() - value;
				else if(option == '*') value = values.pop() * value;
				else if(option == '/') value = values.pop() / value;
				values.push(value);
			}
			else values.push(Double.valueOf(s[i]));
		}
		System.out.println("结果："+values.pop());
	}
	public static void main(String[] args) {
		
		Scanner read = new Scanner(System.in);
		System.out.println("请输入计算公式");
		String s = read.nextLine();
		Evaluate.evaluate(s.toCharArray());
	}

}
