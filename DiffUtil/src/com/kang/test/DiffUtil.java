package com.kang.test;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.CRC32;

import org.apache.log4j.Logger;

/**
 * 
 * 1. ��A��B����Ŀ¼��Ŀ¼����λ�ü��㼶����ȷ�� 2.
 * ��Ҫ��BΪ��׼�ҳ�����Ŀ¼�������иĶ����ļ����ļ����������ӡ��޸ġ�ɾ���������иĶ����ļ����������Ŀ¼�У��㼶�ṹ��ԭĿ¼��ͬ 3.
 * �����������������Ϣ��¼��������־�ļ��� 4. ��ɾ����Ϣ������¼��ɾ����־�ļ��� 5. ÿ��ִ��diff������Ҫ����һ���µ�������������Ŀ¼����ļ�
 */

public class DiffUtil {

	public static void scanFileDirectory(File baseFile, File afterFile,
			File diffFileUrl, Logger logger) {

		// ��diffUrlĿ¼�£��������ڸ�ʽ���ļ��У����ڴ�ŸĶ����ļ�
		// ��ȡ��ǰ����
		String nowTime = createCurrentTime();

		// ����������ʽ���ļ�Ŀ¼
		File fileDire = new File(diffFileUrl, nowTime);
		fileDire.mkdirs();
		String diffFilePath = fileDire.getAbsolutePath();

		// ������ɾ������Ϣ������¼��ɾ����־�ļ���
		// ����ɾ����־�ļ�
		String rootPath = fileDire.getAbsolutePath();
		File deleteLogFile = new File(rootPath, "delete.log");
		try {
			deleteLogFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// ������ɾ������Ϣ
		HashMap<String, Long> sourceUrlMap = getAllFileMap(baseFile);
		HashMap<String, Long> currentUrlMap = getAllFileMap(afterFile);
		List<String> deleteFiles = new ArrayList<String>();

		long start = System.currentTimeMillis();
		logger.info(DiffUtilsTest.class.getName() + "��ʼDiff������1��" + baseFile
				+ "����2��" + afterFile + "����3��" + diffFileUrl);

		int delFilecount = 0;
		for (Map.Entry<String, Long> sourceEntry : sourceUrlMap.entrySet()) {
			// ����ԭ�汾Ŀ¼�µ��ļ���������ļ����ڵ�ǰ�汾Ŀ¼���棬�����ļ���ɾ����
			if (!currentUrlMap.containsKey(sourceEntry.getKey())) {
				deleteFiles.add(sourceEntry.getKey());
				logger.info("�ļ���ɾ����" + sourceEntry.getKey());
				delFilecount++;
			}
		}
		// ��ɾ����Ϣд��log�ļ�
		appendFileWriter(deleteLogFile, deleteFiles);

		// ������������¼�¼,�����뵽������
		List<String> addFiles = new ArrayList<String>();
		List<String> updateFiles = new ArrayList<String>();

		// �����޸Ļ��������ļ�
		File addOrUpdate = new File(rootPath, "updateAndAdd.log");
		try {
			addOrUpdate.createNewFile();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		/**
		 * addOrUpdateFileUrls��key-value�ֱ�ΪafterFile�ļ���ȫ·������ diffFileUrl�ļ���ȫ·����
		 * ͨ���ļ�����ȫ·�����Ի���ļ��������ں�����ļ����Ʋ���
		 */
		Map<String, String> addOrUpdateFileUrls = new HashMap<String, String>();
		int addCount = 0;
		int updateCount = 0;
		for (Map.Entry<String, Long> currentUrlMapEntry : currentUrlMap
				.entrySet()) {
			String currentUrlSuffix = currentUrlMapEntry.getKey();

			// ���Ԫ�ش���ԭ����Ŀ¼�£�����crc��ͬ�����Ǹ��²���
			if (sourceUrlMap.containsKey(currentUrlMapEntry.getKey())) {
				try {
					long sourceUrlCrc = sourceUrlMap.get(currentUrlMapEntry
							.getKey());
					long currentUrlCrc = currentUrlMapEntry.getValue();
					if (currentUrlCrc != sourceUrlCrc) {
						updateCount++;
						updateFiles.add(currentUrlSuffix);
						logger.info("�ļ������ı䣺" + currentUrlSuffix);
						addOrUpdateFileUrls.put(afterFile + currentUrlSuffix,
								diffFilePath + currentUrlSuffix);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

			} else {
				// ���Ԫ�ز�����AĿ¼�£�����������Ԫ��
				addCount++;
				addFiles.add(currentUrlSuffix);
				addOrUpdateFileUrls.put(afterFile + currentUrlSuffix,
						diffFilePath + currentUrlSuffix);
				logger.info("�����ļ���" + currentUrlSuffix);
			}
		}
		// ���޸Ļ��������ļ�д��log�ļ�
		// log��Ϣ����
		addFiles.add(0, "-------- �������ļ�" + addCount + "�� --------");
		updateFiles.add(0, "-------- �������ļ�" + updateCount + "�� --------");
		addFiles.addAll(updateFiles);
		long end = System.currentTimeMillis();

		addFiles.add("--------  ������ϣ���ʱ��" + (end - start) + "ms");
		appendFileWriter(addOrUpdate, addFiles);

		// ����仯���
		logger.info("-------- �������ļ���" + updateCount + "��" + "������" + addCount
				+ "��" + "ɾ����" + delFilecount + "��");

		// ��������/�޸ĵ��ļ�����changeĿ¼��
		for (Map.Entry<String, String> addOrUpdateEntry : addOrUpdateFileUrls
				.entrySet()) {
			String filePath = addOrUpdateEntry.getValue();
			File diffFile = new File(addOrUpdateEntry.getValue());
			String fileDirs = filePath.replace(diffFile.getName(), "");
			File creFileDir = new File(fileDirs);
			creFileDir.mkdirs();
			FileChannel inputChannel = null;
			FileChannel outputChannel = null;
			FileInputStream fileInputStream = null;
			FileOutputStream fileOutputStream = null;
			try {
				fileInputStream = new FileInputStream(new File(
						addOrUpdateEntry.getKey()));
				inputChannel = fileInputStream.getChannel();
				fileOutputStream = new FileOutputStream(diffFile);
				outputChannel = fileOutputStream.getChannel();
				outputChannel
						.transferFrom(inputChannel, 0, inputChannel.size());
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					fileInputStream.close();
					inputChannel.close();
					fileOutputStream.close();
					outputChannel.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * ��List�е���Ϣд���Ӧ��log�ļ�
	 * 
	 * @param file
	 * @param value
	 * @return
	 */
	private static void appendFileWriter(File file, List<String> value) {
		// ��һ���ļ�д����,��׷�ӵ���ʽд��
		try {
			FileWriter writer = new FileWriter(file, true);
			for (String fileName : value) {
				writer.write(fileName);
				writer.write("\r\n");
			}
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * ��ȡyyyy��MM��dd��HH��mm��ss���ʽ�ĵ�ǰʱ��
	 * 
	 * @return
	 */
	private static String createCurrentTime() {
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy��MM��dd��HH��mm��ss��");
		String nowTime = dateFormat.format(now);
		return nowTime;
	}

	/**
	 * ����ȡ�����ļ��浽map��
	 * 
	 * @param path
	 * @return
	 */
	private static HashMap<String, Long> getAllFileMap(File DirectoryUrl) {
		// �ݴ������ļ�
		List<File> allFileList = new ArrayList<File>();

		// ����Map���ļ������·����CRC��Ϣ
		HashMap<String, Long> resMap = new HashMap<String, Long>();

		allFileList = getAllFile(DirectoryUrl, allFileList);
		for (File file : allFileList) {
			// key-value:�ļ������·��-�ļ���CRC��Ϣ
			resMap.put(
					file.getAbsolutePath().replace(
							DirectoryUrl.getAbsolutePath(), ""),
					getFileCRC(file));
		}
		return resMap;
	}

	/**
	 * �ݹ���������ļ�
	 * 
	 * @param file
	 * @param allFileList
	 * @return
	 */
	private static List<File> getAllFile(File file, List<File> allFileList) {
		// �ж��ļ��Ƿ����
		if (file.exists()) {
			// �ж��Ƿ���Ŀ¼���ǣ���ȡ�����ļ����������List;
			if (file.isDirectory()) {
				File f[] = file.listFiles();
				for (File tempFile : f) {
					getAllFile(tempFile, allFileList);
				}
			} else {
				allFileList.add(file);
			}
		}
		return allFileList;
	}

	/**
	 * ��ȡ�ļ���CRC
	 * 
	 */
	private static long getFileCRC(File file) {
		BufferedInputStream bsrc = null;
		CRC32 crc = new CRC32();
		try {
			bsrc = new BufferedInputStream(new FileInputStream(file));
			byte[] bytes = new byte[1024];
			int i;
			while ((i = bsrc.read(bytes)) != -1) {
				crc.update(bytes, 0, i);
			}
		} catch (Exception e) {

		} finally {
			if (bsrc != null) {
				try {
					bsrc.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return crc.getValue();
	}
}