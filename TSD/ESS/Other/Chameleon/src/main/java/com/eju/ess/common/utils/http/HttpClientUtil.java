//package com.eju.ess.common.utils.http;
//
//import org.apache.http.client.methods.CloseableHttpResponse;
//import org.apache.http.util.EntityUtils;
//
//public class HttpClientUtil {
//
//	public static void close(CloseableHttpResponse rsp){
//		if(rsp != null){
//			try {
//				EntityUtils.consume(rsp.getEntity());
//				rsp.close();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//	}
//}
