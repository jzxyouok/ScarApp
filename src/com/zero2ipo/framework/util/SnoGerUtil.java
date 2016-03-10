package com.zero2ipo.framework.util;

import java.util.Random;


public class SnoGerUtil {
	private static Random random = new Random();

	/**
	 * 生成19位编码，格式为：yyMMddHHmmssSSS + 4 随机数
	 */
	public static synchronized String getBusNumber() {

		StringBuffer result = new StringBuffer();

		Random random = new Random();

		int ID_BYTES = 4;

		// 取时间
		String dateTime = VDateTimeUtil.getCurrentTimeNum();

		result = result.append(dateTime.substring(2));

		// 取10位随机数
		for (int i = 0; i < ID_BYTES; i++) {
			result = result.append(random.nextInt(10));
		}

		return result.toString();
	}

	/**
	 * 生成14位编码，格式为：yyyyMMdd + 6 随机数
	 */
	public static synchronized String getOrderNumber() {

		StringBuffer result = new StringBuffer();

		Random random = new Random();

		int ID_BYTES = 6;

		// 取时间
		String dateTime = VDateTimeUtil.getCurrentTimeNum();

		result = result.append(dateTime.substring(0,8));

		// 取10位随机数
		for (int i = 0; i < ID_BYTES; i++) {
			result = result.append(random.nextInt(10));
		}

		return result.toString();
	}

	/**
	 * 获取随机字符
	 * 
	 * @param select
	 *            类型: 0:a-z 1:A-Z 2:0-9
	 * @return
	 */
	public static synchronized Character getRandomChar(int select) {
		int tempval = 0;
		if (select == 0) {
			tempval = (int) ((float) 'a' + random.nextFloat() * (float) ('z' - 'a'));
		} else if (select == 1) {
			tempval = (int) ((float) 'A' + random.nextFloat() * (float) ('Z' - 'A'));
		} else {
			tempval = (int) ((float) '0' + random.nextFloat() * (float) ('9' - '0'));
		}
		return new Character((char) tempval);
	}

	/**
	 * 获取指定长度随机字符串
	 * 
	 * @param length
	 * @return
	 */
	public static synchronized String getRandomString(int length) {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int randomSelect = (int) (random.nextFloat() * 100) % 3;
			buffer.append(getRandomChar(randomSelect));
		}
		return buffer.toString();
	}
	
	/**
	 * 获取指定最大长度随机数
	 * @param length
	 * @return
	 */
	public static synchronized long getRandomNumber(int maxLength) {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < maxLength; i++) {
			buffer.append(getRandomChar(2));
		}
		return Long.parseLong(buffer.toString());
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		for (int i = 0; i < 1000; i++) {
//			System.out.println(getOrderNumber());
//		}
	}
	
}
