package com.kang.test;

import java.io.File;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;

public class DiffUtilsTest {
	
	private static Logger logger = Logger.getLogger(DiffUtilsTest.class);
	
    public static void main(String [] args){
    	//�жϲ�������
		if(args.length<3)
		{
			System.out.println("������ȫ��ʹ�÷�ʽ java -jar DifUtil ԭĿ¼·��    ��Ŀ¼·��    diffĿ¼·��");
			return;
		}
		
    	//��׼Ŀ¼�ļ�
		String oldUrlStr = args[0].replace("\\", "\\\\");
    	File oldUrl = new File(oldUrlStr);
    	
    	//���ļ�Ŀ¼
		String newUrlStr = args[1].replace("\\", "\\\\");
    	File newUrl = new File(newUrlStr);
    	
    	//���changeĿ¼
		String diffUrlStr = args[2].replace("\\", "\\\\");
    	File diffUrl = new File(diffUrlStr);
    	DiffUtil.scanFileDirectory(oldUrl, newUrl, diffUrl,logger);
    }
}