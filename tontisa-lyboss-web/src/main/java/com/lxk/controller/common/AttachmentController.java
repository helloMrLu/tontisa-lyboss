package com.lxk.controller.common;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lxk.base.Result;
import com.lxk.could.qiniu.QiNiuUtils;
import com.lxk.could.qiniu.UploadStatus;
import com.lxk.util.Exception;
import com.lxk.util.FileUtil;
import com.lxk.util.ResultUtil;
import com.qiniu.http.Response;
import com.tontisa.common.collection.Lists;
import com.tontisa.common.lang.Dates;
import com.tontisa.common.lang.Strings;
/**
 * 附件入口，提供基于七牛的uploadtoken生成，及文件生成等
 */
@Controller
@RequestMapping("/attach")
public class AttachmentController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(AttachmentController.class);
	
	/**
	 * 获取上传文件token，并设置回调
	 * 必须是用户登录之后才能调用
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/token/create",method=RequestMethod.POST)
	@ResponseBody
	public Result createUploadToken(String bucket,String key,Long expired, Map<String,Object> attributes) {
		logger.info("默认bucket->" + config.getDefaultBucket());
		if (Strings.isEmpty(bucket)) {
			//默认bucket
			bucket = this.config.getDefaultBucket();
		}
		logger.info("获取token begin...bucket->" + bucket);
		QiNiuUtils qu=new QiNiuUtils(config);
		Map r = qu.createUploadToken(bucket, key, 1l, expired, attributes);
		return ResultUtil.success(r);
	}
	
	/**
	 * 服务端自己上传图片
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unused" })
	@RequestMapping(value="/attachUpload")
	@ResponseBody
	public Result uploadTest(String bucket,String prefix,Long expired, Map<String,Object> attributes,String path) {
		if(Strings.isEmpty(path)){
			//path="C:\\Users\\PVer\\Pictures\\Camera Roll\\123.jpg";
			path="https://cdn.op114.com/compose/2017-11-22/3f3d8d8d81174d5cb680698ad987b200.jpg";
			//throw Exception.makeServiceException("20009");
		}
		if (Strings.isEmpty(bucket)) {
			//默认bucket
			bucket = this.config.getDefaultBucket();
		}
		byte[] b = null;
		if(path.contains("http://")||path.contains("https://")){
			b=FileUtil.url2Byte(path);
		}else{
			b=FileUtil.file2Byte(path);
		}
		if(b==null){
			throw Exception.makeServiceException("20010");
		}
		if (Strings.isEmpty(prefix)) {
			prefix="compose/"+Dates.yyyyMMdd(new Date())+"/"+UUID.randomUUID().toString().trim().replaceAll("-", "")+".jpg";
		}
		String qiniuUrl = null;
		if(b!=null){
			QiNiuUtils qu=new QiNiuUtils(config);
			UploadStatus s=qu.upload(bucket,prefix,b);
			qiniuUrl = qu.getFileURL(prefix);
		}
		return ResultUtil.success(qiniuUrl);
	}
	
	
	
	
	@RequestMapping(value="/file/delete")
	@ResponseBody
	public Result deleteUploadedFile(String bucket,String key) {
		Response r = null;
		if(Strings.isEmpty(key)){
			//key="http://p7dluzko9.bkt.clouddn.com/compose/2018-04-19/7c205fbf0d2e4ef0903b709ed6d93726.jpg";
			throw Exception.makeServiceException("20009");
		}
		if (Strings.isNoneBlank(key)){
			QiNiuUtils qu=new QiNiuUtils(config);
			r= qu.remove(bucket, Lists.of(key.split(",")));
		}
		return r==null?ResultUtil.error(500, "删除失败"):ResultUtil.success();
	}
}



