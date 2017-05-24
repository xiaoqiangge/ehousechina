//package com.eju.ess.common.utils.http;
//
//import org.apache.http.client.config.RequestConfig;
//import org.apache.http.client.methods.CloseableHttpResponse;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
//import org.apache.http.util.EntityUtils;
//
//public class HttpClientPool {
//
//	private final static int MAX=100;
//	
//	public static CloseableHttpClient getHttpClient() {
//		PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
//		/*
//		 * 设置最大httpclient pool最大连接数
//		 */
//		cm.setMaxTotal(MAX);
//		/*
//		 * 设置默认最大路由数
//		 */
//		cm.setDefaultMaxPerRoute(MAX);
//		CloseableHttpClient httpClient = HttpClients
//				.custom()
//				.setDefaultRequestConfig(getDefaultRequestConfig())
//				.setConnectionManager(cm)
//				.build();
//		return httpClient;
//	}
//	
//	private static RequestConfig getDefaultRequestConfig(){
//		RequestConfig requestConfig = RequestConfig
//				.custom()
//				/*
//				 * 从连接池中获取连接的超时时间，假设：连接池中已经使用的连接数等于setMaxTotal，新来的线程在等待1*1000
//				 * 后超时，错误内容：org.apache.http.conn.ConnectionPoolTimeoutException: Timeout waiting for connection from pool
//				 */
//				.setConnectionRequestTimeout(1*1000)
//				/*
//				 * 这定义了通过网络与服务器建立连接的超时时间。
//				 * Httpclient包中通过一个异步线程去创建与服务器的socket连接，这就是该socket连接的超时时间，
//				 * 此处设置为2秒。假设：访问一个IP，192.168.10.100，这个IP不存在或者响应太慢，那么将会返回
//				 * java.net.SocketTimeoutException: connect timed out
//				 */
//			    .setConnectTimeout(5*1000)
//			    /*
//			     * 指的是连接上一个url，获取response的返回等待时间，假设：url程序中存在阻塞、或者response
//			     * 返回的文件内容太大，在指定的时间内没有读完，则出现
//			     * java.net.SocketTimeoutException: Read timed out
//			     */
//			    .setSocketTimeout(30*1000)
//				.build();
//		return requestConfig;
//	}
//}
