package com.lxk.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class FileUtil {
	public static byte[] file2Byte(String filePath) {
		ByteArrayOutputStream bos = null;
		BufferedInputStream in = null;
		try {
			File file = new File(filePath);
			if (!file.exists()) {
				throw new FileNotFoundException("file not exists");
			}
			bos = new ByteArrayOutputStream((int) file.length());
			in = new BufferedInputStream(new FileInputStream(file));
			int buf_size = 1024;
			byte[] buffer = new byte[buf_size];
			int len = 0;
		
			while (-1 != (len = in.read(buffer, 0, buf_size))) {
				bos.write(buffer, 0, len);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw Exception.makeServiceException("20010");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
				if (bos != null) {
					bos.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return bos.toByteArray();
	}
	
	public static byte[] url2Byte(String filePath) {
		HttpURLConnection connection = null;
		InputStream is = null;
		DataInputStream dataInputStream = null;
		byte[] btData = null;
		try {
			 connection = (HttpURLConnection) new URL(filePath).openConnection();
		     connection.setRequestMethod("GET");
		     int code = connection.getResponseCode();
		     if (code == 200 || code == 206) {
		         int contentLength = connection.getContentLength();
		         btData=new byte[contentLength];
		         is = connection.getInputStream();
		         dataInputStream = new DataInputStream(is);
				 dataInputStream.readFully(btData);
		     }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw Exception.makeServiceException("20010");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
			if(dataInputStream!=null){
				try {
					dataInputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(is!=null){
				 try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}   
		}
		return btData;
	}
}
