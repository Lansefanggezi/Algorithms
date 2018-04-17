package com.kang.nio;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

public class FileInputStreamTest {

	@Test
	public  void mian()
	{
		InputStream in = null;
		File file = new File("G:/CarlGit/Carl.txt");
		try {
			in = new BufferedInputStream(new FileInputStream(file));
			
			byte[] byteArray = new byte[1024];
			int length = in.read(byteArray);
			
			while (length != -1) {
				for(int i = 0; i<length; i++)
					System.out.println((char)byteArray[i]);
				length = in.read(byteArray);
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
