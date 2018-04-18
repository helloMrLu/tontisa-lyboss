package com.lxk.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "ttx.lyboss")//前缀 
@PropertySource(value="classpath:com/lxk/config/customProperties.yml")//配置文件路径  
public class Config {

	private String domain;

	private String defaultBucket;

	private String accessKey;

	private String secretKey;
	
	private String qiniucallbackEnabled;

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getDefaultBucket() {
		return defaultBucket;
	}

	public void setDefaultBucket(String defaultBucket) {
		this.defaultBucket = defaultBucket;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	public String getAccessKey() {
		return accessKey;
	}

	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}

	public String getQiniucallbackEnabled() {
		return qiniucallbackEnabled;
	}

	public void setQiniucallbackEnabled(String qiniucallbackEnabled) {
		this.qiniucallbackEnabled = qiniucallbackEnabled;
	}
	
	
	
}
