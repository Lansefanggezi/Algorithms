package com.kang.file;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

public class FileSortString {

	/*
	 * 在指定文件夹test目录下，遍历所有的txt文件，文件的大小不确定，内容不确定，读取文件内容，按照空格区分单词
	 * 把所有txt文件中的英文单词按照a-z不区分大小写排序后，以空格为间隔写入result.log
	 */
	public static void main(String[] args) {

		// 递归获取所有文件并提取文件内容
		String fileUri = "G:\\FFOutput";
		File file = new File(fileUri);
		StringBuilder fileStr = new StringBuilder();
		ReadFile.findFileList(file, fileStr);
		
		//split 用list
		String[] listSplit = fileStr.toString().split(" ");

		// 提取英文单词
		List<String> strEnglish = new ArrayList<String>();
		for (String string : listSplit) {
			
			if (StringUtils.isNotEmpty(string)) {
				System.out.println("+++"+string);
				if (((string.trim().charAt(0) < 'z') && (string.trim().charAt(0) > 'a'))
						|| ((string.trim().charAt(0) < 'Z') && (string.trim().charAt(0) > 'A'))) {
					strEnglish.add(string.trim());
					System.out.println("---"+string);
				}
			}
		}
		Collections.sort(strEnglish);
		System.out.println(strEnglish);
	}

}
