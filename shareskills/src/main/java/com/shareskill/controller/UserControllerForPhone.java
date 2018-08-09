package com.shareskill.controller;

import com.shareskill.model.TUser;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface UserControllerForPhone {
	/**用户注册*/
	public List<Object> registerMember(TUser user, BindingResult bindingResult);

	/**用户登录*/
	public List<Object> signInMember(HttpServletResponse response,Model model, HttpServletRequest request, @ModelAttribute TUser user,BindingResult bindingResult);

	/**用户登出*/
	public void logoutMember(HttpServletResponse response,HttpServletRequest request);
	/**编辑指定用户信息*/
	public void editMember(HttpServletResponse response,TUser user, Model model);

	/**修该指定用户信息*/
	public List<Object> updateMember( TUser user,BindingResult bindingResult,String oldPwd);
	
	/**查看指定用户信息*/
	public List<Object> viewMember(HttpServletResponse response,@ModelAttribute TUser user,BindingResult bindingResult);
	
	
	/**查看所有用户信息*/
	public void browseMember(HttpServletResponse response);
	/**查看当前已登录用户*/
	public List<Object> viewLoginedUser(HttpServletRequest httpServletRequest);
	/** 用户头像上传*/
	public List<Object> uploaadPerssonImg(HttpServletRequest request, HttpServletResponse response, Model model, @RequestParam(value = "upload",required = false) MultipartFile file,Integer type);
}
