package com.lxk.could.qiniu;

import java.util.List;
import java.util.Map;

import com.qiniu.util.StringMap;

/**
 * 云存储的抽象ＡＰＩ
 * @author sam renlei
 *
 */
public interface CloudStorer {
	/**
	 * 创建一个上传的凭证
	 * @param bucket 文件要存放的空间名，必填
	 * @param fileKey 文件放在空间的key，必须自行保证key的唯一性，否则会被复盖，可以为空
	 * @param expired 凭证有效期，可以为空
	 * @param attributes 上传到空间的一些属性设置，可以为空
	 * @return 返回凭证
	 */
	public String createUploadToKen(String bucket,String fileKey,Long expired,Map<String,Object> attributes);
	
	/**
	 * 上传多个文件到云存储中
	 * @param bucket 文件要存放的空间名
	 * @param files 要上传的文件
	 */
	public void upload(String bucket,List<UploadFile> files) ;
	
	/**
	 * 上传一个文件到云存储中
	 * @param bucket 文件要存放的空间名
	 * @param file 要上传的文件
	 */
	public void upload(String bucket,UploadFile file);
	
	/**
	 * 上传一个文件到云存储中，因为是bte[]，所以可以是任意的文件类型，比如说在文本
	 * @param bucket  文件要存放的空间名
	 * @param key 文件的key
	 * @param filedata 文件数据
	 * @return
	 */
	public UploadStatus upload(String bucket,String key,byte[] filedata);
	
	/**
	 * 同上，增加上传策略参数
	 * @param bucket
	 * @param key
	 * @param filedata
	 * @param pubPolicy
	 * @return
	 */
	public UploadStatus upload(String bucket,String key,byte[] filedata, StringMap putPolicy);
	
	/**
	 * 删除一个或者多个文件
	 * @param bucket 删除的空间
	 * @param fileKeys 删除的key
	 */
	public void remove(String bucket,List<String> fileKeys);
	
	/**
	 * 取得某个存储空间下的文件的访问ＵＲＬ
	 * @param bucket 存储文件的空间
	 * @param fileKey 文件ＫＥＹ
	 * @return 返回基于ＨＴＴＰ全链接
	 */
	public String getFileURL(String bucketName,String fileKey);
	
	/**
	 * 获取七牛空间对应的域名
	 * @param bucketName 空间名
	 * @return
	 */
	public String getBucketDN(String bucketName);
	
	/**
	 * 取得多个某个存储空间下的文件的访问ＵＲＬ
	 * @param fileKeys 文件ＫＥＹ
	 * @return 以map返回多个文件的url,map的key为文件的key，map的value为文件ＵＲＬ
	 */
	public Map<String,String> getFileURLs(String bucketName,List<String> fileKeys);
	
	/**
	 * 将可访问的url地址转存到七牛上
	 * @param prefix 七牛地址前缀 如：wx/img/(不已"/"开头)
	 * @param url 文件url地址 必填
	 * @param bucket 七牛存储空间 必填
	 * @return 七牛url地址
	 */
	public String convertUrlToQiniu(String prefix, String url, String bucket);
}




