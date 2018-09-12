package com.shareskill.controllerimp;

import com.shareskill.controller.UserControllerForPhone;
import com.shareskill.model.*;
import com.shareskill.service.*;
import com.shareskill.utils.DateFormatUtils;
import com.shareskill.utils.ImageHelper;
import com.shareskill.utils.JsonUtils;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.executable.ValidateOnExecution;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.*;

@Controller
public class UserControllerImpForPhone implements UserControllerForPhone {
	@Resource
	private UserService userService;
	@Autowired
	BlogsService blogsService;
	@Autowired
	BlogCommentService blogCommentService;
	@Autowired
	BlogoSphereService blogoSphereService;
	@Autowired
	DataResourceService dataResourceService;
	//private final String REGISTERVIEW="views/UserEditForm";
/*	private final String REGISTERVIEW="admin/AdminEditForm";
	private final String LOGINUSERVIEW="";
	private final String LOGOUTUSERVIEW="";
	private final String SAVEUSERVIEW="index";
	private	final String HOMEVIEW="";
	private final String BROWSEMEMBERVIEW="";
	private final String EDITMEMBERVIEW="";
	private final String SEEDETIALMEMBERVIEW="";*/
	private String rand;
	private final Log logger = LogFactory.getLog(UserControllerImpForPhone.class);
	final static String RESULT = "result";
	final static String MESSAGE = "message";
	final static String TRUE = "true";
	final static String FALSE = "false";

	/*@RequestMapping("/registerUser/json")
	@Override
	public Model registerMember(HttpServletResponse response, @ModelAttribute TUser user, @RequestParam(value = "csrq") String strDate) {
		// TODO Auto-generated method stub
		Date tempDate=null;
		logger.info("writeReisForm");
		JSONArray jsonArray =new JSONArray();
		if(strDate!=null){
			try{
				logger.info("date:"+strDate);
				 tempDate =DateFormat.getDateInstance(DateFormat.SHORT,new Locale("zh")).parse(strDate);

			}catch (Exception e){
				e.printStackTrace();
				return;
			}
			user.setCsny(tempDate);
			user.setJybs(new String("0"));
			logger.info(tempDate.toString());
		}
		userService.saveOrUpdateMember(user);

		JSONObject jsonObject=JSONObject.fromObject(user);
		jsonObject.remove("csny");
		jsonObject.put("csny",DateFormat.getDateInstance(DateFormat.SHORT,new Locale("zh")).format(user.getCsny()));
		jsonArray.add(jsonObject);
		JsonUtils.addDateToJsonArray(jsonArray,"writeReisForm","success");
		JsonUtils.ajaxJson(jsonArray.toString(),response);
		logger.info("user:"+jsonArray.toString());
	}*/
	@RequestMapping("/json/registerUser")
	@ResponseBody
	@Override
	public List<Object> registerMember(@Validated(TUser.TUserRuleA.class)@ModelAttribute TUser user, BindingResult bindingResult) {
		logger.info(user.toString());
		List<Object> jsonList = new ArrayList<>();
		StringBuffer message = new StringBuffer();
		Map<String, Object> mapList = new HashMap<>();
		mapList.put(RESULT,FALSE);
		mapList.put(MESSAGE, message);
		jsonList.add(mapList);
		if (bindingResult.hasErrors()) {//遍历错误信息
			List<ObjectError> errorList = bindingResult.getAllErrors();
			for (ObjectError error : errorList) {
				logger.info(error.getDefaultMessage());
				message.append(error.getDefaultMessage());
			}
			return jsonList;
		}
		user.setJybs(String.valueOf(0));
		//user.setCsny(new Date());

		if (null==userService.loadMemberByLoginName(user.getZh())&&userService.saveOrUpdateMember(user)) {
			mapList.put(RESULT, TRUE);
			message.append("注册成功");
		} else {
			message.append("注册失败,账号重复");
			return jsonList;
		}

	return jsonList;
	}
	@ResponseBody
	@RequestMapping("/signinMember/json")
	@Override
	public List<Object> signInMember(HttpServletResponse response, Model model, HttpServletRequest request, @Validated(TUser.TUserRuleC.class) TUser user, BindingResult bindingResult) {
		// TODO Auto-generated method stub
		List<Object> jsonList = new ArrayList<>();
		StringBuffer message = new StringBuffer();
		Map<String, Object> mapList = new HashMap<>();
		mapList.put(RESULT,FALSE);
		mapList.put(MESSAGE, message);
		jsonList.add(mapList);
		if (bindingResult.hasErrors()) {//遍历错误信息
			List<ObjectError> errorList = bindingResult.getAllErrors();
			for (ObjectError error : errorList) {
				logger.info(error.getDefaultMessage());
				message.append(error.getDefaultMessage());
			}
			return jsonList;
		}
			TUser tempUser = userService.memberLogin(user.getZh(), user.getDlmm());
			if(tempUser!=null){
				request.getSession().setAttribute("mumber", tempUser);
				//写入数据登录成功信息
				mapList.put(RESULT, TRUE);
				message.append("登录成功");
				jsonList.add(tempUser);
				logger.info(tempUser.toString());

			}
			else {

				message.append("用户尚未登录");
				return jsonList;
		}
		return jsonList;

	}
	@RequestMapping(value = "/logoutMemberByPhone")
	@Override
	public void logoutMember(HttpServletResponse response,HttpServletRequest request) {
		// TODO Auto-generated method stub
		request.getSession().invalidate();
		//生成json数据
		JSONObject jsonObject= new JSONObject();
		jsonObject.put("logoutMumber","success");
		//写入响应流
		JsonUtils.ajaxJson(jsonObject.toString(),response);

	}
	@Override
	public void editMember(HttpServletResponse response,TUser user, Model model) {
		// TODO Auto-generated method stub
		JSONArray jsonArray = new JSONArray();
		if(user.getId()!=null){
			logger.info("updateMember"+user.getId());
			TUser tempUser=userService.loadMember(user.getId());
			if(tempUser.getId()!=null){
				try {
					BeanUtils.copyProperties(user,tempUser);
					model.addAttribute("user", user);
					jsonArray.add(JsonUtils.getJsonObject().put("editMember","success"));
					jsonArray.add(user);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					JsonUtils.addDateToJsonArray(jsonArray,"editMember","failure");
				}

			}else {
				JsonUtils.addDateToJsonArray(jsonArray,"editMember","failure");
			}
			
			
		}else {
			logger.info("editMember_fail");
			JsonUtils.addDateToJsonArray(jsonArray,"editMember","failure");

		}
	}



