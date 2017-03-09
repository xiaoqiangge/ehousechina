package com.eju.ess.common.utils.md5;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;

public class MD5Util {

	/**
	 * 返回MD5字符串
	 * @param args
	 * @return
	 */
	public static String getMD5Str(String args){
		return DigestUtils.md5Hex(args);
	}
	
	/**
	 * 返回MD5字符串
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static String getMD5Str(File file) throws IOException{
		FileInputStream fis= new FileInputStream(file);
		return DigestUtils.md5Hex(IOUtils.toByteArray(fis));
	}
}
