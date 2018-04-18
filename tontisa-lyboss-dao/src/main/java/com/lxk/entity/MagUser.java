package com.lxk.entity;

import java.util.Date;



public class MagUser{
	private static final long serialVersionUID = -3083565813762138271L;

    private Integer id;
    private String nickName;		//昵称
    private String salt;			//密码加密盐
    private String password;		//密码
    private String realName;		//姓名
    private String phone;			//电话
    private String company;			//公司名
    private Integer point;			//点数
    private Short status;			//状态
    private Short checkStatus;			//审核状态
    private String invitationCode; 	//邀请码
    private Integer inviterId; 	//邀请人id
    private String inviterName; 	//邀请人姓名
    private Integer inviterGain; 	//邀请人所得
    private String job;				//职务
    private String mail;
    private String qq;
    private String weixin;			
    private String weixinQrcode;	//微信二维码
    private String gender;
    private String address;
    private String avatorUrl;		//头像
    private String cardUrl;			//名片
    private Short source;			//来源
    private String remark;			//备注
    private Long usedSize; 	//已使用大小
    private Long totalSize; 	//总大小
    private Byte isVip; 	//是否是vip
    private Date beVipTime; 	//成会员时间
    private Byte level; 	//用户等级
    private Integer checkUserId;
    private String checkUserName;
    private Date checkTime;
    private String isPass;			//是否通过
    private Date vipStartTime;    
    private Date vipEndTime;
    private Byte openHomepage;		//是否开通个人主页
    private Date homepageStartTime; //开通开始时间
    private Date homepageEndTime;   //开通结束时间
    private Byte isManager;         //是否为企业管理员
    
    //登录注册接受其他参数 begin
    private String openType;		//登录注册类型:mobile,email,qq,wx
    private String username;		//用户名(登录注册时接受参数)
    private String password2;		//确认密码
    private String newPassword;		//新密码
    private String imgCode;			//图片验证码
    private String code;			//短信或者邮箱验证码
    private String token;			//登录成功之后的token
    private String key;				//图形验证码生成key
    private String auth;				//erp身份验证串
    //登录注册接受其他参数 begin
    
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
    private String isNew;
    
    private String openId;				//微信开放id
    
    
    public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
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
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
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
	public Integer getPoint() {
		return point;
	}
	public void setPoint(Integer point) {
		this.point = point;
	}
	public Short getStatus() {
		return status;
	}
	public void setStatus(Short status) {
		this.status = status;
	}
	public String getInvitationCode() {
		return invitationCode;
	}
	public void setInvitationCode(String invitationCode) {
		this.invitationCode = invitationCode;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
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
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
	public Integer getCheckUserId() {
		return checkUserId;
	}
	public void setCheckUserId(Integer checkUserId) {
		this.checkUserId = checkUserId;
	}
	public String getCheckUserName() {
		return checkUserName;
	}
	public void setCheckUserName(String checkUserName) {
		this.checkUserName = checkUserName;
	}
	public Date getCheckTime() {
		return checkTime;
	}
	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
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
	public String getIsPass() {
		return isPass;
	}
	public void setIsPass(String isPass) {
		this.isPass = isPass;
	}
	public String getOpenType() {
		return openType;
	}
	public void setOpenType(String openType) {
		this.openType = openType;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword2() {
		return password2;
	}
	public void setPassword2(String password2) {
		this.password2 = password2;
	}
	public String getImgCode() {
		return imgCode;
	}
	public void setImgCode(String imgCode) {
		this.imgCode = imgCode;
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
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public Short getCheckStatus() {
		return checkStatus;
	}
	public void setCheckStatus(Short checkStatus) {
		this.checkStatus = checkStatus;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getAuth() {
		return auth;
	}
	public void setAuth(String auth) {
		this.auth = auth;
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
	public Integer getInviterGain() {
		return inviterGain;
	}
	public void setInviterGain(Integer inviterGain) {
		this.inviterGain = inviterGain;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getIsNew() {
		return isNew;
	}
	public void setIsNew(String isNew) {
		this.isNew = isNew;
	}

	public Long getUsedSize() {
		return usedSize;
	}
	public void setUsedSize(Long usedSize) {
		this.usedSize = usedSize;
	}
	public Long getTotalSize() {
		return totalSize;
	}
	public void setTotalSize(Long totalSize) {
		this.totalSize = totalSize;
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
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public Byte getOpenHomepage() {
		return openHomepage;
	}
	public void setOpenHomepage(Byte openHomepage) {
		this.openHomepage = openHomepage;
	}
	public Date getHomepageStartTime() {
		return homepageStartTime;
	}
	public void setHomepageStartTime(Date homepageStartTime) {
		this.homepageStartTime = homepageStartTime;
	}
	public Date getHomepageEndTime() {
		return homepageEndTime;
	}
	public void setHomepageEndTime(Date homepageEndTime) {
		this.homepageEndTime = homepageEndTime;
	}
	public Byte getIsManager() {
		return isManager;
	}
	public void setIsManager(Byte isManager) {
		this.isManager = isManager;
	}
	
	
}