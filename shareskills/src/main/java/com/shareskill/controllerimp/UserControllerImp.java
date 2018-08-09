package com.shareskill.controllerimp;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


import com.shareskill.model.TBlog;
import com.shareskill.service.BlogsService;
import com.shareskill.service.CategoryService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.shareskill.controller.UserController;
import com.shareskill.model.TUser;
import com.shareskill.service.UserService;
@Controller
public class UserControllerImp implements UserController {
	@Resource
	private UserService userService;

	private final String REGISTERVIEW="/webs/enroll";
	//登录界面
	private final String LOGINUSERVIEW="/webs/login";
	private final String LOGOUTUSERVIEW="";
	private final String SAVEUSERVIEW="index";
	private final String HOMEVIEW="forward:/showHome";
	private final String BROWSEMEMBERVIEW="";
	private final String EDITMEMBERVIEW="";
	private final String SEEDETIALMEMBERVIEW="";
	private String rand="";
	private final Log logger = LogFactory.getLog(UserControllerImp.class);




	/**
	 * 用户注册填表
	 * @param model
	 * @return
	 */
	@RequestMapping("/writeReisForm")
	@Override
	public String writeReisForm(Model model) {
		// TODO Auto-generated method stub
		TUser user= new TUser();
		model.addAttribute("user",user);
		logger.info("writeReisForm");
		return REGISTERVIEW;
	}

	/**
	 * 用户填表
	 *
	 * @param model
	 * @param user
	 */
	@RequestMapping("/registerUser")
	@Override
	public String registerUser(Model model, @Validated(TUser.TUserRuleb.class)@ModelAttribute("user") TUser user, BindingResult bindingResult) {
		logger.info(user.toString());
		String massage = "注册成功";
/*		if (bindingResult.hasErrors()){
			logger.info("输入信息有误");
			model.addAttribute("user", new TUser());
			return REGISTERVIEW;
		}*/
		if (bindingResult.hasErrors()) {
			List<ObjectError> errorList = bindingResult.getAllErrors();
			for (ObjectError error : errorList) {
				logger.info(error.getDefaultMessage());
				massage=error.getDefaultMessage();

			}
			model.addAttribute("user", user);
			model.addAttribute("massage", massage);
			return REGISTERVIEW;
		}
		user.setJybs(String.valueOf(0));
		user.setCsny(new Date());
		if (userService.saveOrUpdateMember(user)) {
			model.addAttribute("massage", massage);
			return HOMEVIEW;
		} else {
			massage = "注册失败,请从新尝试";
			model.addAttribute("user", new TUser());
			model.addAttribute("massage", massage);
			return REGISTERVIEW;
		}


	}

	/**
	 * 用户登录填表
	 *
	 * @param model
	 */
	@RequestMapping("/writeSignForm")
	@Override
	public String writeSignForm(Model model) {
		TUser tempUser = new TUser();
		model.addAttribute("user", tempUser);
		return LOGINUSERVIEW;
	}

