package com.kang.test;

import java.io.File;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;

public class DiffUtilsTest {
	
	private static Logger logger = Logger.getLogger(DiffUtilsTest.class);
	
    public static void main(String [] args){
    	//判断参数个数
		if(args.length<3)
		{
			System.out.println("参数不全，使用方式 java -jar DifUtil 原目录路径    新目录路径    diff目录路径");
			return;
		}
		
    	//基准目录文件
		String oldUrlStr = args[0].replace("\\", "\\\\");
    	File oldUrl = new File(oldUrlStr);
    	
    	//新文件目录
		String newUrlStr = args[1].replace("\\", "\\\\");
    	File newUrl = new File(newUrlStr);
    	
    	//输出change目录
		String diffUrlStr = args[2].replace("\\", "\\\\");
    	File diffUrl = new File(diffUrlStr);
    	DiffUtil.scanFileDirectory(oldUrl, newUrl, diffUrl,logger);
    }
}