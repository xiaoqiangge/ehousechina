package com.eju.ess.common.utils.json;

import java.util.Iterator;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * 基于jackson包装的一个工具类
 * 
 * @author Administrator
 *
 */
public class JsonUtil {

	private static ObjectMapper mapper = new ObjectMapper();
	static {
		mapper.setSerializationInclusion(Include.NON_NULL);
	}

	/**
	 * 将对象序列化成Json字符串
	 * 
	 * @param obj
	 * @return
	 */
	public static String getJsonString(Object obj) {
		String jsonString = null;
		try {
			jsonString = mapper.writeValueAsString(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonString;
	}

	/**
	 * 将Json字符串反序列化成对象
	 * 
	 * @param jsonString
	 * @param clazz
	 * @return
	 */
	public static <T> T getJsonObject(String jsonString, Class<T> clazz) {
		T t = null;
		try {
			t = mapper.readValue(jsonString, clazz);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jsonString = null;
			clazz = null;
		}
		return t;
	}
	
	public static <T> T getJsonObjectArray(String jsonString, TypeReference<T> jsonTypeReference) {
		T t = null;
		try {
			t = mapper.readValue(jsonString, jsonTypeReference);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jsonString = null;
			jsonTypeReference = null;
		}
		return t;
	}

	public static String getJsonNodeAsText(String jsonKey, String content) {
		JsonNode jsoNode = null;
		try {
			jsoNode = mapper.readTree(content);
			return jsoNode.get(jsonKey).asText();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jsoNode = null;
		}
		return null;
	}

	public static Iterator<JsonNode> getJsonNodes(String jsonKey, String content) {
		JsonNode jsoNode = null;
		try {
			jsoNode = mapper.readTree(content);
			return jsoNode.get(jsonKey).iterator();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jsoNode = null;
		}
		return null;
	}

	public static JsonNode getJsonNode(String jsonKey, String content) {
		JsonNode jsoNode = null;
		try {
			jsoNode = mapper.readTree(content);
			return jsoNode.get(jsonKey);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jsoNode = null;
		}
		return null;
	}
	
	public static JsonNode getJsonNode(String content) {
		try {
			return mapper.readTree(content);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}

	public static ObjectNode createObjectNode() {
		return mapper.createObjectNode();
	}

	
	public static ArrayNode createArrayNode() {
		return mapper.createArrayNode();
	}
}