	/**
	 * 用户登录
	 * @param model
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping("/signInMember")
	@Override
	public String signInMember(Model model,HttpServletRequest request,@ModelAttribute TUser user) {
		// TODO Auto-generated method stub
		String massage=null;
		logger.info("zh:"+user.getZh()+"mm"+user.getDlmm());
		TUser sessionUser=(TUser) request.getSession().getAttribute("mumber");
		//logger.info("sessionobject:"+rand.equalsIgnoreCase((String)sessionUser.getZh()));

		if (null != sessionUser && !user.getZh().equalsIgnoreCase((String) (sessionUser.getZh()) ) ) {//如果当前登录用户非已登录用户
			TUser tempUser = userService.memberLogin(user.getZh(), user.getDlmm());
			if (tempUser != null) {
				request.getSession().setAttribute("mumber", tempUser);
				logger.info("login_success");
				logger.info(tempUser.toString());
				model.addAttribute("massage", "用户登录成功");
				return HOMEVIEW;
			} else {
				logger.info("login_fail");
				model.addAttribute("massage", "账号或密码错误");
				model.addAttribute("user", new TUser());
				return LOGINUSERVIEW;
			}
/*			model.addAttribute("user", user);
			logger.info("sessionuser" + sessionUser);
			//model.addAttribute("user", new TUser());
			request.getSession().setAttribute("mumber",user);
			return LOGINUSERVIEW;*/

		} else if (null != sessionUser && user.getZh().equalsIgnoreCase((String) (sessionUser.getZh()) )){//如果垱店登录的用户是已登录用户
			model.addAttribute("massage", "登录成功");
			return HOMEVIEW;
		}else{//如果当前无已登录用户
			TUser tempUser = userService.memberLogin(user.getZh(), user.getDlmm());
			if (tempUser != null) {
				request.getSession().setAttribute("mumber", tempUser);
				logger.info("login_success");
				logger.info(tempUser.toString());
				model.addAttribute("massage", "登录成功");
				return HOMEVIEW;
			} else {
				logger.info("login_fail");
				model.addAttribute("massage", "登录失败");
				model.addAttribute("user", new TUser());
				return LOGINUSERVIEW;
			}
		}

	}
	@RequestMapping("/logoutMember")
	@Override
	public String logoutMember(HttpServletRequest request) {
		// TODO Auto-generated method stub
		logger.info(request.getSession().getAttribute("user"));
		request.getSession().invalidate();
		return HOMEVIEW;
	}
	@Override
	public String editMember(TUser user, Model model) {
		// TODO Auto-generated method stub
			logger.info("updateMember"+user.getId());
			TUser tempUser=userService.loadMember(user.getId());
			if(tempUser.getId()!=null){
				try {
					BeanUtils.copyProperties(user,tempUser);
					model.addAttribute("user", user);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
					return EDITMEMBERVIEW;
			}else {
				return BROWSEMEMBERVIEW;
			}



	}
	@RequestMapping("/updateUser")
	@Override
	public String updateMember(HttpServletRequest request,@ModelAttribute("newUser") TUser newUser,BindingResult bindingResult,Model model) {
		// TODO Auto-generated method stub
	//	JSONObject massage = new JSONObject();
		//massage.put("massage", "修改成功");
		StringBuffer message = new StringBuffer();
		String oldpwd = request.getParameter("oldpwd");
		TUser user=newUser;
		logger.info("nweUser"+newUser.toString());
		//通过用户提供的id查询用户对象
		TUser tempUser =(TUser) request.getSession().getAttribute("mumber");
		//修改对象内部状态
		if (user.getYhnc()!=null){
				tempUser.setYhnc(user.getYhnc());
			message.append("用户昵称,");
		}

		if(user.getCsny()!=null){
			message.append("出生年月,");
			tempUser.setCsny(user.getCsny());
		}
		if(user.getGrjj()!=null && user.getGrjj().trim().length()>0){
			message.append("个人简介,");
			tempUser.setGrjj(user.getGrjj().trim());
		}
		//if(user.getZh()!=null)tempUser.setZh(user.getZh());
		if(user.getDlmm()!=null&&user.getDlmm().trim().length()>0) {
			if (oldpwd.equals(tempUser.getDlmm())) {
				tempUser.setDlmm(user.getDlmm());
				message.append("密码,");
			} else {
			//	message.delete(0, message.length());//清空
				message.append("密码修改失败");
				model.addAttribute("massage",message);
				return "/webs/editorPerson";
			}
		}
		logger.info("密码比对"+user.getDlmm().equals(tempUser.getDlmm()));

			if(userService.saveOrUpdateMember(tempUser)){
				logger.info("updateMember_success");
			}else{
				message.delete(0, message.length());
				message.append("修改失败,未知错误");
				model.addAttribute("massage",message);
				logger.info("updateMember_failure");
				return "/webs/editorPerson";
			}

		model.addAttribute("massage",message);
		return  "forward:/showMypersionInf";
	}

	@Override
	public ModelAndView viewMember(HttpServletRequest request) {
		// TODO Auto-generated method stub
		logger.info("viewMember");
		Integer id=(Integer)request.getAttribute("id");
		if(id!=null){//id不为空
			TUser tempUser = userService.loadMember(id);
			TUser user = new TUser();
			BeanUtils.copyProperties(tempUser, user);
			if(tempUser!=null){
				ModelAndView modelAndView = new ModelAndView(SEEDETIALMEMBERVIEW);
				modelAndView.addObject("user", user);
				logger.info("viewMember_success");
				return modelAndView;
			}else {
				logger.info("viewMember_fail");
				return new ModelAndView(BROWSEMEMBERVIEW);
			}
		}else{
			logger.info("viewMember_fail");
			return new ModelAndView(BROWSEMEMBERVIEW);
		}

	}
	@RequestMapping("/viewMyInf")
	@Override
	public String viewMyInf(HttpServletRequest request, Model model) {
		TUser sessionUser = (TUser) request.getSession().getAttribute("mumber");
		JSONObject massage = new JSONObject();
		model.addAttribute("newUser", new TUser());
		if (sessionUser != null) {
			massage.put("massage", "用户未登陆,或登录超时");
			model.addAttribute(massage);

		}
		model.addAttribute("oldUser",sessionUser);
		return "/webs/editorPerson";
	}

	@Override

	@RequestMapping("/browseMember")
	public ModelAndView browseMember() {
		// TODO Auto-generated method stub
		List<TUser> tempUserList = userService.browseMember();
		ModelAndView modelAndView = new ModelAndView(BROWSEMEMBERVIEW);
		modelAndView.addObject("userList", tempUserList);
		return modelAndView;

	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}



}
