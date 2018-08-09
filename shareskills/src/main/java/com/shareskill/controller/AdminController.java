package com.shareskill.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.shareskill.model.TAdmin;

/**管理员控制器*/
public interface AdminController {
	/**管理员注册*/
	public ModelAndView registerAdmin(Model model);
	

	
	/**管理员登出*/
	public ModelAndView logoutAdmin(HttpServletRequest request);
	
	/**保存管理员信息 */
	public String saveAdmin(@ModelAttribute TAdmin admin);
	/**管理员登录*/
	public ModelAndView signinAdmin(Model model, HttpServletRequest request,@ModelAttribute TAdmin admin);
	
}
