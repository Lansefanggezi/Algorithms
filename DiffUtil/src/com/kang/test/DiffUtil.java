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
 * 1. 有A和B两个目录，目录所在位置及层级均不确定 2.
 * 需要以B为基准找出两个目录中所有有改动的文件（文件或内容增加、修改、删除），将有改动的文件放入第三个目录中，层级结构与原目录相同 3.
 * 将所有新增与更新信息记录到更新日志文件中 4. 将删除信息单独记录到删除日志文件中 5. 每次执行diff工具需要生成一个新的以日期命名的目录存放文件
 */

public class DiffUtil {

	public static void scanFileDirectory(File baseFile, File afterFile,
			File diffFileUrl, Logger logger) {

		// 在diffUrl目录下，创建日期格式的文件夹，用于存放改动的文件
		// 获取当前日期
		String nowTime = createCurrentTime();

		// 创建日期形式的文件目录
		File fileDire = new File(diffFileUrl, nowTime);
		fileDire.mkdirs();
		String diffFilePath = fileDire.getAbsolutePath();

		// 遍历出删除的信息，并记录到删除日志文件中
		// 创建删除日志文件
		String rootPath = fileDire.getAbsolutePath();
		File deleteLogFile = new File(rootPath, "delete.log");
		try {
			deleteLogFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 遍历出删除的信息
		HashMap<String, Long> sourceUrlMap = getAllFileMap(baseFile);
		HashMap<String, Long> currentUrlMap = getAllFileMap(afterFile);
		List<String> deleteFiles = new ArrayList<String>();

		long start = System.currentTimeMillis();
		logger.info(DiffUtilsTest.class.getName() + "开始Diff，参数1：" + baseFile
				+ "参数2：" + afterFile + "参数3：" + diffFileUrl);

		int delFilecount = 0;
		for (Map.Entry<String, Long> sourceEntry : sourceUrlMap.entrySet()) {
			// 遍历原版本目录下的文件，如果该文件不在当前版本目录下面，即该文件被删除了
			if (!currentUrlMap.containsKey(sourceEntry.getKey())) {
				deleteFiles.add(sourceEntry.getKey());
				logger.info("文件被删除：" + sourceEntry.getKey());
				delFilecount++;
			}
		}
		// 把删除信息写入log文件
		appendFileWriter(deleteLogFile, deleteFiles);

		// 遍历新增与更新记录,并存入到集合中
		List<String> addFiles = new ArrayList<String>();
		List<String> updateFiles = new ArrayList<String>();

		// 创建修改或者新增文件
		File addOrUpdate = new File(rootPath, "updateAndAdd.log");
		try {
			addOrUpdate.createNewFile();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		/**
		 * addOrUpdateFileUrls的key-value分别为afterFile文件的全路径名、 diffFileUrl文件的全路径名
		 * 通过文件的完全路径可以获得文件对象，用于后面的文件复制操作
		 */
		Map<String, String> addOrUpdateFileUrls = new HashMap<String, String>();
		int addCount = 0;
		int updateCount = 0;
		for (Map.Entry<String, Long> currentUrlMapEntry : currentUrlMap
				.entrySet()) {
			String currentUrlSuffix = currentUrlMapEntry.getKey();

			// 如果元素存在原来的目录下，并且crc相同，就是更新操作
			if (sourceUrlMap.containsKey(currentUrlMapEntry.getKey())) {
				try {
					long sourceUrlCrc = sourceUrlMap.get(currentUrlMapEntry
							.getKey());
					long currentUrlCrc = currentUrlMapEntry.getValue();
					if (currentUrlCrc != sourceUrlCrc) {
						updateCount++;
						updateFiles.add(currentUrlSuffix);
						logger.info("文件发生改变：" + currentUrlSuffix);
						addOrUpdateFileUrls.put(afterFile + currentUrlSuffix,
								diffFilePath + currentUrlSuffix);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

			} else {
				// 如果元素不存在A目录下，就是新增的元素
				addCount++;
				addFiles.add(currentUrlSuffix);
				addOrUpdateFileUrls.put(afterFile + currentUrlSuffix,
						diffFilePath + currentUrlSuffix);
				logger.info("新增文件：" + currentUrlSuffix);
			}
		}
		// 把修改或者新增文件写入log文件
		// log信息补充
		addFiles.add(0, "-------- 共新增文件" + addCount + "个 --------");
		updateFiles.add(0, "-------- 共更新文件" + updateCount + "个 --------");
		addFiles.addAll(updateFiles);
		long end = System.currentTimeMillis();

		addFiles.add("--------  运行完毕，耗时：" + (end - start) + "ms");
		appendFileWriter(addOrUpdate, addFiles);

		// 总体变化情况
		logger.info("-------- 共更新文件：" + updateCount + "个" + "新增：" + addCount
				+ "个" + "删除：" + delFilecount + "个");

		// 将有新增/修改的文件放入change目录中
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
	 * 把List中的信息写入对应的log文件
	 * 
	 * @param file
	 * @param value
	 * @return
	 */
	private static void appendFileWriter(File file, List<String> value) {
		// 打开一个文件写入器,以追加的形式写入
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
	 * 获取yyyy年MM月dd日HH点mm分ss秒格式的当前时间
	 * 
	 * @return
	 */
	private static String createCurrentTime() {
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy年MM月dd日HH点mm分ss秒");
		String nowTime = dateFormat.format(now);
		return nowTime;
	}

	/**
	 * 将获取到的文件存到map中
	 * 
	 * @param path
	 * @return
	 */
	private static HashMap<String, Long> getAllFileMap(File DirectoryUrl) {
		// 暂存所有文件
		List<File> allFileList = new ArrayList<File>();

		// 利用Map存文件的相对路径和CRC信息
		HashMap<String, Long> resMap = new HashMap<String, Long>();

		allFileList = getAllFile(DirectoryUrl, allFileList);
		for (File file : allFileList) {
			// key-value:文件的相对路径-文件的CRC信息
			resMap.put(
					file.getAbsolutePath().replace(
							DirectoryUrl.getAbsolutePath(), ""),
					getFileCRC(file));
		}
		return resMap;
	}

	/**
	 * 递归遍历所有文件
	 * 
	 * @param file
	 * @param allFileList
	 * @return
	 */
	private static List<File> getAllFile(File file, List<File> allFileList) {
		// 判断文件是否存在
		if (file.exists()) {
			// 判断是否是目录：是，获取所有文件按；否存入List;
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
	 * 获取文件的CRC
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