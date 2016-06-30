package com.test.xujixiao.xjx.util;

import java.math.BigDecimal;

public class DataFormatUtils {

	public static BigDecimal formatMoney(double money, int retainDigit) {
		BigDecimal resultBigDecimal;
		try {
			BigDecimal bigDecimal = new BigDecimal(money);
			resultBigDecimal = bigDecimal.setScale(retainDigit, BigDecimal.ROUND_FLOOR);
		} catch (Exception e) {
			StringBuffer stringBuffer = new StringBuffer("0");
			for (int i = 0; i < retainDigit; i++) {
				if (i == 0) {
					stringBuffer.append(".");
				}
				stringBuffer.append("0");
			}
			resultBigDecimal = new BigDecimal(stringBuffer.toString());
		}
		return resultBigDecimal;
	}

	public static BigDecimal formatMoney(String money, int retainDigit) {
		BigDecimal resultBigDecimal;
		try {
			BigDecimal bigDecimal = new BigDecimal(money);
			resultBigDecimal = bigDecimal.setScale(retainDigit, BigDecimal.ROUND_FLOOR);
		} catch (Exception e) {
			StringBuffer stringBuffer = new StringBuffer("0");
			for (int i = 0; i < retainDigit; i++) {
				if (i == 0) {
					stringBuffer.append(".");
				}
				stringBuffer.append("0");
			}
			resultBigDecimal = new BigDecimal(stringBuffer.toString());
		}
		return resultBigDecimal;
	}
}
