package com.eju.ess;

import org.junit.Test;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * 基于jackson包装的一个工具类
 * @author Administrator
 *
 */
@Slf4j
public class JsonUtil {

	private static ObjectMapper mapper = new ObjectMapper();
	static{
		mapper.setSerializationInclusion(Include.NON_NULL);
	}
	/**
	 * 将对象序列化成Json字符串
	 * @param obj
	 * @return
	 */
	public static String getJsonString(Object obj) {
		String jsonString = null;
		try {
			jsonString = mapper.writeValueAsString(obj);
		} catch (Exception e) {
			log.error(null, e);
		}
		return jsonString;
	}
	
	/**
	 * 将Json字符串反序列化成对象
	 * @param jsonString
	 * @param clazz
	 * @return
	 */
	public static <T> T getJsonObject(String jsonString,Class<T> clazz){
		T t=null;
		try {
			t=mapper.readValue(jsonString, clazz);
		} catch (Exception e) {
			log.error(null, e);
		}finally{
			jsonString=null;
			clazz=null;
		}
		return t;
	}
	
	public static String getJsonNodeAsText(String jsonKey,String content){
		JsonNode jsoNode=null;
		try {
			jsoNode=mapper.readTree(content);
			return jsoNode.get(jsonKey).asText();
		} catch (Exception e) {
			log.error(null,e);
		}finally{
			jsoNode=null;
		}
		return null;
	}
	
	@Test
	public void test1(){
		String name=getJsonNodeAsText("age","{\"name\":\"zhangsan\",\"age\":100}");
		log.info(name);
	}
}
