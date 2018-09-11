package com.shareskill.controllerimp;

import com.shareskill.controller.DataController;
import com.shareskill.model.DataCategory;
import com.shareskill.model.TDataresource;
import com.shareskill.model.TUser;
import com.shareskill.service.DataCategoryService;
import com.shareskill.service.DataResourceService;
import com.shareskill.utils.InitPage;
import com.shareskill.utils.Page;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

@Controller
public class DataControllerImp implements DataController {
    @Autowired
    DataResourceService dataResourceService;
    @Autowired
    DataCategoryService dataCategoryService;
    private final static Log logger = LogFactory.getLog(DataControllerImp.class);


    /**
     * 填写数据表单
     *
     * @param model
     */
    @RequestMapping("/writeDataResource")
    @Override
    public String writeDataResource(Model model) {
        model.addAttribute("dataCategoryList", dataCategoryService.browseAllDataCategory());
        model.addAttribute("data", new TDataresource());
        return "/webs/creatresource";
    }

    /**
     * 新增资源数据
     *
     * @param model
     * @param data
     */
    @RequestMapping("/addDataResource")
    @Override
    public String addDataResource(HttpServletRequest request, HttpServletResponse response,Model model, @ModelAttribute TDataresource data,@RequestParam(value = "upload",required = false) MultipartFile file) {
        String massage=null;
        TUser sessionUser=(TUser) request.getSession().getAttribute("mumber");
        String userDataPath=null;
        String tempFilename=null;
        logger.info("userDataPath"+userDataPath);

        Enumeration list =  request.getParameterNames();
        while(list.hasMoreElements()){
            logger.info(list.nextElement().toString());
        }
        logger.info("file"+file);

        /*    * 上传文件的属性*/
        // String uploadContentType=file.getContentType();	//上传文件的类型
        String uploadFileName=file.getOriginalFilename();//上传文件的文件名
        //   PrintWriter out = null;
        //创建一个StringBuffer对象存放返回给CKeditor的提示信息
        StringBuffer sb = new StringBuffer();
        String errMsg = "对不起，文件上传失败！";
        try {

            // out = response.getWriter();
            response.setCharacterEncoding("UTF-8");
            //sb.append("<script type=\"text/javascript\">\n");
            //取得UserFiles文件夹对应的物理路径
            String basePath=request.getServletContext().getRealPath("/uploadfile").replaceAll("\\\\", "/");
            //获取用户真实存储路径
            userDataPath = basePath+"/root/"+sessionUser.getZh() + sessionUser.getId();
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
        //保存文本数据
        data.setScsj(new Date());//上传时间
        data.setDjcs(new Integer(0));//点击次数
        data.setZylj("/"+sessionUser.getZh() + sessionUser.getId()+"/"+tempFilename);
        //  String basePath=request.getServletContext().getRealPath("/uploadfile").replaceAll("\\\\", "/");

        //String userDataPath = basePath+"/"+sessionUser.getZh() + sessionUser.getId();
        //用户保存文件路径
        //data.setZylj(userDataPath);
        if (sessionUser!=null) data.setUserId(sessionUser);

        if (dataResourceService.saveOrUpdateDataRes(data)) {
            dataResourceService.saveOrUpdateDataRes(data);
            massage = "上传资料成功";
        } else {
            massage = "上传资料失败";
        }
        model.addAttribute(massage);
        return "forward:/showHome";
    }

    /**
     * 删除资源数据
     *
     * @param model
     * @param id
     */
    @Override
    public String delDataResourceById(Model model, Integer id) {
        String massage=null;
        if (dataResourceService.delDataResById(id)) {
            massage = "删除资料成功";
        } else {
            massage = "删除资料失败";
        }
        model.addAttribute(massage);
        return null;

    }

    /**
     * 修改资源数据
     *
     * @param model
     * @param data
     */
    @Override
    public String updateDataResource(Model model, TDataresource data) {
        String massage = null;
        if (dataResourceService.saveOrUpdateDataRes(data)) {
            massage = "修改资料成功";
        } else {
            massage = "修改资料失败";
        }
        return null;
    }

    /**
     * 查看资源数据
     *
     * @param model
     * @param id
     */
    @RequestMapping("/viewDataResourceByid")
    @Override
    public String viewDataResourceById(Model model, @RequestParam Integer id) {
        TDataresource data = null;
        String massage = null;
        if (id != null) {
            data = dataResourceService.loadDataResById(id);
            if (data != null) {
                data.setDjcs(data.getDjcs()+1);//点击次数加一
                dataResourceService.saveOrUpdateDataRes(data);
                massage = "查找数据成功";
            } else {
                massage = "查找数据失败";
            }
        }
        model.addAttribute(massage);
        model.addAttribute("data",data);
        return "/webs/downloadInner";
    }
    @RequestMapping("/searchDataByCateAndKey")
    @Override
    public String searchDataByCateAndKey(HttpServletRequest request,Model model, @RequestParam(required = false) Integer cateId,@RequestParam(required = false,defaultValue = "1") Integer pageNo, @RequestParam(required = false) String keyWord) {
        List<TDataresource> dataList=null;
        DataCategory dataCategory=null;
        String uri =null;
        Page page = null;
        int count=0;//當前分類縂條數
        String massage=null;//处理结果
        // dataResourceService.browseDataRes(0);
        String tempKeyWord = (String) request.getSession().getAttribute("keyWord");
        List<DataCategory> cateList=dataCategoryService.browseAllDataCategory();//分类
        logger.info("before:cateId"+cateId+"keyWord"+keyWord+"pageNo"+pageNo+"pageSize");
        if (cateId != null && (dataCategory = dataCategoryService.loadDataCategoryById(cateId)) != null) {

            //分类不为空同时可查到此分类的信息
            uri=new String("/searchDataByCateAndKey?cateId=" + cateId);//

            if(keyWord!=null){//如果关键字获取到
                count = dataResourceService.loadDataResByDataCategoryCount(cateId.intValue(), keyWord);
                page= InitPage.getInstence(uri,pageNo,4,count);
                dataList=dataResourceService.loadDataResByDataCategory(dataCategory,keyWord,pageNo,page.getPageSize());
                // count = dataResourceService.loadDataResByDataCategoryCount(cateId.intValue(), keyWord);
                request.getSession().setAttribute("keyWord", keyWord);
            }
            else if (tempKeyWord!=null){//如果之前没有搜索关键字,从session中获取
                count = dataResourceService.loadDataResByDataCategoryCount(cateId.intValue(), tempKeyWord);
                page= InitPage.getInstence(uri,pageNo,4,count);
                // count = dataResourceService.loadDataResByDataCategoryCount(cateId.intValue(), tempKeyWord);
                dataList=dataResourceService.loadDataResByDataCategory(dataCategory,tempKeyWord,pageNo,page.getPageSize());

            }
            page= InitPage.getInstence(uri,pageNo,4,count);
/*            else{//如果第一次访问同时未输入关键字
                dataList = dataResourceService.loadDataResByDataCategory(dataCategory, "", pageNo, page.getPageSize());
                 count = dataResourceService.loadDataResByDataCategoryCount(cateId.intValue(), "");
                }*/


        } else  {//如果不能通过分类查找
            massage = new String("非法分类参数");
        }
        model.addAttribute("url", "/searchDataByCateAndKey");
        model.addAttribute("dataList", dataList);
        model.addAttribute("page", page);
        //   model.addAttribute("uri", uri);
        model.addAttribute("dataCategoryList",cateList );
        model.addAttribute("cateId", cateId);
        logger.info("dataList size"+dataList.size()+"page "+page.toString());
        logger.info("after:cateId"+cateId+"keyWord"+keyWord+"pageNo"+pageNo+"pageSize"+page.getPageSize());
        return "/webs/downloadSearchResult";

    }

    /**
     * 浏览资源数据
     */
    @RequestMapping("/browseDataResourceByUser")
    @Override
    public String browseDataResourceByUser(HttpServletRequest request,Model model,@RequestParam(defaultValue ="1") Integer pageNo) {
        String massage = null;
        Page page = null;
        int count=0;//总计
        List<TDataresource> dataList=null;
        String uri = "/browseDataResourceByUser?";
        TUser sessionUser= (TUser) request.getSession().getAttribute("mumber");
        if (sessionUser != null) {
            count = dataResourceService.browseDataResourceByUserCount(sessionUser.getId());
            page = InitPage.getInstence(uri, pageNo.intValue(), 5, count);
            logger.info("page"+page.toString());
            dataList = dataResourceService.browseDataResourceByUser(sessionUser.getId(), pageNo, page.getPageSize());
            logger.info("dataList"+dataList.size());
        }else{
            page = InitPage.getInstence(uri, pageNo.intValue(), 5, count);
        }

        if (dataList != null) {
            massage = "浏览数据成功";
        } else {
            massage = "浏览数据失败";
        }
        model.addAttribute(massage);
        model.addAttribute("page", page);
        model.addAttribute("dataList",dataList);
        return "/webs/download_look_res";
    }
    @RequestMapping("/showDownload")
    @Override
    public String showDownLoad(HttpServletRequest request,Model model) {
        //判断用户是否登录
        TUser sessionUser = (TUser) request.getSession().getAttribute("mumber");
        if (sessionUser == null) {
            model.addAttribute("massage", "请先登录");
            return "forward:/showHome";
        }
        List<DataCategory> cateList=dataCategoryService.browseAllDataCategory();//分类
        List tempList = dataResourceService.browseDataRes(0);//所有数据按照博文分类
        //List<TDataresource> dataListCard1=null, dataListCard2=null;
        List<List> dataCardLists = new ArrayList<List>();//所有卡片的信息
        List<DataCategory> dataCordCateList = new ArrayList<>();
        List dataList = new ArrayList();
        int count = tempList.size();
        int index = 0;
        if (count >4) {//小编推荐
            for (int i=0;i<3;i++) {
                dataList.add(tempList.get(i));
            }
        }

        if (cateList.size() <= 4) {//如果只有4个小卡片
            for (int i=0;i<cateList.size();i++) {
                DataCategory tempcate=cateList.get(i);
                dataCordCateList.add(tempcate);
                List tempListbyCate = dataResourceService.loadDataResByDataCategory(tempcate);
                dataCardLists.add(tempListbyCate);
            }

        } else {//随机分类数据展示小卡片如果有4个以上的小卡片
/*            dataListCard1 = dataResourceService.loadDataResByDataCategory(cateList.get(0));
            dataListCard2*/
            int indexStart = (int) (Math.random() * (cateList.size() - 4));
            if (indexStart>0)indexStart=indexStart-1;
            logger.info("indexStart"+indexStart);
            for (int j=1;j<5;j++) {
                DataCategory tempcate=cateList.get(indexStart++);
                List tempListbyCate = dataResourceService.loadDataResByDataCategory(tempcate);
                dataCardLists.add(tempListbyCate);
                dataCordCateList.add(tempcate);
            }
        }
        logger.info("dataList"+dataList.size());
        model.addAttribute("dataCategoryList",cateList );
        model.addAttribute("recDataList", dataList);
        model.addAttribute("dataCordCateList", dataCordCateList);
        model.addAttribute("dataCardLists", dataCardLists);
        logger.info("dataCordCateListsize"+dataCordCateList.size());
        return "/webs/download";
    }

    public DataResourceService getDataResourceService() {
        return dataResourceService;
    }

    public void setDataResourceService(DataResourceService dataResourceService) {
        this.dataResourceService = dataResourceService;
    }
    public DataCategoryService getDataCategoryService() {
        return dataCategoryService;
    }

    public void setDataCategoryService(DataCategoryService dataCategoryService) {
        this.dataCategoryService = dataCategoryService;
    }
}
