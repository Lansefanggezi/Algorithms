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
 * 1. 有A和B两个目录，目录所在位置及层级均不确定 2.
 * 需要以B为基准找出两个目录中所有有改动的文件（文件或内容增加、修改、删除），将有改动的文件放入第三个目录中，层级结构与原目录相同 3.
 * 将所有新增与更新信息记录到更新日志文件中 4. 将删除信息单独记录到删除日志文件中 5. 每次执行diff工具需要生成一个新的以日期命名的目录存放文件
 */
public class DiffUtil {

	public static void scanFileDirectory(File baseUrl, File afterUrl,
			File diffFileUrl, Logger logger) {

		// 遍历出删除的信息
		// 修改前文件的相对路径和CRC值
		HashMap<String, Long> baseFileMap = new HashMap<String, Long>();

		// 修改后文件的相对路径和CRC值
		HashMap<String, Long> afterFileMap = new HashMap<String, Long>();
		
		// 在diffUrl目录下，创建日期格式的文件夹，用于存放改动的文件
		// 获取当前日期
		String nowTime = createCurrentTime();

		// 创建日期形式的文件目录
		File fileDire = new File(diffFileUrl.getAbsoluteFile(), nowTime);
		fileDire.mkdirs();
		String diffFilePath = fileDire.getAbsolutePath();

		// 遍历出删除的信息，并记录到删除日志文件中
		// 创建删除日志文件
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

		// 被删除的文件
		List<String> deleteFiles = new ArrayList<String>();

		// 标记开始时间
		long start = System.currentTimeMillis();

		// 遍历原先目录，如果它不存在与当前目录，就是被删除了
		int delFilecount = 0;
		for (Map.Entry<String, Long> baseFileEntry : baseFileMap.entrySet()) {
			// 如果它不存在与当前目录，就是被删除了
			if (!afterFileMap.containsKey(baseFileEntry.getKey())) {
				delFilecount++;
				deleteFiles.add(baseFileEntry.getKey());
				logger.info("被刪除文件" + baseFileEntry.getKey());
			}
		}

		// 把删除信息写入log文件
		try {
			FileUtils.writeLines(deleteLogFile, deleteFiles);
		} catch (IOException e1) {
			logger.error(Const.ERRORFILEWRITE, e1);
		}

		// 遍历新增与更新记录,并存入到集合中
		List<String> addFiles = new ArrayList<String>();
		List<String> updateFiles = new ArrayList<String>();

		// 创建修改或者新增文件
		File addOrUpdate = new File(rootPath, Const.ADDANDUPDATE);
		try {
			addOrUpdate.createNewFile();
		} catch (IOException e) {
			logger.error(Const.ERRORCREATE, e);
		}

		// addOrUpdateFileUrls的key-value分别为afterFile文件的全路径名、diffFileUrl文件的全路径名
		// 通过文件的完全路径可以获得文件对象，用于后面的文件复制操作
		Map<String, String> addOrUpdateFileUrls = new HashMap<String, String>();
		int addCount = 0;
		int updateCount = 0;
		for (Map.Entry<String, Long> currentUrlMapEntry : afterFileMap
				.entrySet()) {
			String currentUrlSuffix = currentUrlMapEntry.getKey();

			// 如果元素存在原来的目录下，并且crc不同，就是更新操作
			if (baseFileMap.containsKey(currentUrlMapEntry.getKey())) {
				long sourceUrlCrc = baseFileMap
						.get(currentUrlMapEntry.getKey());
				long currentUrlCrc = currentUrlMapEntry.getValue();
				if (currentUrlCrc != sourceUrlCrc) {
					updateCount++;
					updateFiles.add(currentUrlSuffix);
					logger.info("被修改文件" + currentUrlSuffix);
					// 此文件的当前完整目录
					addOrUpdateFileUrls.put(afterUrl + currentUrlSuffix,
							diffFilePath + currentUrlSuffix);
				}
			} else {
				// 如果元素不存在A目录下，就是新增的元素
				addCount++;
				addFiles.add(currentUrlSuffix);
				addOrUpdateFileUrls.put(afterUrl + currentUrlSuffix,
						diffFilePath + currentUrlSuffix);
				logger.info("新增文件" + currentUrlSuffix);
			}
		}
		// log信息补充
		addFiles.add(0, Const.MAYINASI + Const.INFONEWFILE + addCount
				+ Const.GE + Const.MAYINASI);
		updateFiles.add(0, Const.MAYINASI + Const.INFOMODIFYFILE + updateCount
				+ Const.GE + Const.MAYINASI);
		addFiles.addAll(updateFiles);
		long end = System.currentTimeMillis();

		addFiles.add(Const.MAYINASI + " 运行完毕，耗时：" + (end - start) + "ms");
		try {
			FileUtils.writeLines(addOrUpdate, addFiles);
		} catch (IOException e1) {
			logger.error(Const.ERRORFILEWRITE, e1);
		}

		// 总体变化情况
		logger.info(Const.MAYINASI + Const.INFOMODIFYFILE + Const.FENHAO
				+ updateCount + Const.GE + Const.INFONEWFILE + Const.FENHAO
				+ addCount + Const.GE + Const.INFODELETEFILE + Const.FENHAO
				+ delFilecount + Const.GE);

		// 将有新增/修改的文件放入change目录中
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
	 * 获取yyyy年MM月dd日HH点mm分ss秒格式的当前时间
	 * 
	 * @return String 格式化时间
	 */
	private static String createCurrentTime() {

		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(Const.DATEFORMAT);
		String dateStr = sdf.format(date);
		return dateStr;
	}

	/**
	 * 递归遍历所有文件
	 * 
	 * @param file
	 *            目标目录
	 * @param allFileList
	 * @return HashMap<String, Long> 文件列表
	 * @throws IOException
	 */
	private static HashMap<String, Long> getAllFileMap(File file,
			String filePath, HashMap<String, Long> resMap) throws IOException {
		// 判断文件是否存在
		if (file.exists()) {
			// 判断文件是不是目录：是，获取子目录；不是，存入list；
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