package com.kang.test;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;

import com.kang.constStr.Const;

/**
 * 
 * 1. ��A��B����Ŀ¼��Ŀ¼����λ�ü��㼶����ȷ�� 2.
 * ��Ҫ��BΪ��׼�ҳ�����Ŀ¼�������иĶ����ļ����ļ����������ӡ��޸ġ�ɾ���������иĶ����ļ����������Ŀ¼�У��㼶�ṹ��ԭĿ¼��ͬ 3.
 * �����������������Ϣ��¼��������־�ļ��� 4. ��ɾ����Ϣ������¼��ɾ����־�ļ��� 5. ÿ��ִ��diff������Ҫ����һ���µ�������������Ŀ¼����ļ�
 */
public class DiffUtil {

	public static void scanFileDirectory(File baseUrl, File afterUrl,
			File diffFileUrl, Logger logger) {

		// ������ɾ������Ϣ
		// �޸�ǰ�ļ������·����CRCֵ
		HashMap<String, Long> baseFileMap = new HashMap<String, Long>();

		// �޸ĺ��ļ������·����CRCֵ
		HashMap<String, Long> afterFileMap = new HashMap<String, Long>();
		
		// ��diffUrlĿ¼�£��������ڸ�ʽ���ļ��У����ڴ�ŸĶ����ļ�
		// ��ȡ��ǰ����
		String nowTime = createCurrentTime();

		// ����������ʽ���ļ�Ŀ¼
		File fileDire = new File(diffFileUrl.getAbsoluteFile(), nowTime);
		fileDire.mkdirs();
		String diffFilePath = fileDire.getAbsolutePath();

		// ������ɾ������Ϣ������¼��ɾ����־�ļ���
		// ����ɾ����־�ļ�
		String rootPath = fileDire.getAbsolutePath();
		File deleteLogFile = new File(rootPath, Const.DELETELOG);
		try {
			deleteLogFile.createNewFile();
		} catch (IOException e2) {
			logger.error(Const.ERRORCREATE, e2);
		}



		try {
			getAllFileMap(baseUrl, baseUrl.getAbsolutePath(), baseFileMap);
			getAllFileMap(afterUrl, afterUrl.getAbsolutePath(), afterFileMap);
		} catch (IOException e1) {
			logger.error(Const.ERROECRC, e1);
		}

		// ��ɾ�����ļ�
		List<String> deleteFiles = new ArrayList<String>();

		// ��ǿ�ʼʱ��
		long start = System.currentTimeMillis();

		// ����ԭ��Ŀ¼��������������뵱ǰĿ¼�����Ǳ�ɾ����
		int delFilecount = 0;
		for (Map.Entry<String, Long> baseFileEntry : baseFileMap.entrySet()) {
			// ������������뵱ǰĿ¼�����Ǳ�ɾ����
			if (!afterFileMap.containsKey(baseFileEntry.getKey())) {
				delFilecount++;
				deleteFiles.add(baseFileEntry.getKey());
				logger.info("���h���ļ�" + baseFileEntry.getKey());
			}
		}

		// ��ɾ����Ϣд��log�ļ�
		try {
			FileUtils.writeLines(deleteLogFile, deleteFiles);
		} catch (IOException e1) {
			logger.error(Const.ERRORFILEWRITE, e1);
		}

		// ������������¼�¼,�����뵽������
		List<String> addFiles = new ArrayList<String>();
		List<String> updateFiles = new ArrayList<String>();

		// �����޸Ļ��������ļ�
		File addOrUpdate = new File(rootPath, Const.ADDANDUPDATE);
		try {
			addOrUpdate.createNewFile();
		} catch (IOException e) {
			logger.error(Const.ERRORCREATE, e);
		}

		// addOrUpdateFileUrls��key-value�ֱ�ΪafterFile�ļ���ȫ·������diffFileUrl�ļ���ȫ·����
		// ͨ���ļ�����ȫ·�����Ի���ļ��������ں�����ļ����Ʋ���
		Map<String, String> addOrUpdateFileUrls = new HashMap<String, String>();
		int addCount = 0;
		int updateCount = 0;
		for (Map.Entry<String, Long> currentUrlMapEntry : afterFileMap
				.entrySet()) {
			String currentUrlSuffix = currentUrlMapEntry.getKey();

			// ���Ԫ�ش���ԭ����Ŀ¼�£�����crc��ͬ�����Ǹ��²���
			if (baseFileMap.containsKey(currentUrlMapEntry.getKey())) {
				long sourceUrlCrc = baseFileMap
						.get(currentUrlMapEntry.getKey());
				long currentUrlCrc = currentUrlMapEntry.getValue();
				if (currentUrlCrc != sourceUrlCrc) {
					updateCount++;
					updateFiles.add(currentUrlSuffix);
					logger.info("���޸��ļ�" + currentUrlSuffix);
					// ���ļ��ĵ�ǰ����Ŀ¼
					addOrUpdateFileUrls.put(afterUrl + currentUrlSuffix,
							diffFilePath + currentUrlSuffix);
				}
			} else {
				// ���Ԫ�ز�����AĿ¼�£�����������Ԫ��
				addCount++;
				addFiles.add(currentUrlSuffix);
				addOrUpdateFileUrls.put(afterUrl + currentUrlSuffix,
						diffFilePath + currentUrlSuffix);
				logger.info("�����ļ�" + currentUrlSuffix);
			}
		}
		// log��Ϣ����
		addFiles.add(0, Const.MAYINASI + Const.INFONEWFILE + addCount
				+ Const.GE + Const.MAYINASI);
		updateFiles.add(0, Const.MAYINASI + Const.INFOMODIFYFILE + updateCount
				+ Const.GE + Const.MAYINASI);
		addFiles.addAll(updateFiles);
		long end = System.currentTimeMillis();

		addFiles.add(Const.MAYINASI + " ������ϣ���ʱ��" + (end - start) + "ms");
		try {
			FileUtils.writeLines(addOrUpdate, addFiles);
		} catch (IOException e1) {
			logger.error(Const.ERRORFILEWRITE, e1);
		}

		// ����仯���
		logger.info(Const.MAYINASI + Const.INFOMODIFYFILE + Const.FENHAO
				+ updateCount + Const.GE + Const.INFONEWFILE + Const.FENHAO
				+ addCount + Const.GE + Const.INFODELETEFILE + Const.FENHAO
				+ delFilecount + Const.GE);

		// ��������/�޸ĵ��ļ�����changeĿ¼��
		for (Map.Entry<String, String> addOrUpdateEntry : addOrUpdateFileUrls
				.entrySet()) {
			try {
				FileUtils.copyFile(new File(addOrUpdateEntry.getKey()),
						new File(addOrUpdateEntry.getValue()));
			} catch (IOException e) {
				logger.error(Const.ERRORFILEWRITE, e);
			}
		}
	}

	/**
	 * ��ȡyyyy��MM��dd��HH��mm��ss���ʽ�ĵ�ǰʱ��
	 * 
	 * @return String ��ʽ��ʱ��
	 */
	private static String createCurrentTime() {

		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(Const.DATEFORMAT);
		String dateStr = sdf.format(date);
		return dateStr;
	}

	/**
	 * �ݹ���������ļ�
	 * 
	 * @param file
	 *            Ŀ��Ŀ¼
	 * @param allFileList
	 * @return HashMap<String, Long> �ļ��б�
	 * @throws IOException
	 */
	private static HashMap<String, Long> getAllFileMap(File file,
			String filePath, HashMap<String, Long> resMap) throws IOException {
		// �ж��ļ��Ƿ����
		if (file.exists()) {
			// �ж��ļ��ǲ���Ŀ¼���ǣ���ȡ��Ŀ¼�����ǣ�����list��
			if (file.isDirectory()) {
				File f[] = file.listFiles();
				for (File tempFile : f) {
					getAllFileMap(tempFile, filePath, resMap);
				}
			} else {
				resMap.put(file.getAbsolutePath().replace(filePath, ""),
						FileUtils.checksumCRC32(file));
			}
		}
		return resMap;
	}

}