package com.jufangyuan.weixinpay;

public class Configure {
	private static String key = "7kfpajdjrzc5pbrj27ae8gwdybk4q5ic";

	//灏忕▼搴廔D	
	private static String appID = "wx43b4a325dfcf1409";
	//鍟嗘埛鍙�
	private static String mch_id = "1498243132";
	//
	private static String secret = "fed2da2df958cecefef92dc311c70133";

	public static String getSecret() {
		return secret;
	}

	public static void setSecret(String secret) {
		Configure.secret = secret;
	}

	public static String getKey() {
		return key;
	}

	public static void setKey(String key) {
		Configure.key = key;
	}

	public static String getAppID() {
		return appID;
	}

	public static void setAppID(String appID) {
		Configure.appID = appID;
	}

	public static String getMch_id() {
		return mch_id;
	}

	public static void setMch_id(String mch_id) {
		Configure.mch_id = mch_id;
	}

}
