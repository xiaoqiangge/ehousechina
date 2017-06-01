package com.eju.ess.common.utils.json;

import org.junit.Test;

import com.fasterxml.jackson.databind.node.ObjectNode;


public class JsonUtilTest {
	@Test
	public void test(){
		ObjectNode root = JsonUtil.createObjectNode();
		ObjectNode node1 = JsonUtil.createObjectNode();
		ObjectNode node2 = JsonUtil.createObjectNode();
		node2.put("zhang", 1);
		node1.put("aaaa", "1111");
		node1.set("bbb", node2);
		root.set("child",node1);
		System.out.println(root.toString());
	}
}
