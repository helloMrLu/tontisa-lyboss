package com.lxk.controller.authority;

import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lxk.Vo.UserVo;
import com.lxk.base.Result;
import com.lxk.constant.MagConstant;
import com.lxk.controller.common.BaseController;
import com.lxk.entity.User;
import com.lxk.util.Exception;
import com.lxk.util.MyMD5;
import com.lxk.util.ResultUtil;
import com.lxk.util.TokenUtils;
import com.lxk.web.util.CookieUtils;
import com.tontisa.common.lang.Strings;

import net.sf.json.JSONObject;



@RestController
@RequestMapping("/login")
public class LoginController extends BaseController{
	private final Logger logger = LoggerFactory.getLogger(getClass());
	@RequestMapping("/login")
	public Result login(HttpServletRequest request, HttpServletResponse response,User user){
		//校验逻辑
		if (Strings.isEmpty(Strings.trim(user.getNickName()))) {
			throw Exception.makeServiceException("20001");
		}
		if (user.getSource() == null&&user.getSource() != MagConstant.REG_TYPE_SITE && 
				user.getSource() != MagConstant.REG_TYPE_MOBILE && user.getSource() != MagConstant.REG_TYPE_ERP) {
			throw Exception.makeServiceException("20002");
		}
		if (Strings.isEmpty(Strings.trim(user.getPassword()))&&Strings.isEmpty(Strings.trim(user.getCode()))) {
			throw Exception.makeServiceException("20004");
		}
		User realUser;
		//手机格式校验
		if(Strings.isNumeric(user.getNickName())){
			if(user.getNickName().matches(MagConstant.MOBILE_METCH)){
				throw Exception.makeServiceException("20003");
			}else{
				if(Strings.isNotEmpty(user.getCode())&&Strings.isNotEmpty(user.getPassword())){
					//验证码登录，此处校验验证码
				}
			}
		}
		realUser=userServer.getUser(user);
		if(realUser!=null){
			if(!Strings.isEmpty(Strings.trim(user.getPassword()))&&Strings.isEmpty(Strings.trim(user.getCode()))){
				String password = MyMD5.getMD5Code(user.getPassword() + user.getSalt());
				if(!password.equals(realUser.getPassword())) {
					throw Exception.makeServiceException("20005");
				}
			}
		}else{
			throw Exception.makeServiceException("20006");
		}
		//登录成功1. 发放token并设置token至redis；
		dispatchToken(realUser);
		//2.写cookie操作
		loginCookieConfig(request, response, realUser);
		//loginLog(request,realMagUser);
		return ResultUtil.success(null);
	}
	
	/**
	 * app端手机注册--基本注册信息
	 * 后续可能会有邮箱注册啥的到时候再说
	 * @param request
	 * @param user
	 * @param model
	 * @return
	 */
	@RequestMapping(value={"/app/regist"}, method=RequestMethod.POST)
	@ResponseBody
	public Result appRegist(HttpServletRequest request, UserVo magUser){
		logger.info("注册begin");
		validateCheck(magUser);
		if(Strings.isNumeric(magUser.getNickName())){
			if(magUser.getNickName().matches(MagConstant.MOBILE_METCH)){
				throw Exception.makeServiceException("20003");
			}else{
				magUser.setPhone(magUser.getNickName());
			}
		}
		User u=new User();
		BeanUtils.copyProperties(magUser,u);
		u = userServer.registerUser(u);
		logger.info("注册信息-->{}",JSONObject.fromObject(u));
		logger.info("注册end");
		return ResultUtil.success();
	}
	
	
	/**
	 * 登录成功写cookie操作
	 * @param request
	 * @param response
	 * @param realMagUser
	 */
	private void loginCookieConfig(HttpServletRequest request, HttpServletResponse response, User realMagUser) {
		CookieUtils.set(response,MagConstant.TOKEN_NAME, realMagUser.getToken(),null,TokenUtils.TOKEN_EXPIRE);
	}
	
