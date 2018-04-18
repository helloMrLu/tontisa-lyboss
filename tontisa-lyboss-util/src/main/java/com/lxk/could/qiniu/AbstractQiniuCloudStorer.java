package com.lxk.could.qiniu;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import com.tontisa.common.lang.Dates;
import com.tontisa.common.lang.Strings;

/**
 * 基于七牛的云存储ＡＰＩ部分公共实现
 * @author renlei
 */
public abstract class AbstractQiniuCloudStorer implements CloudStorer {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public String convertUrlToQiniu(String prefix, String url, String bucket) {
		Assert.hasText(bucket, "Qiniu bucket Can not be Empty!!!");
		InputStream is = null;
		ByteArrayOutputStream outStream = null;
		String qiniuUrl = null;
		try {
			//CloudStorer cloudStorer = Springs.getBean("cloudStorer");
			URL path = new URL(url);
			is = path.openStream();
			
			outStream = new ByteArrayOutputStream();  
			//创建一个Buffer字符串  
	        byte[] buffer = new byte[1024];
	        //每次读取的字符串长度，如果为-1，代表全部读取完毕  
	        int len = 0;  
	        //使用一个输入流从buffer里把数据读取出来  
	        while( (len=is.read(buffer)) != -1 ){  
	            //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度  
	            outStream.write(buffer, 0, len);  
	        }
	        
			byte[] data = outStream.toByteArray();
			//文件存储路径 "/wx/img/2016/10/16/xxxx"
			StringBuilder sbFile = new StringBuilder();
			if (Strings.isNotEmpty(prefix)) {
				sbFile.append(prefix);
			}
			sbFile.append(Dates.yyyyMMdd(new Date())).append("/").append(getUUIDFileName());
			String fileKey =  sbFile.toString();
			//String bucket = (String)CustomizedPropertyConfigurer.getContextProperty("image.bucket");
			upload(bucket, fileKey, data);
			qiniuUrl = getFileURL(bucket, fileKey);
		} catch (MalformedURLException e) {
			logger.error("url格式错误!url:" + url);
			logger.error("错误信息:" + e.getMessage());
		} catch (IOException e) {
			logger.error("通过url获取文件流io错误!url:" + url);
			logger.error("错误信息:" + e.getMessage());
		} catch(Exception e) {
			logger.error("错误信息:" + e.getMessage());
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
				}
			}
			is = null;
			if (outStream != null) {
				try {
					outStream.close();
				} catch (IOException e) {
				}
			}
			outStream = null;
		}
		if (Strings.isEmpty(qiniuUrl)) {
			//转换失败-->返回原url
			qiniuUrl = url;
		}
		return qiniuUrl;
	}
	
	/**
	 * 返回UUID
	 * @param fileName
	 * @return
	 */
	private static String getUUIDFileName(){
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replace("-", "");
	}
}