	@RequestMapping(value = "/json/updateMember")
	@ResponseBody
	@Override
	public List<Object> updateMember(@Validated(TUser.TUserRuleD.class) TUser user, BindingResult bindingResult,@RequestParam(required = false) String oldPwd) {
		// TODO Auto-generated method stub
		List<Object> jsonList = new ArrayList<>();
		StringBuffer message = new StringBuffer();
		Map<String, Object> mapList = new HashMap<>();
		mapList.put(RESULT,FALSE);
		mapList.put(MESSAGE, message);
		jsonList.add(mapList);
		if (bindingResult.hasErrors()) {//遍历错误信息
			List<ObjectError> errorList = bindingResult.getAllErrors();
			for (ObjectError error : errorList) {
				logger.info(error.getDefaultMessage());
				message.append(error.getDefaultMessage());
			}
			return jsonList;
		}
		//通过用户提供的id查询用户对象
		TUser tempUser = userService.loadMember(user.getId());
		if (tempUser == null) {
			message.append("修改失败,不存在此用户信息,");
			return jsonList;
		}
		//修改昵称
		if (user.getYhnc()!=null&&user.getYhnc().trim().length()>0){
			mapList.put(RESULT, TRUE);
			message.append("昵称");
			tempUser.setYhnc(user.getYhnc());
		}
		//修改用户姓名
		if (user.getXm() != null && user.getXm().trim().length() > 0) {
			mapList.put(RESULT, TRUE);
			message.append("姓名,");
			tempUser.setXm(user.getXm());
		}
		//修改用户性别
		if (user.getXb() != null && user.getXb().trim().length() > 0) {
			mapList.put(RESULT, TRUE);
			message.append("性别,");
			tempUser.setXb(user.getXb());
		}
		//修改用户出生日期
		if(user.getCsny()!=null){
			mapList.put(RESULT, TRUE);
			message.append("出生日期,");
			tempUser.setCsny(user.getCsny());
		}
		//修改密码
		if(user.getDlmm()!=null&&user.getDlmm().trim().length()>0) {
			if (!tempUser.getDlmm().equals(oldPwd)) {
				message.append("密码修改失败,原密码输入有误");
				return jsonList;
			} else {
				tempUser.setDlmm(user.getDlmm());
				mapList.put(RESULT, TRUE);
				message.append("密码,");
			}
		}
		//修改个人简介
		if(user.getGrjj()!=null && user.getGrjj().trim().length()>0){
			message.append("个人简介,");
			mapList.put(RESULT, TRUE);
			tempUser.setGrjj(user.getGrjj().trim());
		}
		if(mapList.get(RESULT).equals(TRUE) && userService.saveOrUpdateMember(tempUser)){
				//mapList.put(RESULT,TRUE);
				message.append("修改成功");

		}else{

				logger.info("updateMember_failure");
				message.append("修改失败,未知错误错误,或用户未修改信息");
				return jsonList;
		}
		return jsonList;
	}
	@ResponseBody
	@RequestMapping(value = "/json/viewMember")
	@Override
	public List<Object> viewMember(HttpServletResponse response, @Validated(TUser.TUserRuleD.class)@ModelAttribute TUser user, BindingResult bindingResult) {
		// TODO Auto-generated method stub
		List<Object> jsonList = new ArrayList<>();
		StringBuffer message = new StringBuffer();
		Map<String, Object> mapList = new HashMap<>();
		mapList.put(RESULT,FALSE);
		mapList.put(MESSAGE, message);
		jsonList.add(mapList);
		if (bindingResult.hasErrors()) {//遍历错误信息
			List<ObjectError> errorList = bindingResult.getAllErrors();
			for (ObjectError error : errorList) {
				logger.info(error.getDefaultMessage());
				message.append(error.getDefaultMessage());
			}
			return jsonList;
		}
		logger.info("viewMember");
		Integer id=user.getId();
		TUser tempUser = userService.loadMember(id);

			if(tempUser!=null){//如果有此用户信息
				logger.info("viewMember_success");
				message.append("查看成功");
				mapList.put(RESULT,TRUE);
				jsonList.add(tempUser);
			}else {
				message.append("非法请求参数,找不到此用户信息");
				return jsonList;
			}
			return jsonList;
	}