	@RequestMapping("/loginOut")
    public Result logout(HttpServletRequest request,
                       HttpServletResponse response) {
        //1. 从cookie里查询
        Cookie cookie = CookieUtils.get(request,MagConstant.TOKEN_NAME);
        if (cookie != null) {
            //2. 清除redis
        	StringBuilder tokenNoBuilder = new StringBuilder();
        	String finalToken = tokenNoBuilder.append(TokenUtils.TOKEN_PREFIX).append(":").append(cookie.getValue()).toString();
            redisTemplate.opsForValue().getOperations().delete(finalToken);
            //3. 清除cookie
            CookieUtils.set(response,MagConstant.TOKEN_NAME, null,null,0);
        }

        return ResultUtil.success(null);
    }
	
	/**
	 * 退出
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = {"/logout2"})
	@ResponseBody
	public Result logout2(HttpServletRequest request, HttpServletResponse response) {
		Cookie userCookie = CookieUtils.getCookie(request.getCookies(), MagConstant.TOKEN_NAME);
		if (null != userCookie) {
			redisTemplate.opsForValue().getOperations().delete(userCookie.getValue());
		}
		//清空fallbackUrl的cookie
		//CookieUtils.updateExpiry(response, request.getCookies(), MagConstant.fallbackUrl, 0);
		return ResultUtil.success(null);
	}
	
	/**
	 * 登录成功派发token
	 * @param realMagUser
	 */
	private void dispatchToken(User realMagUser) {
		//如果已经有关联用户的token存在，废除
		//String oldToken = redisTemplate.opsForValue().get(TokenUtils.getMagUserTokenCacheKey(realMagUser.getId().toString()));
		/*if (Strings.isNotEmpty(oldToken)) {
			shardedJedisPoolService.del(oldToken);
			
			StringBuilder logInfoBuilder = new StringBuilder();
			logInfoBuilder.append("用户->").append(realMagUser.getId()).append("重新登录！oldToken->").append(oldToken).append("被删除!");
			logger.info(logInfoBuilder.toString());
		}*/
		
		//设置token并绑定用户基本信息存储到redis
		JSONObject userJson = new JSONObject();
		userJson.put("id", realMagUser.getId());
		userJson.put("nickName", Strings.defaultString(realMagUser.getNickName()));
		userJson.put("company", Strings.defaultString(realMagUser.getCompany()));
		userJson.put("phone", Strings.defaultString(realMagUser.getPhone()));

		StringBuilder tokenNoBuilder = new StringBuilder();
		tokenNoBuilder.append(realMagUser.getId()).append("_").append(Strings.remove(UUID.randomUUID().toString(),"-"));
		String tokenNo = tokenNoBuilder.toString();
		tokenNoBuilder.setLength(0);
		String finalTokenNo = tokenNoBuilder.append(TokenUtils.TOKEN_PREFIX).append(":").append(tokenNo).toString();
		redisTemplate.opsForValue().set(finalTokenNo, userJson.toString(), TokenUtils.TOKEN_EXPIRE);
		//redisTemplate.opsForValue().set(TokenUtils.getMagUserTokenCacheKey(realMagUser.getId().toString()), finalTokenNo);
		//设置当前登录用户token
		realMagUser.setToken(tokenNo);
	}
	
	/**
	 * 注册基本校验
	 * @param magUser
	 */
	private void validateCheck(UserVo userVo) {
		//校验逻辑
		String username = Strings.trim(userVo.getNickName());
		String password = Strings.trim(userVo.getPassword());
		Short source = userVo.getSource();
		String password2 = Strings.trim(userVo.getPassword2());
		if (Strings.isEmpty(username)) {
			throw Exception.makeServiceException("20001");
		}
		//手机格式校验
		/*if (!username.matches(MagConstant.MOBILE_METCH)) {
			throw Exceptions.makeServiceException("10016");
		}*/
		if (Strings.isEmpty(password)) {
			throw Exception.makeServiceException("20004");
		}
		if (Strings.isEmpty(password2)) {
			throw Exception.makeServiceException("20004");
		}
		if (source == null) {
			throw Exception.makeServiceException("20002");
		}
		if (source != MagConstant.REG_TYPE_SITE && source != MagConstant.REG_TYPE_MOBILE && source != MagConstant.REG_TYPE_ERP) {
			throw Exception.makeServiceException("20002");
		}
		if (!Strings.equals(password, password2)) {
			throw Exception.makeServiceException("20012");
		}
	}
}
