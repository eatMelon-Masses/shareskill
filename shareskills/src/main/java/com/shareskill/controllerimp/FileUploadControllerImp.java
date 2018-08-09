package com.shareskill.controllerimp;

import com.shareskill.controller.UploadController;
import com.shareskill.model.TDataresource;
import com.shareskill.model.TUser;
import com.shareskill.service.DataResourceService;
import com.shareskill.service.UserService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
@Controller
public class FileUploadControllerImp implements UploadController {
    @Autowired
    private UserService service;
    @Autowired
    DataResourceService dataResourceService;
    private final static Log logger = LogFactory.getLog(FileUploadControllerImp.class);
    @RequestMapping("/upLoadFile")
    @Override
    public String uploadPicture(HttpServletRequest request, HttpServletResponse response,@RequestParam(value = "upload",required = false) MultipartFile file) {
        logger.info("/uploadPicture"+request.getParameterNames());
        TUser sessionUser=(TUser) request.getSession().getAttribute("mumber");
        String userDataPath=null;

        logger.info("userDataPath"+userDataPath);

        Enumeration list =  request.getParameterNames();
        while(list.hasMoreElements()){
            logger.info(list.nextElement().toString());
        }
        logger.info("file"+file);

        /*    * 上传文件的属性*/
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
                String tempFilename = String.valueOf(System.currentTimeMillis());
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
        sb.append(errMsg);
        //sb.append("</script>\n");
        out.println(sb.toString());
        out.flush();
        out.close();
        return null;
    }
    @RequestMapping(value="/download")
    @Override
    public ResponseEntity<byte[]> downloadFile(HttpServletRequest request,@RequestParam Integer resId, Model model)throws Exception {
        logger.info("resid"+resId);
        //下载文件路径
        TDataresource data=null;
        String filename=null;
        // TUser sessionUser=(TUser) request.getSession().getAttribute("mumber");
        String userDataPath=null;
        if (resId!=null){
            data = dataResourceService.loadDataResById(resId);
            filename=data.getZylj();
        }
        String basePath=request.getServletContext().getRealPath("/uploadfile").replaceAll("\\\\", "/");
        //获取用户真实存储路径
        //  userDataPath = basePath+"/"+sessionUser.getZh() + sessionUser.getId()+"/";
        logger.info("userdatapath:"+userDataPath+ " filename :"+filename);
        File file = new File(basePath+filename);
        HttpHeaders headers = new HttpHeaders();
        //下载显示的文件名，解决中文名称乱码问题
        String downloadFielName = new String(filename.getBytes("UTF-8"),"iso-8859-1");
        //通知浏览器以attachment（下载方式）打开图片
        headers.setContentDispositionFormData("attachment", downloadFielName);
        //application/octet-stream ： 二进制流数据（最常见的文件下载）。
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),
                headers, HttpStatus.CREATED);
    }

    @Override
    public String test(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }

    public UserService getService() {
        return service;
    }

    public void setService(UserService service) {
        this.service = service;
    }

    public DataResourceService getDataResourceService() {
        return dataResourceService;
    }

    public void setDataResourceService(DataResourceService dataResourceService) {
        this.dataResourceService = dataResourceService;
    }
}
