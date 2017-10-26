package com.eju.ess.common.utils.string;

import org.apache.commons.codec.binary.Base64;

public class Base64Util {
	/**
	 * base64编码
	 * @param args
	 * @return
	 */
	public static String encodeBase64(String args) {
		byte[] enbytes = Base64.encodeBase64(args.getBytes());
		return new String(enbytes);
	}
	
	/**
	 * base64解码
	 * @param args
	 * @return
	 */
	public static String decodeBase64(String args) {
		byte[] debytes = Base64.decodeBase64(new String(args));
		return new String(debytes);
	}
}

