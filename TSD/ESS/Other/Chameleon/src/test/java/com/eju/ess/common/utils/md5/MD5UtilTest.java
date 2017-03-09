package com.eju.ess.common.utils.md5;

import org.junit.Test;

public class MD5UtilTest {
	@Test
	public void test(){
		String result = MD5Util.getMD5Str("test");
		System.out.println(result);
	}
}
