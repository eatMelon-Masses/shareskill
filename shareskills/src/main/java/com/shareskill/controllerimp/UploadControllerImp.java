package com.shareskill.controllerimp;

import com.shareskill.controller.UploadController;
import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
import java.util.List;

@Controller
public class UploadControllerImp implements UploadController {
    private final static Log logger = LogFactory.getLog(UploadControllerImp.class);


    @Override
    @RequestMapping("/uploadimage")
    public String
    uploadPicture(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "upload",required = false) MultipartFile file) {
        logger.info("/uploadPicture"+request.getParameterNames());
        Enumeration list =  request.getParameterNames();
        while(list.hasMoreElements()){
            logger.info(list.nextElement().toString());
        }
        logger.info("file"+file);
        //上传文件类型 image表示图片(gif,jpg,png,bmp)文件,flash表示swf文件
        String type = "image";
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
            sb.append("<script type=\"text/javascript\">\n");
            //取得UserFiles文件夹对应的物理路径
            String basePath=request.getServletContext().getRealPath("/upload").replaceAll("\\\\", "/");
            logger.info("basePath:"+basePath);
            logger.info("uploadFileName"+uploadFileName);
            File saveDir = new File(basePath);
            if(!saveDir.exists())saveDir.mkdirs();
            //处理上传文件
            if (uploadFileName==null||uploadFileName.trim().length()<1){
                //未选择上传文件时的错误提示信息
                errMsg = "对不起，文件不能为空,请选择文件然后上传！";
                errMsg =  new String(errMsg.getBytes(),"iso8859-1");
                sb.append("window.parent.CKEDITOR.tools.callFunction(1,'','"+errMsg+"');\n");
            }else{
                String[] tmpNames = uploadFileName.split("\\.");
                String tempFilename = String.valueOf(System.currentTimeMillis());
                String extName = null;
                if (tmpNames!=null && tmpNames.length>1){
                    extName = tmpNames[tmpNames.length-1].toLowerCase();
                    tempFilename = tempFilename+"."+extName;
                    if (type.equals("image")){
                        if ("gif,jpg,png,bmp".indexOf(extName)==-1){
                            //图片格式不正确时的错误提示信息
                            errMsg = "对不起，图片格式不正确,请重新选择正确的图片文件！";
                            errMsg =  new String(errMsg.getBytes(),"iso8859-1");
                            sb.append("window.parent.CKEDITOR.tools.callFunction(1,'','"+errMsg+"');\n");
                        }else{
                            FileCopyUtils.copy(file.getBytes(), new File(saveDir, tempFilename));
                            //上传成功后返回文件的引用地址
                            sb.append("window.parent.CKEDITOR.tools.callFunction(1,'"+request.getServletContext().getContextPath()+"/upload/"+tempFilename+"');\n");
                        }
                    }else if(type.equals("flash")){
                        if (!"swf".equals(extName)){
                            //Flash文件不正确时的错误提示信息
                            errMsg = "对不起，Flash文件不正确,请重新选择正确的Flash文件！";
                            errMsg =  new String(errMsg.getBytes(),"iso8859-1");
                            sb.append("window.parent.CKEDITOR.tools.callFunction(1,'','"+errMsg+"');\n");
                        }else{
                            FileCopyUtils.copy(file.getBytes(), new File(saveDir, tempFilename));
                            //上传成功后返回文件的引用地址
                            sb.append("window.parent.CKEDITOR.tools.callFunction(1,'"+request.getServletContext().getContextPath()+"/upload/"+tempFilename+"');\n");
                        }
                    }else{
                        //文件格式不正确时的错误提示信息
                        errMsg = "对不起，文件格式不正确,请重新选择正确的文件！";
                        errMsg =  new String(errMsg.getBytes(),"iso8859-1");
                        sb.append("window.parent.CKEDITOR.tools.callFunction(1,'','"+errMsg+"');\n");
                    }
                }else{
                    //文件格式不正确时的错误提示信息
                    errMsg = "对不起，文件格式不正确,请重新选择正确的文件！";
                    errMsg =  new String(errMsg.getBytes(),"iso8859-1");
                    sb.append("window.parent.CKEDITOR.tools.callFunction(1,'','"+errMsg+"');\n");
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
            sb.append("window.parent.CKEDITOR.tools.callFunction(1,'','"+errMsg+"');\n");
        }
        sb.append("</script>\n");
        out.println(sb.toString());
        out.flush();
        out.close();
        return null;
    }

    @Override
    public ResponseEntity<byte[]> downloadFile(HttpServletRequest request, Integer resId, Model model) throws Exception {
        return null;
    }

    @RequestMapping("/test")
    @Override
    public String test(HttpServletRequest request, HttpServletResponse response) {
        logger.info("/test");
        return null;
    }
}
