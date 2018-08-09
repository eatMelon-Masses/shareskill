package com.shareskill.controllerimp;



import javax.annotation.Resource;
import javax.annotation.Resources;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


import com.shareskill.controller.AdminController;
import com.shareskill.model.TAdmin;
import com.shareskill.service.AdminService;
import com.shareskill.serviceImp.AdminServiceImp;
@Controller
public class AdminControllerImp implements AdminController {
	private final String REGISTERVIEW="admin/AdminEditForm";
	private final String LOGINADMINVIEW="";
	private final String LOGOUTVIEWVIEW="";
	private final String SAVEADMINVIEW="index";
	private	final String ADMINHOMEVIEW="";
	private String rand;
	
	@Resource
	private AdminService adminService;
	private  final Log logger=LogFactory.getLog(AdminControllerImp.class);
	

	@RequestMapping("/admin/registerAdmin")
	@Override
	public ModelAndView registerAdmin(Model model) {
		// TODO Auto-generated method stub
		TAdmin admin=new TAdmin();
		model.addAttribute("admin", admin);
		logger.info("registerAdmin");
		
		return new ModelAndView(REGISTERVIEW);
	}
	
	@RequestMapping("/admin/signinAdmin")
	@Override
	public ModelAndView signinAdmin(Model model,HttpServletRequest request,@ModelAttribute TAdmin admin) {
		// TODO Auto-generated method stub
		if(!rand.equalsIgnoreCase((String)request.getSession().getAttribute("rand"))){
			
			return new ModelAndView(LOGINADMINVIEW);
		}else{
			//Admin tempAdmin = dataResourceService.adminLogin(model.getLoginName(), MD5.MD5Encode(model.getLoginPwd()));
			TAdmin tempAdmin =  adminService.adminLogin(admin.getZh(), admin.getMm());
			if(tempAdmin!=null){
				//ServletActionContext.getRequest().getSession().setAttribute("admin",tempAdmin);
				request.getSession().setAttribute("admin", tempAdmin);
				logger.info("login_success");
				logger.info(tempAdmin.toString());
				return new ModelAndView(ADMINHOMEVIEW);
			}else{
				logger.info("login_fail");
				return new ModelAndView(LOGINADMINVIEW);				
			}
		}
		//return null;
	}

	@Override
	public ModelAndView logoutAdmin(HttpServletRequest request) {
		// TODO Auto-generated method stub
		request.getSession().invalidate();
		return new ModelAndView(LOGINADMINVIEW);
	}
	@RequestMapping("/admin/saveadmin")
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
