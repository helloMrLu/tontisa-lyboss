package com.tontisa.util;

import java.util.UUID;

public class TtxRandomUtils {

	/**
	 * uuit
	 * @return
	 */
	public static String getUuid(){
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	/**
	 * 需要多长的字符串（数字）
	 * @param len
	 * @return
	 */
	public static String getRandomNumber(int len){
		return String.valueOf(Math.random()).substring(2, 2 + len) ;
	}
	
	/**
	 * 需要多长的字符串（数字和字母）
	 * @param len
	 * @return
	 */
	public static String getRandomString(int len){
		if (len > 16) {
			len = 16;
		}
		return getUuid().substring(16, 16+len);
	}
	
	/*public static void main(String[] args) {
		String orig = getUuid();
		System.out.println(orig);
		System.out.println(orig.substring(16, 16+16));
	}*/
}
