package com.shareskill.controller;

import com.shareskill.model.TAdmin;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**管理员控制器*/
public interface AdminControllerForPhone {
	/**管理员注册*/
	public ModelAndView registerAdmin(Model model);



	/**管理员登出*/
	public List<Object> logoutAdmin(HttpServletRequest request);

	/**保存管理员信息 */
	public String saveAdmin(@ModelAttribute TAdmin admin);
	/**管理员登录*/
	public List<Object> signinAdmin(Model model,HttpServletRequest request, @ModelAttribute TAdmin admin);
	
}
