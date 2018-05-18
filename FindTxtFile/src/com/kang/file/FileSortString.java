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
	 * ��ָ���ļ���testĿ¼�£��������е�txt�ļ����ļ��Ĵ�С��ȷ�������ݲ�ȷ������ȡ�ļ����ݣ����տո����ֵ���
	 * ������txt�ļ��е�Ӣ�ĵ��ʰ���a-z�����ִ�Сд������Կո�Ϊ���д��result.log
	 */
	public static void main(String[] args) {

		// �ݹ��ȡ�����ļ�����ȡ�ļ�����
		String fileUri = "G:\\FFOutput";
		File file = new File(fileUri);
		StringBuilder fileStr = new StringBuilder();
		ReadFile.findFileList(file, fileStr);
		
		//split ��list
		String[] listSplit = fileStr.toString().split(" ");

		// ��ȡӢ�ĵ���
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
