package com.kang.file;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class ReadFile {
	public static StringBuilder findFileList(File fileUri, StringBuilder fileStr) {
		// �ж�·���Ƿ����
		if (fileUri.exists()) {
			// ��fileUri��Ŀ¼
			File[] fileList = fileUri.listFiles();
			for (File file : fileList) {
				if (file.isDirectory()) {
					findFileList(file, fileStr);
				} else {
					// ���ļ�ʱ�ж��Ƿ�ʱtxt�ļ�
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
