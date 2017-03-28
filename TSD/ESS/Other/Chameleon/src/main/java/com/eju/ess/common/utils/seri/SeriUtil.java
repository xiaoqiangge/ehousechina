package com.eju.ess.common.utils.seri;

import java.util.UUID;

public class SeriUtil {

	public static String getUUID(){
		 UUID uuid = UUID.randomUUID();
		 return uuid.toString();
	}
}
