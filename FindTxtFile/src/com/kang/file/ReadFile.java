package com.kang.file;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class ReadFile {
	public static StringBuilder findFileList(File fileUri, StringBuilder fileStr) {
		// 判断路径是否存在
		if (fileUri.exists()) {
			// 当fileUri是目录
			File[] fileList = fileUri.listFiles();
			for (File file : fileList) {
				if (file.isDirectory()) {
					findFileList(file, fileStr);
				} else {
					// 是文件时判断是否时txt文件
					if (file.getName().indexOf("txt") > -1) {
						// fileUri.
						try {
							fileStr.append(" ");
							fileStr.append(FileUtils.readFileToString(file));
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}

		}
		return fileStr;
	}
}
