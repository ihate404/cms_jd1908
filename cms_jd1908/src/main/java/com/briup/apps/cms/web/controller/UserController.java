package com.briup.apps.cms.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.briup.apps.cms.bean.extend.BaseUserExtend;
import com.briup.apps.cms.service.IBaseUserService;
import com.briup.apps.cms.utils.Message;
import com.briup.apps.cms.utils.MessageUtil;
import com.briup.apps.cms.vm.UserVM;

import io.swagger.annotations.ApiOperation;

/**
 * 用户控制器
 * @author lenovo
 *
 */
@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private IBaseUserService baseUserService;
	
	
	@ApiOperation(value="登录") 
	@PostMapping(value="login")
public Message login(@RequestBody UserVM userVM) { 
		    //1.验证用户信息
		//产生一个token 缓存起来
		//返回
		Map<String,String> map= new HashMap<>();
		map.put("token", "admin-token");
		return MessageUtil.success(map);
		   }
	
	
	  @ApiOperation(value="退出")
	@PostMapping(value="logout") 
	public Message loginout() { 
		 //将缓存中的token移除
		  //2。其他
	return MessageUtil.success("success"); 
	}
	  
	  @ApiOperation(value="通过token获取用户信息")
	  @GetMapping(value="info") 
	  public Message info(String token) { 
		  //通过token获取用户信息
		  BaseUserExtend user = baseUserService.findById(1l);
		  return MessageUtil.success(user); 
	  }
	
	 
}
