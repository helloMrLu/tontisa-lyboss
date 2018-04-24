package com.lxk.could.qiniu;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.lxk.config.Config;
import com.lxk.util.Exception;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import com.tontisa.common.collection.Lists;
import com.tontisa.common.collection.Maps;
import com.tontisa.common.lang.Strings;
import com.tontisa.common.lang.Throwables;

/**
 * 七牛工具
 *
 */
public class QiNiuUtils {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	private Config config;
	private Auth auth;
	private Configuration cfg;
	private UploadManager uploadManager;
	private BucketManager bucketManager;
	public QiNiuUtils(Config config) {
		this.config=config;
		init(config);
	}
	
	/**
	 * 获取上传文件token，并设置回调
	 * 必须是用户登录之后才能调用
	 * @param bucket文件要存放的空间名，必填
	 * @param key文件放在空间的key即文件名前缀，必须自行保证key的唯一性，否则会被复盖，可以为空
	 * @param expired凭证有效期，可以为空
	 * @param attributes上传到空间的一些属性设置，可以为空
	 */
	public  Map createUploadToken(String bucket,String key,Long magUserId,Long expired, Map<String,Object> attributes) {
		//设置回调地址--需要配置一个变量控制是否启用
		boolean callbackEnabled = "1".equals(config);	
		if (callbackEnabled) {
			//设置回调信息
			//attributes.put("callbackUrl", callback);
			StringBuilder bodyBuilder = new StringBuilder("name=$(fname)&fileSize=$(fsize)&mimeType=$(mimeType)&extName=$(ext)&attachKey=$(key)&bucket=$(bucket)");
			//bodyBuilder.append("&createUserId=").append(magUserId);
			//bodyBuilder.append("&createUserType=").append(UserType.XQERP);
			//设置业务类型
			//小强erp系统
			attributes.put("callbackBody", bodyBuilder.toString());
			//设置生成key策略
			attributes.put("saveKey", "lyboss/" + magUserId + "/$(year)$(mon)$(day)$(hour)$(min)$(sec)-$(etag)$(ext)" );
			//限定上传文件大小最大值，单位：字节（Byte） 10485760字节-10M
			/*if (fsizeLimit != null) {
				attributes.put("fsizeLimit", fsizeLimit);
			}*/
		}
		String token = this.createUploadToKen(bucket,key,expired,attributes);
		String dn = getBucketDN();
		Map map = Maps.mapByAarray("token",Strings.defaultString(token), "dn", dn);
		return map;
	}
	
	
	/**
	 * 服务器端上传
	 * @param bucket
	 * @param key
	 * @param filedata
	 * @param attributes
	 * @return
	 */
	public UploadStatus upload(String bucket, String key, byte[] filedata) {
		String token = createUploadToKen(bucket, key, null, null);
		if(Strings.isEmpty(token)){
			throw Exception.makeServiceException("20011");
		}
		try {
			uploadManager.put(filedata,key,token);
			return UploadStatus.SUCCESS;
		} catch (QiniuException e) {
			logger.error(e.getMessage(), e);
			return UploadStatus.FAIL;
		}
	}
	

	
	/**
	 * 获取dn
	 * @return
	 */
	private String getBucketDN() {
		return config.getDomain();
	}
	
	/**
	 * 获取图片路径
	 * @param key
	 * @return
	 */
	public String getFileURL(String key) {
		String dn=getBucketDN();
		if (Strings.isBlank(dn)) {
			Throwables.makeThrow("the bucket dn is empty!");
		}
		if (dn.trim().endsWith("/"))
			return dn + key;
		else 
			return dn + "/" + key;
	}
		
	/**
	 * 上传图片
	 * @param bucket
	 * @param key
	 * @param expired
	 * @param attributes
	 * @return
	 */
	private String createUploadToKen(String bucket, String key, Long expired, Map<String, Object> attributes) {
		Assert.hasText(bucket,"bucket must not be null or empty!");
		if (Strings.isNotBlank(key) ) {
			if (expired != null) {
				StringMap policy = null;
				if (attributes != null)   {
					policy = new StringMap();
					policy.putAll(attributes);
				}
				return auth.uploadToken(bucket, key, expired, policy);
			} 
			return auth.uploadToken(bucket, key);
		}  
		return auth.uploadToken(bucket);
	}
	
	/**
	 * 删除七牛文件
	 * @param bucket
	 * @param fileKeys
	 * @return 
	 */
	public Response remove(String bucket, List<String> fileKeys) {
		Response r = null;
		if (Lists.isNotEmpty(fileKeys)) {
			if(Strings.isBlank(bucket)){
				bucket=config.getDefaultBucket();
			}
			for (String key : fileKeys) {
				if(key.contains(config.getDomain())){
					key=key.split(config.getDomain())[1];
				}
				try {
					r = bucketManager.delete(bucket, key);
				} catch (QiniuException e) {
					logger.error(e.getMessage(), e);
				}
			}
		}
		return r;
	}
	
	/**
	 * 七牛初始化
	 * @param config
	 */
	private void init(Config config) {
		auth = Auth.create(config.getAccessKey(), config.getSecretKey());
		cfg=new Configuration(Zone.zone0());
		uploadManager=new UploadManager(cfg);
		bucketManager = new BucketManager(auth, cfg);
	}
}