	@Override
	//@ResponseBody
	@RequestMapping("/browseMember/json")
	public void browseMember(HttpServletResponse response) {
		// TODO Auto-generated method stub
		logger.info("browseMember/json");
		List<TUser> tempUserList = userService.browseMember();
		JSONArray jsonArray =new JSONArray();
		//修改json自动转换日期为年-月-日
		for (TUser user :tempUserList ){
			JSONObject tempObject =JSONObject.fromObject(user);
			Date tempDate = user.getCsny();
			tempObject.remove("csny");
			//tempObject.put("csny", DateFormat.getDateInstance(DateFormat.SHORT, Locale.CHINESE).format(user.getCsny()));

			if (tempDate!=null)
			tempObject.put("csrq",DateFormatUtils.changeDateToString(user.getCsny()));
			jsonArray.add(tempObject);
		}
		//写入响应流
		JsonUtils.ajaxJson(jsonArray.toString(),response);

	}
	@RequestMapping("/viewLoginedUser")
	@ResponseBody
	@Override
	public List<Object> viewLoginedUser(HttpServletRequest request) {
		List<Object> jsonList = new ArrayList<>();
		JSONObject massage = new JSONObject();
		TUser sessionUser=(TUser) request.getSession().getAttribute("mumber");
//		logger.info("sessionUser"+sessionUser.toString());
		if (sessionUser==null) {
			massage.put("operation", "failure");
		}
		jsonList.add(sessionUser);
		return jsonList;
	}
	@ResponseBody
	@RequestMapping("/json/uploaduserImg")
	@Override
	public List<Object> uploaadPerssonImg(HttpServletRequest request, HttpServletResponse response, Model model, @RequestParam(value = "upload") MultipartFile file, @RequestParam(defaultValue = "0") Integer type) {
		logger.info("/uploadPicture"+request.getParameterNames());
		List<Object> jsonList = new ArrayList<>();
		StringBuffer message = new StringBuffer();
		Map<String, Object> mapList = new HashMap<>();
		mapList.put(RESULT,FALSE);
		mapList.put(MESSAGE, message);
		jsonList.add(mapList);

		TUser sessionUser=(TUser) request.getSession().getAttribute("mumber");
		String userDataPath=null;
		String tempFilename=null;

		logger.info("userDataPath"+userDataPath);

/*		Enumeration list =  request.getParameterNames();
		while(list.hasMoreElements()){
			logger.info(list.nextElement().toString());
		}*/
		//  logger.info("file"+file);

		/*    * 上传文件的属性*/
		String uploadContentType=file.getContentType();	//上传文件的类型
		String uploadFileName=file.getOriginalFilename();//上传文件的文件名
		PrintWriter out = null;
		//创建一个StringBuffer对象存放返回给CKeditor的提示信息
		StringBuffer sb = new StringBuffer();

		try {
			//sb.append("<script type=\"text/javascript\">\n");
			//取得UserFiles文件夹对应的物理路径
			String basePath=request.getServletContext().getRealPath("uploadfile").replaceAll("\\\\", "/");
			//String basePath="/root/uploadfile".replaceAll("\\\\", "/");
			//获取用户真实存储路径
			userDataPath = basePath+"/"+sessionUser.getZh() + sessionUser.getId();
			logger.info("basePath:"+basePath+"userDataPath"+userDataPath);
			logger.info("uploadFileName"+uploadFileName);
			File saveDir = new File(basePath);
			if(!saveDir.exists())saveDir.mkdirs();
			//当前登录用户的文件夹是否创建
			File saveUserDataDir = new File(userDataPath);
			if(!saveUserDataDir.exists())saveUserDataDir.mkdirs();
			//处理上传文件
			if (uploadFileName==null||uploadFileName.trim().length()<1){
				//未选择上传文件时的错误提示信息
				message.append("对不起,头像图片不能为空,请选择图片从新上传");
				return jsonList;
			}else{
				String[] tmpNames = uploadFileName.split("\\.");
				tempFilename = String.valueOf(System.currentTimeMillis());
				String extName = null;
				if (tmpNames!=null && tmpNames.length>1){
					extName = tmpNames[tmpNames.length-1].toLowerCase();
					tempFilename = tempFilename+"."+extName;
					//FileCopyUtils.copy(file.getBytes(), new File(userDataPath, tempFilename));
					FileCopyUtils.copy(file.getBytes(), new File(userDataPath, tempFilename));
					if (type==0)
						ImageHelper.compress(file.getInputStream(), new File(userDataPath, tempFilename), 74, 74);//压缩图片为100*100像素的头像
					else
						ImageHelper.compress(file.getInputStream(), new File(userDataPath, tempFilename), 674, 120);//压缩图片为100*100像素的头像
					message.append("头像上传成功");
					mapList.put(RESULT,TRUE);
				}else{
					//文件格式不正确时的错误提示信息
					message.append("对不起，文件格式不正确,请重新选择正确的文件！");
					return jsonList;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			//文件上传失败时的错误提示信息
				message.append("头像上传失败,位置错误");
				return jsonList;

		}
		//保存上传的图片路径
		//用户图片
		if (type==0)
		sessionUser.setUserImg("/uploadfile/"+sessionUser.getZh() + sessionUser.getId()+"/"+tempFilename);
		else
			sessionUser.setUserBgImg("/uploadfile/"+sessionUser.getZh() + sessionUser.getId()+"/"+tempFilename);
		userService.saveOrUpdateMember(sessionUser);
		//sb.append("</script>\n");
		return jsonList;
	}
	@ResponseBody
	@RequestMapping("/json/delUser")
	@Override
	public List<Object> delUser(Integer id) {
		List<Object> jsonList = new ArrayList<>();
		StringBuffer message = new StringBuffer();
		Map<String, Object> mapList = new HashMap<>();
		mapList.put(RESULT,FALSE);
		mapList.put(MESSAGE, message);
		jsonList.add(mapList);
		TUser user = userService.loadMember(id);
		if (user!=null){
			List<TBlog> blogList = blogsService.loadBlogsByMember(user);

			List<TBlogcomment> tBlogcommentList = blogCommentService.browseBlogCommentByUserId(user.getId());
			List<BlogoSphere> blogoSpheres = blogoSphereService.browseBlogSphereByUser(user.getId());
			List<TDataresource> tDataresources = dataResourceService.browseDataResourceByUser(user.getId());
			if (blogList != null) {
				for (TBlog blog : blogList) {
					blogsService.delBlogById(blog.getId());
				}
			}
			if (tBlogcommentList!=null){
				for (TBlogcomment blogcomment:tBlogcommentList) {
					blogCommentService.delComment(blogcomment.getId());
				}
			}
			if (blogoSpheres != null) {
				for (BlogoSphere blogoSphere : blogoSpheres) {
					blogoSphereService.delUserWordById(blogoSphere.getId());
				}
			}
			if (tDataresources!=null){
				for (TDataresource tDataresource : tDataresources) {
					dataResourceService.delDataResById(tDataresource.getId());
				}
			}
			userService.delMember(user.getId());
			message.append("删除账号成功,所有相关数据已清空");
			mapList.put(RESULT,TRUE);
		}else{
			message.append("删除账号失败,不存在此账号");

		}
		return jsonList;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}



}
