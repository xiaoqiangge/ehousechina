package com.eju.ess.common.utils.seri;

import java.util.UUID;

/**
 * 随机序列工具类
 * @author 小强哥
 *
 */
public class SeriUtil {

	/**
	 * 获得UUID
	 * @return 
	 */
	public static String getUUID(){
		 UUID uuid = UUID.randomUUID();
		 return uuid.toString();
	}
}
