package com.eju.ess;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.util.StringUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HttpUtil {

	public static void close(CloseableHttpResponse rsp){
		if(!StringUtils.isEmpty(rsp)){
			try {
				EntityUtils.consume(rsp.getEntity());
				rsp.close();
			} catch (Exception e) {
				log.error(null,e);
			}
		}
	}
}
