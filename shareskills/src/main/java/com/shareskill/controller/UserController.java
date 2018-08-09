package com.shareskill.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.shareskill.model.TUser;

public interface UserController {

	/**用户注册填表*/
	public String writeReisForm(Model model);
	/**用户填表*/
	public String registerUser(Model model, @Valid TUser user, BindingResult bindingResult);
	/**
	 * 用户填表
	 */
	public  String writeSignForm(Model model);
	/**用户登录*/
	public String signInMember(Model model,HttpServletRequest request,@ModelAttribute TUser user);
	
	/**用户登出*/
	public String logoutMember(HttpServletRequest request);
	/**编辑指定用户信息*/
	public String editMember(@ModelAttribute TUser user,Model model);
	
	/**修该指定用户信息*/
	public String updateMember(HttpServletRequest request,@ModelAttribute TUser user,BindingResult bindingResult, Model model);
	
	/**查看指定用户信息*/
	public ModelAndView viewMember(HttpServletRequest request);
	public String viewMyInf(HttpServletRequest request,Model model);
	/**删除指定用户信息
	/**查看所有用户信息*/
	public ModelAndView browseMember();
}
