package com.lxk.Vo;

import java.util.Date;

public class UserVo {
	private static final long serialVersionUID = 1L;
	
	private String nickName;		//昵称
	private String realName;		//姓名
	private String salt;			//密码加密盐
	private String password;		//密码
	private String sex;
	private String phone;			//电话
	private String company;			//公司名
    private String avatorUrl;		//头像
    private String cardUrl;			//名片
    private Short source;			//来源
    private String job;				//职务
    
    private Date vipStartTime;    
    private Date vipEndTime;
    private Byte isVip; 	//是否是vip
    private Date beVipTime; 	//成会员时间
    
    private Byte level; 	//用户等级

    private String mail;
    private String qq;
    private String weixin;			
    private String weixinQrcode;	//微信二维码
    private String invitationCode; 	//邀请码
    private Integer inviterId; 	//邀请人id
    private String inviterName; 	//邀请人姓名
    private String remark;			//备注
    
    private Date createTime;
    private Integer createUserId;
    private String createUserName;
    private Date updateTime;
    private Integer updateUserId;
    private String updateUserName;
    private Byte isDelete;
    private Date deleteTime;		//是否删除(0-未删除;1-删除)
    private Integer deleteUserId;
    private String deleteUserName;
    
    private String code;//验证码
    private String token;//token
	private String password2;		//密码2
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getAvatorUrl() {
		return avatorUrl;
	}
	public void setAvatorUrl(String avatorUrl) {
		this.avatorUrl = avatorUrl;
	}
	public String getCardUrl() {
		return cardUrl;
	}
	public void setCardUrl(String cardUrl) {
		this.cardUrl = cardUrl;
	}
	public Short getSource() {
		return source;
	}
	public void setSource(Short source) {
		this.source = source;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public Date getVipStartTime() {
		return vipStartTime;
	}
	public void setVipStartTime(Date vipStartTime) {
		this.vipStartTime = vipStartTime;
	}
	public Date getVipEndTime() {
		return vipEndTime;
	}
	public void setVipEndTime(Date vipEndTime) {
		this.vipEndTime = vipEndTime;
	}
	public Byte getIsVip() {
		return isVip;
	}
	public void setIsVip(Byte isVip) {
		this.isVip = isVip;
	}
	public Date getBeVipTime() {
		return beVipTime;
	}
	public void setBeVipTime(Date beVipTime) {
		this.beVipTime = beVipTime;
	}
	public Byte getLevel() {
		return level;
	}
	public void setLevel(Byte level) {
		this.level = level;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getWeixin() {
		return weixin;
	}
	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}
	public String getWeixinQrcode() {
		return weixinQrcode;
	}
	public void setWeixinQrcode(String weixinQrcode) {
		this.weixinQrcode = weixinQrcode;
	}
	public String getInvitationCode() {
		return invitationCode;
	}
	public void setInvitationCode(String invitationCode) {
		this.invitationCode = invitationCode;
	}
	public Integer getInviterId() {
		return inviterId;
	}
	public void setInviterId(Integer inviterId) {
		this.inviterId = inviterId;
	}
	public String getInviterName() {
		return inviterName;
	}
	public void setInviterName(String inviterName) {
		this.inviterName = inviterName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}
	public String getCreateUserName() {
		return createUserName;
	}
	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Integer getUpdateUserId() {
		return updateUserId;
	}
	public void setUpdateUserId(Integer updateUserId) {
		this.updateUserId = updateUserId;
	}
	public String getUpdateUserName() {
		return updateUserName;
	}
	public void setUpdateUserName(String updateUserName) {
		this.updateUserName = updateUserName;
	}
	public Byte getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Byte isDelete) {
		this.isDelete = isDelete;
	}
	public Date getDeleteTime() {
		return deleteTime;
	}
	public void setDeleteTime(Date deleteTime) {
		this.deleteTime = deleteTime;
	}
	public Integer getDeleteUserId() {
		return deleteUserId;
	}
	public void setDeleteUserId(Integer deleteUserId) {
		this.deleteUserId = deleteUserId;
	}
	public String getDeleteUserName() {
		return deleteUserName;
	}
	public void setDeleteUserName(String deleteUserName) {
		this.deleteUserName = deleteUserName;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getPassword2() {
		return password2;
	}
	public void setPassword2(String password2) {
		this.password2 = password2;
	}
    
	
}
