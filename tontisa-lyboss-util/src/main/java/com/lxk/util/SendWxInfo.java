package com.lxk.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tontisa.common.lang.Dates;
import com.tontisa.common.lang.Strings;


import net.sf.json.JSONObject;

public class SendWxInfo {/*
	private final Logger logger = LoggerFactory.getLogger(getClass());
	//测试环境
	//String[] adminTousers={"o8xmFwon76PaApdSF5L8kX7qV3W8","o8xmFwpWRyw0km_-Ut22LlG7uy-8","o8xmFwgmnap7P6p-74-8_lblV5DA","o8xmFwrhSwSeukr6dry7CkB6eraY"};
	//生产环境
	//String[] adminTousers={"ouKh2jkguTeu-7JSgNjujDTKk_eg","ouKh2jkhKln6gQULea9WYdvvNpmA","ouKh2jmW013QqKSy7E750SZ_I258","ouKh2jscwSdk48HH9mKVu-qyNTuA"};	
	String url="https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=";
	
	*//**
	 * 发送注册提醒信息
	 * @param ACCESS_TOKEN
	 * @param magUser
	 * @param remark
	 * @param template 
	 * @return
	 *//*
	public String sendRegistInfo(String[] adminTousers,String ACCESS_TOKEN,User magUser,String remark, String template,String urltz) {
		url=url+ACCESS_TOKEN;		
		urltz=urltz+"&Key=op114";
		String res=null;
		logger.info("SendWxInfo  ACCESS_TOKEN="+ACCESS_TOKEN);
		for(String touser:adminTousers){
			logger.info("SendWxInfo regist touser="+touser);
			JSONObject json=new JSONObject();
			JSONObject data=new JSONObject();
			JSONObject first=new JSONObject();
			JSONObject keyword1=new JSONObject();
			JSONObject keyword2=new JSONObject();
			JSONObject keyword3=new JSONObject();
			JSONObject remarkJson=new JSONObject();
			first.put("value", "旅游人助手新用户注册成功");
			first.put("color", "#FF6600");			
			keyword1.put("value", "旅游人"+magUser.getPhone().substring(magUser.getPhone().length()-4,magUser.getPhone().length()));
			keyword1.put("color", null);
			keyword2.put("value", magUser.getPhone());
			keyword2.put("color", "#FF6600");
			String date=Dates.format("yyyy-MM-dd HH:mm",new Date());
			String source = null;
			if(magUser.getSource()==1){
				source="网站注册";
			}else if(magUser.getSource()==2){
				source="移动版注册";
			}else if(magUser.getSource()==3){
				source="ERP注册";
			}else if(magUser.getSource()==4){
				source="转发信息";
			}
			String keyword3String=date;
			if(!Strings.isEmpty(magUser.getInviterName())){
				keyword3String=keyword3String+"\n邀请人:"+magUser.getInviterName()+"("+magUser.getCompany()+")";
			}
			keyword3.put("value",keyword3String +"\n来源:"+source);
			keyword3.put("color", null);
			remarkJson.put("value",remark);
			remarkJson.put("color", null);			
			data.put("first", first);
			data.put("keyword1", keyword1);
			data.put("keyword2", keyword2);
			data.put("keyword3", keyword3);
			data.put("remark", remarkJson);
			json.put("touser", touser);
			json.put("template_id", template);
			json.put("url",urltz.toString());
			json.put("miniprogram",null);
			json.put("data",data);
			res=HttpUtil.sendPost(url, json.toString());
			logger.info("SendWxInfo regist res="+res);
			logger.info("SendWxInfo regist res="+json.toString());
		}
		return res;
	}
	
	*//**
	 * 发送认证失败审核信息
	 * @param ACCESS_TOKEN
	 * @param magUser
	 * @param remark
	 * @param mag_weixin_identification_redirect 
	 * @param template 
	 * @return
	 *//*
	public String sendIdentificationFailInfo(String[] adminTousers,String ACCESS_TOKEN,MagUser magUser,String remark, String mag_weixin_identification_redirect, String template) {
		url=url+ACCESS_TOKEN;	
		logger.info("SendWxInfo  ACCESS_TOKEN="+ACCESS_TOKEN);
		String res=null;
		String idmd5=HttpUtil.EncoderByMd5(magUser.getId().toString(), "101");
		StringBuffer urltz=new StringBuffer(mag_weixin_identification_redirect);
		urltz.append("&Key=");urltz.append(idmd5);urltz.append("&id=");urltz.append(magUser.getId());
		for(String touser:adminTousers){
			logger.info("SendWxInfo IdentificationFail touser="+touser);
			JSONObject json=new JSONObject();
			JSONObject data=new JSONObject();
			JSONObject first=new JSONObject();
			JSONObject keyword1=new JSONObject();
			JSONObject keyword2=new JSONObject();
			JSONObject keyword3=new JSONObject();
			JSONObject remarkJson=new JSONObject();
			first.put("value", "用户认证资料等待审核");
			first.put("color", "#FF6600");			
			keyword1.put("value", magUser.getRealName()==null?magUser.getNickName():magUser.getRealName());
			keyword1.put("color", "#FF6600");
			keyword2.put("value", magUser.getPhone()+"\n公司:"+magUser.getCompany()+"\n职位/部门:"+magUser.getJob());
			keyword2.put("color", null);
			String date=Dates.format("yyyy-MM-dd HH:mm",new Date());
			keyword3.put("value",date);
			keyword3.put("color", null);
			remarkJson.put("value",remark);
			remarkJson.put("color", null);			
			data.put("first", first);
			data.put("keyword1", keyword1);
			data.put("keyword2", keyword2);
			data.put("keyword3", keyword3);
			data.put("remark", remarkJson);
			json.put("touser", touser);
			json.put("template_id", template);
			json.put("url",urltz.toString());
			json.put("miniprogram",null);
			json.put("data",data);
			res=HttpUtil.sendPost(url, json.toString());
			logger.info("SendWxInfo IdentificationFail json="+json);
			logger.info("SendWxInfo IdentificationFail res="+json.toString());
		}
		return res;
	}
	
	*//**
	 * 发送认证手动审核成功信息
	 * @param ACCESS_TOKEN
	 * @param magUser
	 * @param remark
	 * @param weixinIdentifyRedirect 
	 * @param template 
	 * @return
	 *//*
	public String sendIdentificationSuccessInfo(String[] adminTousers,String ACCESS_TOKEN,MagUser magUser,String remark,String template) {
		url=url+ACCESS_TOKEN;		
		logger.info("SendWxInfo  ACCESS_TOKEN="+ACCESS_TOKEN);
		String res=null;
		ArrayList<String> list=new ArrayList<String>(Arrays.asList(adminTousers));
		ArrayList<String> list2=list;
		if(!Strings.isEmpty(magUser.getOpenId())){
			list.add(magUser.getOpenId());
		}
		for(String touser:list){
			logger.info("SendWxInfo IdentificationSuccess touser="+touser);
			JSONObject json=new JSONObject();
			JSONObject data=new JSONObject();
			JSONObject first=new JSONObject();
			JSONObject keyword1=new JSONObject();
			JSONObject keyword2=new JSONObject();
			JSONObject keyword3=new JSONObject();
			JSONObject remarkJson=new JSONObject();
			if(list2.contains(touser)){
				first.put("value", "用户认证成功!");
			}else{
				first.put("value", "恭喜您已经通过旅游人认证!");	
			}
			first.put("color", "#FF6600");			
			String k1="名片认证\n姓名:"+magUser.getRealName()==null?magUser.getNickName():magUser.getRealName();
			k1=k1+"\n手机:"+magUser.getPhone()+"\n公司:"+magUser.getCompany()+"\n职位/部门:"+magUser.getJob();
			keyword1.put("value",k1);
			keyword1.put("color", null);
			keyword2.put("value", "认证通过");
			keyword2.put("color", "#FF6600");
			String date=Dates.format("yyyy-MM-dd HH:mm",new Date());
			keyword3.put("value",date);
			keyword3.put("color", null);
			remarkJson.put("value",remark);
			remarkJson.put("color", null);			
			data.put("first", first);
			data.put("keyword1", keyword1);
			data.put("keyword2", keyword2);
			data.put("keyword3", keyword3);
			data.put("remark", remarkJson);
			json.put("touser", touser);
			json.put("template_id", template);
			json.put("miniprogram",null);
			json.put("data",data);
			res=HttpUtil.sendPost(url, json.toString());
			logger.info("SendWxInfo IdentificationSuccess json="+json);
			logger.info("SendWxInfo IdentificationSuccess res="+json.toString());
		}
		return res;
	}
	

	public String sendWeiXinInfo(JSONObject json,String ACCESS_TOKEN) {
		logger.info("SendWxInfo  ACCESS_TOKEN="+ACCESS_TOKEN);
		url=url+ACCESS_TOKEN;
		String res=HttpUtil.sendPost(url, json.toString());
		return res;
	}
	
	public static void main(String[] args) {
	        //发送 GET 请求
	       // String s=HttpUtil.sendGet("http://dev.op110.com/yun/main?xwl=N43M1S4VE0MN&name=18485796324&createTime=2017-7-12 16:07:12&code=f42be8fac6a59c9268e6d93b79a1eabd");
	       // JSONObject jsonArray1= JSONObject.fromObject(s);
	        //jsonArray1.get("");
	        //String ss=HttpUtil.EncoderByMd5("18485796324", "2017-7-12 16:07:12");
		 	String path="https://api.weixin.qq.com/cgi-bin/user/info?";  
		 	String param="access_token=faxwyybsk4zus5IaTcDLAm97rv9TxJ8CJpCM9ot6zSBYF63zRW-FtRH4X9h4WlnNc6K5R9uAmjHWk5uabl2q87az9fA3t_2GRcI75l2wuBo2yMk_0XZkjDddzghF8LFBNPXiAAABDL&openid=o8xmFwo86C0XV_LDnqaUxQabaI4o&lang=zh_CN";
		 	//String rs=HttpUtil.sendPost(path, param);
		 	//String rs=SendWxInfo.sendGet(path+param);
	       // System.out.println(rs);
	        
    }

*/}
