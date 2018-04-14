package com.tontisa.entity;

import java.util.Date;


public class MagUserBind{
	private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer userId;		//用户名
    private String type;		//绑定类型
    private Date bindTime;		//绑定时间
    private Integer status;		//状态
    private String openId;		//第三方用户id
    private String unionId;		//第三方联盟id
    private String nickName;	//昵称
    private String avatorUrl;	//头像地址
    private String location;	//地区
    private String gender;		//性别

    private Byte subscribe;
    private String city;    		//用户所在城市
    private String country;    //用户所在国家
    private String province;   //用户所在省份
    private String language;    //用户的语言，简体中文为zh_CN
    private Date subscribeTime;//微信关照时间
    private String remark;    //微信备注
    private Integer groupid;//微信分组
    private String tagidList;    //微信标签
    
    private Date createTime;
    private Integer createUserId;
    private String createUserName;
    private Date updateTime;
    private Integer updateUserId;
    private String updateUserName;
    private Byte isDelete;
    private Date deleteTime;
    private Integer deleteUserId;
    private String deleteUserName;
    
    private String subscribeTimeStr;//微信关照时间
    
}