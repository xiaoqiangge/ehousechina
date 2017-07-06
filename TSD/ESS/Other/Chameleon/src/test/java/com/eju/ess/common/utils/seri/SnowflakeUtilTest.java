package com.eju.ess.common.utils.seri;

import org.junit.Test;

import com.relops.snowflake.Snowflake;

public class SnowflakeUtilTest {
	@Test
	public void test(){
		Snowflake snowflake = SnowflakeUtil.getInstance();
	}
}
