package com.kang.test;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileTest {

	private static Logger logger = LoggerFactory.getLogger(FileTest.class);

	public static void main(String[] args) {

		// ���������ж�
		if (args.length < 3) {
			System.out
					.println("������ȫ��ʹ�÷�ʽ java -jar DifUtil ԭĿ¼·��    ��Ŀ¼·��    diffĿ¼·��");
			return;
		}

		// ��׼Ŀ¼�ļ�
		File baseUrl = new File(args[0]);

		// ���ļ�Ŀ¼
		File newUrl = new File(args[1]);

		// ���changeĿ¼
		File diffFileUrl = new File(args[2]);

		// String oldUrlStr = "E:\\soft\\diff��������˵����ʾ��\\A";
		// String newUrlStr = "E:\\soft\\diff��������˵����ʾ��\\B";
		// String diffUrlStr = "E:\\soft\\diff��������˵����ʾ��\\change";
		//
		// File baseFile = new File(oldUrlStr);
		// File newUrl = new File(newUrlStr);
		// File diffFileUrl = new File(diffUrlStr);
		//

		logger.info("��һ�������ǣ�" + baseUrl + "	�ڶ�������" + newUrl + "		����������"
				+ diffFileUrl);

		DiffUtil.scanFileDirectory(baseUrl, newUrl, diffFileUrl, logger);
	}
}
