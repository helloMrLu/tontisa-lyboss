package com.lxk.could.qiniu;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;


import com.qiniu.common.QiniuException;
import com.qiniu.http.ProxyConfiguration;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import com.tontisa.common.collection.Lists;
import com.tontisa.common.collection.Maps;
import com.tontisa.common.lang.Strings;
import com.tontisa.common.lang.Sys;
import com.tontisa.common.lang.Throwables;

import net.sf.json.JSON;


/**
 * 基于七牛的云存储ＡＰＩ实现
 * 
 * @author Sam
 *
 */
public class QiniuCloudStorer extends AbstractQiniuCloudStorer {

	private Logger logger = LoggerFactory.getLogger(getClass());

	private String accessKey;

	private String secretKey;

	private UploadManager uploadManager;

	private BucketManager bucketManager;

	private Auth auth;

	private Map<String, Bucket> bucketMappings = Maps.newMap();

	private String httpProxyHost;

	private String httpProxyPort;

	@Override
	public String createUploadToKen(String bucket, String key, Long expired, Map<String, Object> attributes) {
		Assert.hasText(bucket, "bucket must not be null or empty!");
		Auth auth = Auth.create(accessKey, secretKey);
		if (Strings.isNotBlank(key)) {
			if (expired != null) {
				StringMap policy = null;
				if (attributes != null) {
					policy = new StringMap();
					policy.putAll(attributes);
				}
				return auth.uploadToken(bucket, key, expired, policy);
			}
			return auth.uploadToken(bucket, key);
		}
		return auth.uploadToken(bucket);
	}

	private String createUploadToKen(String bucket) {
		return auth.uploadToken(bucket);
	}

	private String createUploadToKen(String bucket, StringMap putPolicy) {
		return auth.uploadToken(bucket);
	}

	@Override
	public void upload(String bucket, UploadFile file) {
		try {
			uploadManager.put(file.getFileData(), file.getKey(), createUploadToKen(bucket));
		} catch (QiniuException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void upload(String bucket, List<UploadFile> files) {
		for (UploadFile file : files) {
			file.setStatus(upload(bucket, file.getKey(), file.getFileData()));
		}
	}

	public String getAccessKey() {
		return accessKey;
	}

	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	public void setBucketMappingsUseJson(String json) {
		//this.bucketMappings = JSON.parseObject(json, new TypeReference<Map<String, Bucket>>() {
		//});
	}

	// @Override
	public void afterPropertiesSet() throws Exception {
		/*Assert.hasText(accessKey, "Qiniu AccessKey Can not be Empty!!!");
		Assert.hasText(secretKey, "Qiniu SecretKey Can not be Empty!!!");
		Assert.notEmpty(bucketMappings, "Qiniu BucketMappings can not be Empty!!!");
		auth = Auth.create(accessKey, secretKey);
		// 配置代理设置
		if (Strings.isNoneBlank(httpProxyHost, httpProxyPort)) {
			ProxyConfiguration proxy = new ProxyConfiguration(httpProxyHost, Integer.valueOf(httpProxyPort));
			Config.proxy = proxy;
		}
		uploadManager = new UploadManager();
		bucketManager = new BucketManager(auth);*/
	}

	@Override
	public UploadStatus upload(String bucket, String key, byte[] filedata) {
		try {
			uploadManager.put(filedata, key, createUploadToKen(bucket));
			return UploadStatus.SUCCESS;
		} catch (QiniuException e) {
			logger.error(e.getMessage(), e);
			return UploadStatus.FAIL;
		}
	}

	@Override
	public UploadStatus upload(String bucket, String key, byte[] filedata, StringMap putPolicy) {
		try {
			uploadManager.put(filedata, key, createUploadToKen(bucket, putPolicy));
			return UploadStatus.SUCCESS;
		} catch (QiniuException e) {
			// logger.error(e.getMessage(),e);
			return UploadStatus.FAIL;
		}
	}

	public String getHttpProxyHost() {
		return httpProxyHost;
	}

	public void setHttpProxyHost(String httpProxyHost) {
		this.httpProxyHost = httpProxyHost;
	}

	public String getHttpProxyPort() {
		return httpProxyPort;
	}

	public void setHttpProxyPort(String httpProxyPort) {
		this.httpProxyPort = httpProxyPort;
	}

	@Override
	public String getFileURL(String bucketName, String fileKey) {
		Bucket bucket = bucketMappings.get(bucketName);
		if (bucket == null)
			throw Throwables.makeThrow("Can't found bucket:'%s',Please check!!!", bucketName);
		return bucket.getFileUrl(fileKey);
	}

	@Override
	public String getBucketDN(String bucketName) {
		Bucket bucket = bucketMappings.get(bucketName);
		return bucket.getDn();
	}

	@Override
	public Map<String, String> getFileURLs(String bucketName, List<String> fileKeys) {
		Map<String, String> urls = Maps.newMap();
		for (String fileKey : fileKeys) {
			urls.put(fileKey, getFileURL(bucketName, fileKey));
		}
		return urls;
	}

	@Override
	public void remove(String bucket, List<String> fileKeys) {
		if (Strings.isNotBlank(bucket) && Lists.isNotEmpty(fileKeys)) {
			for (String key : fileKeys) {
				try {
					bucketManager.delete(bucket, key);
				} catch (QiniuException e) {
					logger.error(e.getMessage(), e);
				}
			}
		}

	}

	public static void main(String[] args) {
		/*
		 * String url =
		 * "https://dn-ddkl-frontend.qbox.me/ffffffff.jpg?imageView/2/w/200/h/200";
		 * // String uri =
		 * Base64.encodeBase64String("ddkl-frontend:xxxxxxx.jpg".getBytes());
		 * String uri =
		 * UrlSafeBase64.encodeToString("ddkl-frontend:xxxxxxx.jpg".getBytes());
		 * url += "|saveas/" + uri; Auth auth =
		 * Auth.create("0kI7SzrB8CPVLEtqRANEr9JkfCC46fqvQeiSGFg3",
		 * "9lLkbNv-k712_H4PXNG-OwgLiZLqxOgnxBvn-N_3"); //
		 * Sys.println(auth.signRequest(url, null, null)); String signUrl =
		 * Strings.remove(url,"http://"); Sys.println(signUrl);
		 * Sys.println(auth.sign(signUrl)); url += "/sign/" + auth.sign(uri);
		 * Sys.println(url); // Open
		 * 
		 */
		Auth auth = Auth.create("0kI7SzrB8CPVLEtqRANEr9JkfCC46fqvQeiSGFg3", "9lLkbNv-k712_H4PXNG-OwgLiZLqxOgnxBvn-N_3");
		Sys.println(auth.uploadToken("pro-ddkl-frontend"));

	}

}
