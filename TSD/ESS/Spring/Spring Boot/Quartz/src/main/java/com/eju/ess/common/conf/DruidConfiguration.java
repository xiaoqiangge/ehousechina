package com.eju.ess.common.conf;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class DruidConfiguration {
	@Value("${druid.datasource.driver-class-name}")
	private String druidDriver;
	@Value("${druid.datasource.url}")
	private String druidUrl;
	@Value("${druid.datasource.username}")
	private String druidUserName;
	@Value("${druid.datasource.password}")
	private String druidPwd;
	@Value("${druid.datasource.initialsize}")
	private int druidInitialSize;
	@Value("${druid.datasource.druidminidle}")
	private int druidMinIdle;
	@Value("${druid.datasource.druidmaxactive}")
	private int druidMaxActive;
	
	@Bean
	public DataSource dataSource() throws Exception {
		DruidDataSource druidDataSource = new DruidDataSource();
		druidDataSource.setDriverClassName(druidDriver);
		druidDataSource.setUrl(druidUrl);
		druidDataSource.setUsername(druidUserName);
		druidDataSource.setPassword(druidPwd);
		druidDataSource.setInitialSize(druidInitialSize);
		druidDataSource.setMinIdle(druidMinIdle);
		druidDataSource.setMaxActive(druidMaxActive);
		return druidDataSource;
	}
}
