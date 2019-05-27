package com.db.sys.controller;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.db.common.vo.JsonResult;
import com.db.sys.entity.SysUser;
import com.db.sys.service.SysUserService;

@Controller
@RequestMapping("/user/")
public class SysUserController {
	  @Autowired
	  private SysUserService sysUserService;
	 
	  @RequestMapping("doUserListUI")
	  public String doUserListUI(){
		  return "sys/user_list";
	  }
	  @RequestMapping("doUserEditUI")
	  public String doUserEditUI(){
		  return "sys/user_edit";
	  }
	  @RequestMapping("doUpdatePwdUI")
      public String doUpdatePwdUI(){
    	  return "sys/pwd_edit";
      }
	  
	  @RequestMapping("doLogin")
	  @ResponseBody
	  public JsonResult doLogin(String username,
			  String password){
          //1.对用户信息进行封装
		  UsernamePasswordToken token=
		  new UsernamePasswordToken(username,password);
		  //token.setUsername(username);
		  //token.setPassword(passWord.toCharArray());
		  //2.提交用户信息(交给shiro框架进行认证)
		  Subject subject=SecurityUtils.getSubject();
		  subject.login(token);
		  //token会传递给securityManager
		  //securityManager会将token传递给认证管理器.
		  return new JsonResult("login ok");
	  }
	  
	  @RequestMapping("doUpdateObject")
	  @ResponseBody
	  public JsonResult doUpdateObject(SysUser entity,
			  Integer[] roleIds){
		  sysUserService.updateObject(entity,
				  roleIds);
		  return new JsonResult("update ok");
	  }
	  
	  @RequestMapping("doFindObjectById")
	  @ResponseBody
	  public JsonResult doFindObjectById(
			  Integer id){
		  return new JsonResult(
		 sysUserService.findObjectById(id));
	  }
	  
	  @RequestMapping("doSaveObject")
	  @ResponseBody
	  public JsonResult doSaveObject(
			  SysUser entity,Integer[]roleIds){
		  sysUserService.saveObject(entity, roleIds);
		  return new JsonResult("save ok");
	  }
	  
	  @RequestMapping("doValidById")
	  @ResponseBody
	  public JsonResult doValidById(Integer id,
			  Integer valid){
		  SysUser user=(SysUser)SecurityUtils.getSubject()
				  .getPrincipal();//获取登录用户的身份信息
		  sysUserService.validById(id, valid,user.getUsername());
		  return new JsonResult("update ok");
	  }
	  
	  @ResponseBody
	  @RequestMapping("doFindPageObjects")
	  public JsonResult doFindPageObjects(
			  String username,Integer pageCurrent){
		  return new JsonResult(
		  sysUserService.doFindPageObjects(
				  username, pageCurrent));
	  }
}
