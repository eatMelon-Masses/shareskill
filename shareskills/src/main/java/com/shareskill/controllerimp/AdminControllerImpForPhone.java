package com.shareskill.controllerimp;


import com.shareskill.controller.AdminControllerForPhone;
import com.shareskill.model.TAdmin;
import com.shareskill.service.AdminService;
import com.shareskill.utils.JsonUtils;
import net.sf.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminControllerImpForPhone implements AdminControllerForPhone {
	private final String REGISTERVIEW="admin/AdminEditForm";
	private final String LOGINADMINVIEW="";
	private final String LOGOUTVIEWVIEW="";
	private final String SAVEADMINVIEW="index";
	private	final String ADMINHOMEVIEW="";
	private String rand=new String("");
	
	@Resource
	private AdminService adminService;
	private  final Log logger=LogFactory.getLog(AdminControllerImpForPhone.class);
	

	@RequestMapping("/admin/registerAdmin/gson")
	@Override
	public ModelAndView registerAdmin(Model model) {
		// TODO Auto-generated method stub
		TAdmin admin=new TAdmin();
		model.addAttribute("admin", admin);
		logger.info("registerAdmin");
		
		return new ModelAndView(REGISTERVIEW);
	}
	@ResponseBody
	@RequestMapping("/admin/signinAdmin/json")
	@Override
	public List<Object> signinAdmin(Model model, HttpServletRequest request, @ModelAttribute TAdmin admin) {
		JSONObject massage = new JSONObject();
		List<Object> list = new ArrayList<>();
		massage.put("operation", "success");
				TAdmin tempAdmin =  adminService.adminLogin(admin.getZh(), admin.getMm());
			if(tempAdmin!=null){
				request.getSession().setAttribute("admin", tempAdmin);
				logger.info("login_success");
				logger.info(tempAdmin.getZh()+tempAdmin.getMm());

			}else{
				massage.put("operation", "failure");
			}
			list.add(massage);
			list.add(tempAdmin);
		return list;
	}
	@ResponseBody
	@RequestMapping("/admin/logoutAdmin")
	@Override
	public List<Object> logoutAdmin(HttpServletRequest request) {
		// TODO Auto-generated method stub
		request.getSession().invalidate();
		List<Object> list=new ArrayList();
		JSONObject masage = new JSONObject();
		masage.put("massage","退出成功");
		list.add(masage);
		return list;
	}
	@RequestMapping("/admin/saveadmin/json")
	@Override
	public String saveAdmin(@ModelAttribute TAdmin admin) {
		// TODO Auto-generated method stub
		logger.info("saveAdmin");
		System.out.println(adminService+admin.getMm()+admin.getZh());
		adminService.saveOrUpdateAdmin(admin);

		return SAVEADMINVIEW;
	}
	
	public AdminService getAdminService() {
		return adminService;
	}
	@Resource
	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}


}
