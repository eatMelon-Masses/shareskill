package com.shareskill.controllerimp;

import com.shareskill.controller.psersonController;
import com.shareskill.model.BlogoSphere;
import com.shareskill.model.TBlog;
import com.shareskill.model.TBlogcomment;
import com.shareskill.model.TUser;
import com.shareskill.service.BlogCommentService;
import com.shareskill.service.BlogoSphereService;
import com.shareskill.service.BlogsService;
import com.shareskill.service.UserService;
import net.sf.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.List;
@Controller
public class psersonControllerImpl implements psersonController {
    @Autowired
    private BlogoSphereService blogoSphereService;
    @Autowired
    private BlogsService blogService;
    @Autowired
    private UserService userService;
    @Autowired
    private BlogCommentService blogCommentService;

    static final Log logger = LogFactory.getLog(psersonControllerImpl.class);
    /**
     * 个人信息中心
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/showMypersionInf")
    @Override
    public String showMyPsersonInf(HttpServletRequest request, Model model) {
        TUser sessionUser = (TUser) request.getSession().getAttribute("mumber");
        if (sessionUser == null) {
            model.addAttribute("massage", "请先登录");
            return "forward:/showHome";
        }
        JSONObject massage = new JSONObject();
        massage.put("massage", "操作成功");
        TUser user = (TUser) request.getSession().getAttribute("mumber");
        List<TBlogcomment> bcList=null;//我的评论
        List<TBlog> blogList=null;//我的博客
        List<BlogoSphere> bpList=null;//説説
        logger.info("user" + user);
        if (user != null) {
            logger.info("user" + user);
            if (user.getUserImg() != null) {
               // String basePath=request.getServletContext().getRealPath("/uploadfile").replaceAll("\\\\", "/")+"/";
                user.setUserImg(user.getUserImg());
                logger.info("userimguri"+user.getUserImg());
            }

            bcList = blogCommentService.browseBlogCommentByUserId(user.getId());
            blogList = blogService.loadBlogsByMember(user);
            bpList = blogoSphereService.browseBlogSphereByUser(user.getId());
        } else {
            massage.put("massage", "操作失败");
        }

        model.addAttribute("user", user);
        model.addAttribute("bcList", bcList);
        model.addAttribute("blogList", blogList);
        model.addAttribute("bpList", bpList);
        logger.info("bcList:"+bcList.size());
        logger.info("blogList"+blogList.size());
        logger.info("bpList"+bpList.size());
        return "/webs/person";
    }
   // @RequestMapping("/uploaduserImg")
    @Override
    public String uploaadPerssonImg(HttpServletRequest request, HttpServletResponse response, Model model, @RequestParam(value = "upload",required = false) MultipartFile file) {
       /* logger.info("/uploadPicture"+request.getParameterNames());
        TUser sessionUser=(TUser) request.getSession().getAttribute("mumber");
        String userDataPath=null;
        String tempFilename=null;

        logger.info("userDataPath"+userDataPath);

        Enumeration list =  request.getParameterNames();
        while(list.hasMoreElements()){
            logger.info(list.nextElement().toString());
        }
      //  logger.info("file"+file);

        *//*    * 上传文件的属性*//*
        String uploadContentType=file.getContentType();	//上传文件的类型
        String uploadFileName=file.getOriginalFilename();//上传文件的文件名
        PrintWriter out = null;
        //创建一个StringBuffer对象存放返回给CKeditor的提示信息
        StringBuffer sb = new StringBuffer();
        String errMsg = "对不起，文件上传失败！";
        try {

            out = response.getWriter();
            response.setCharacterEncoding("UTF-8");
            //sb.append("<script type=\"text/javascript\">\n");
            //取得UserFiles文件夹对应的物理路径
            String basePath=request.getServletContext().getRealPath("/uploadfile").replaceAll("\\\\", "/");
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
                errMsg = "对不起，文件不能为空,请选择文件然后上传！";
                errMsg =  new String(errMsg.getBytes(),"iso8859-1");
                // sb.append("window.parent.CKEDITOR.tools.callFunction(1,'','"+errMsg+"');\n");
            }else{
                String[] tmpNames = uploadFileName.split("\\.");
                 tempFilename = String.valueOf(System.currentTimeMillis());
                String extName = null;
                if (tmpNames!=null && tmpNames.length>1){
                    extName = tmpNames[tmpNames.length-1].toLowerCase();
                    tempFilename = tempFilename+"."+extName;
                    FileCopyUtils.copy(file.getBytes(), new File(userDataPath, tempFilename));
                    errMsg = "文件上传成功";
                    errMsg =  new String(errMsg.getBytes(),"iso8859-1");

                }else{
                    //文件格式不正确时的错误提示信息
                    errMsg = "对不起，文件格式不正确,请重新选择正确的文件！";
                    errMsg =  new String(errMsg.getBytes(),"iso8859-1");
                    // sb.append("window.parent.CKEDITOR.tools.callFunction(1,'','"+errMsg+"');\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            //文件上传失败时的错误提示信息
            try {
                errMsg =  new String(errMsg.getBytes(),"iso8859-1");
            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
            }
        }
        sessionUser.setUserImg("/"+sessionUser.getZh() + sessionUser.getId()+"/"+tempFilename);
        userService.saveOrUpdateMember(sessionUser);
        sb.append(errMsg);
        //sb.append("</script>\n");
        out.println(sb.toString());
        out.flush();
        out.close();*/
        return "/webs/person";
    }

}
