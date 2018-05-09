package com.kang.test;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileTest {

	private static Logger logger = LoggerFactory.getLogger(FileTest.class);

	public static void main(String[] args) {

		// 参数个数判断
		if (args.length < 3) {
			System.out
					.println("参数不全，使用方式 java -jar DifUtil 原目录路径    新目录路径    diff目录路径");
			return;
		}

		// 基准目录文件
		File baseUrl = new File(args[0]);

		// 新文件目录
		File newUrl = new File(args[1]);

		// 输出change目录
		File diffFileUrl = new File(args[2]);

		// String oldUrlStr = "E:\\soft\\diff工具需求说明及示例\\A";
		// String newUrlStr = "E:\\soft\\diff工具需求说明及示例\\B";
		// String diffUrlStr = "E:\\soft\\diff工具需求说明及示例\\change";
		//
		// File baseFile = new File(oldUrlStr);
		// File newUrl = new File(newUrlStr);
		// File diffFileUrl = new File(diffUrlStr);
		//

		logger.info("第一个参数是：" + baseUrl + "	第二个参数" + newUrl + "		第三个参数"
				+ diffFileUrl);

		DiffUtil.scanFileDirectory(baseUrl, newUrl, diffFileUrl, logger);
	}
}
