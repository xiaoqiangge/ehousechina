package com.eju.ess.common.utils.seri;

import com.eju.ess.common.utils.md5.MD5Util;
import com.relops.snowflake.Snowflake;

public class SnowflakeUtil {
	public static Snowflake getSnowflake() {
		String md5Str = MD5Util.getMD5Str(String.valueOf(System.currentTimeMillis()));
		String binStr = StrToBinstr(md5Str);
		String LastStr = binStr.substring(binStr.length() - 10, binStr.length());
		int workId = Integer.parseInt(LastStr, 2);
		Snowflake snowflake = new Snowflake(workId);
		return snowflake;
	}

	private static String StrToBinstr(String str) {
		char[] strChar = str.toCharArray();
		String result = "";
		for (int i = 0; i < strChar.length; i++) {
			result += Integer.toBinaryString(strChar[i]);
		}
		return result;
	}
}
