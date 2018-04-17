package com.kang.nio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import org.junit.Test;

public class Channel {

	@Test
	public void TestChannel()
	{
		File file = new File("G:/CarlGit/Carl.txt");
		try {
			RandomAccessFile rwFile = new RandomAccessFile(file, "rw");
			
			FileChannel fileChannel = rwFile.getChannel();
			ByteBuffer buffer = ByteBuffer.allocate(1024);
			
			int length = fileChannel.read(buffer);
			 while(length != -1)
            {
				 //切换到读模式
				 buffer.flip();
	                while(buffer.hasRemaining())
	                {
	                    System.out.print((char)buffer.get());
	                }
	             //移除已经读到数据
	            buffer.compact();
	            length = fileChannel.read(buffer);
            }
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
