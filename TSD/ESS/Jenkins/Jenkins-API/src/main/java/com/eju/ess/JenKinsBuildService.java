package com.eju.ess;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.Consts;
import org.apache.http.HttpHost;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JenKinsBuildService {
	
	private String jenKinsHost="10.99.70.38";
	private int jenKinsPort=8000;
	private String jenKinsUser="demo";
	private String jenKinsPasswd="demo";
	private CloseableHttpClient httpClient=HttpClientPool.getHttpClient();
	
	public void build(String jobName) {
		String fmt="http://%s:%s/job/%s/build";
		CrumbEntity crumbEntity = getCrumb();
		HttpPost httpPost = new HttpPost(String.format(fmt, jenKinsHost,jenKinsPort,jobName));
		CloseableHttpResponse rsp = null;
		try {
			httpPost.addHeader(crumbEntity.getCrumbRequestField(),crumbEntity.getCrumb());
			rsp = httpClient.execute(httpPost, this.getHttpClientContext());
		} catch (Exception e) {
			log.error(null, e);
		}finally{
			HttpUtil.close(rsp);
			fmt=null;
			crumbEntity=null;
			httpPost=null;
		}
	}
	
	public void build(String jobName,Map<String,String> parameters) {
		String fmt="http://%s:%s/job/%s/buildWithParameters";
		CrumbEntity crumbEntity = getCrumb();
		HttpPost httpPost = new HttpPost(String.format(fmt, jenKinsHost,jenKinsPort,jobName));
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		for (String key : parameters.keySet()) {
			formparams.add(new BasicNameValuePair(key, parameters.get(key)));
	    }
		UrlEncodedFormEntity urlEntity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);
		CloseableHttpResponse rsp = null;
		try {
			httpPost.setEntity(urlEntity);
			httpPost.addHeader(crumbEntity.getCrumbRequestField(),crumbEntity.getCrumb());
			rsp = httpClient.execute(httpPost, this.getHttpClientContext());
		} catch (Exception e) {
			log.error(null, e);
		}finally{
			HttpUtil.close(rsp);
			fmt=null;
			crumbEntity=null;
			httpPost=null;
			formparams.clear();
			parameters.clear();
			formparams=null;
			parameters=null;
		}
	}
	
	public String getJobDetail(String jobName){
		String fmt="http://%s:%s/job/%s/api/json";
		CrumbEntity crumbEntity = getCrumb();
		HttpGet httpGet=new HttpGet(String.format(fmt, jenKinsHost,jenKinsPort,jobName));
		CloseableHttpResponse rsp=null;
		try {
			httpGet.addHeader(crumbEntity.getCrumbRequestField(),crumbEntity.getCrumb());
			rsp = httpClient.execute(httpGet,this.getHttpClientContext());
			String jsonResult=EntityUtils.toString(rsp.getEntity());
			return jsonResult;
		} catch (Exception e) {
			log.error(null, e);
		} finally {
			HttpUtil.close(rsp);
			fmt=null;
			crumbEntity=null;
			httpGet=null;
		}
		return null;
	}
	
	public CrumbEntity getCrumb(){
		String fmt="http://%s:%s/crumbIssuer/api/json";
		CloseableHttpResponse rsp=null;
		HttpGet httpGet=new HttpGet(String.format(fmt, jenKinsHost,jenKinsPort));
		try {
			rsp=httpClient.execute(httpGet,this.getHttpClientContext());
			String jsonResult=EntityUtils.toString(rsp.getEntity());
			CrumbEntity crumbEntity=JsonUtil.getJsonObject(jsonResult, CrumbEntity.class);
			return crumbEntity;
		} catch (Exception e) {
			log.error(null,e);
		}finally{
			HttpUtil.close(rsp);
			fmt=null;
			httpGet=null;
		}
		return null;
	}
	
	private CredentialsProvider getCredentialsProvider(){
		CredentialsProvider credsProvider = new BasicCredentialsProvider();
		credsProvider.setCredentials(
		        new AuthScope(this.getHttpHost().getHostName(), this.getHttpHost().getPort()),
		        new UsernamePasswordCredentials(jenKinsUser, jenKinsPasswd));
		return credsProvider;
	}
	
	private HttpHost getHttpHost(){
		return new HttpHost(jenKinsHost, jenKinsPort);
	}
	
	private AuthCache getAuthCache(){
		AuthCache authCache = new BasicAuthCache();
		BasicScheme basicAuth = new BasicScheme();
		authCache.put(getHttpHost(), basicAuth);
		return authCache;
	}
	
	private HttpClientContext getHttpClientContext(){
		HttpClientContext httpClientContext = HttpClientContext.create();
		httpClientContext.setCredentialsProvider(this.getCredentialsProvider());
		httpClientContext.setAuthCache(this.getAuthCache());
		return httpClientContext;
	}
}
