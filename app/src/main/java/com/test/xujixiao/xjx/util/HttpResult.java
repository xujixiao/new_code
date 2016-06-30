package com.test.xujixiao.xjx.util;

public class HttpResult {

	/**
	 * 获取验证码返回消息
	 * 
	 * @param errorId
	 * @return
	 */
	public static String getVerificationResult(final int errorId) {

		String result;

		switch (errorId) {
		case 1:
			result = "发送成功";
			break;
		case 2:
			result = "验证码已发送，请稍候再试";
			break;
		case 3:
			result = "被违规删除，15天不能注册";
			break;
		case 4:
			result = "被列为黑名单";
			break;
		case 5:
			result = "已被注册";
			break;
		default:
			result = "其他错误." + errorId;
			break;
		}
		return result;
	}

	/**
	 * 注册帐号返回消息
	 * 
	 * @param errorId
	 * @return
	 */
	public static String getRegisterResult(final int errorId) {
		String result;

		switch (errorId) {
		case 1:
			result = "完成注册";
			break;
		case 2:
			result = "已经注册";
			break;
		case 3:
			result = "销户未满30天，不能注册";
			break;
		default:
			result = "其他错误." + errorId;
			break;
		}
		return result;
	}
}
