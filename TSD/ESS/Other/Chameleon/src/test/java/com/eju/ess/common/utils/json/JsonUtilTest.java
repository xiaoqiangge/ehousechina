package com.eju.ess.common.utils.json;

import org.junit.Test;

import com.fasterxml.jackson.databind.node.ObjectNode;


public class JsonUtilTest {
	@Test
	public void test1(){
		ObjectNode root = JsonUtil.createObjectNode();
		ObjectNode node1 = JsonUtil.createObjectNode();
		ObjectNode node2 = JsonUtil.createObjectNode();
		node2.put("zhang", 1);
		node1.put("aaaa", "1111");
		node1.set("bbb", node2);
		root.set("child",node1);
		System.out.println(root.toString());
	}
	
	@Test
	public void test2(){
		String json="{\"aaa\":{\"bbb\":\"2222\"}}";
		ObjectNode objectNode = JsonUtil.getJsonObject(json, ObjectNode.class);
		String text = objectNode.get("aaa").get("bbb").asText();
		System.out.println(" >>  "+text);
	}
}
