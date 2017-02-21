package com.eju.ess;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HttpClientPool {
	private final static int MAX_PERROUTE = 500;
	private final static int MAX_TOTAL = 500;

	public static CloseableHttpClient getHttpClient() {
		PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
		cm.setMaxTotal(MAX_TOTAL);
		cm.setDefaultMaxPerRoute(MAX_PERROUTE);
		CloseableHttpClient httpClient = HttpClients
				.custom()
				.setConnectionManager(cm)
				.build();
		return httpClient;
	}
}
