package com.shareskill.controllerimp;

import com.shareskill.controller.HomeController;
import com.shareskill.model.TUser;
import com.shareskill.service.BlogsService;
import com.shareskill.service.CategoryService;
import com.shareskill.utils.GetHomePicsName;
import com.shareskill.utils.InitPage;
import com.shareskill.utils.Page;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeControllerImp implements HomeController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private BlogsService blogsService;
    private static final Log logger = LogFactory.getLog(HomeControllerImp.class);
    final static String RESULT = "result";
    final static String MESSAGE = "message";
    final static String TRUE = "true";
    final static String FALSE = "false";



    private final String HOMEVIEW = "webs/index";
    /**
     *
     *
     * @param model
     */
    @RequestMapping(value = {"","/","/showHome"})
    @Override
    public String showHome(HttpServletRequest request,Model model,@RequestParam(defaultValue = "1") Integer pageNo) {
        //String path = request.getContextPath();
        String servletPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/";
        TUser sessionUser=(TUser) request.getSession().getAttribute("mumber");
        String path = request.getPathTranslated();

        String basePath=request.getServletContext().getRealPath("/homeslideshow").replaceAll("\\\\", "/");
        ArrayList<String> fileList = new ArrayList<String>();

        GetHomePicsName.getAllFileName(basePath,fileList);
        String uri=new String(servletPath+"showHome?pageNo=");//
        int count=blogsService.browseAllBlogsCount();
        Page page = InitPage.getInstence(uri, pageNo, 4, count);

/*        logger.info("basePath"+basePath);
        logger.info("filelist:"+fileList.size());*/
        //logger.info("filelist:"+fileList.size()+fileList.get(0).toString());
        model.addAttribute("lbList", fileList);
        model.addAttribute("blogList", blogsService.browseAllBlogs(pageNo.intValue(),page.getPageSize()));
        model.addAttribute("var", "helloworld");
        model.addAttribute("cateList", categoryService.browseCategory());
        model.addAttribute("mumber", sessionUser);
        model.addAttribute("page", page);
        logger.info("page"+page.toString());
        //  String massage = "this is first massage";
        //model.addAttribute("massage", massage);
        //logger.info(massage);

        return HOMEVIEW;
    }

    /**
     *
     *
     * @param request
     * @param model
     */
    @RequestMapping("/showPicForPhone")
    @ResponseBody
    @Override
    public Map showPicForPhone(HttpServletRequest request, Model model) {
        //List<Object> jsonList = new ArrayList<>();
        //StringBuffer message = new StringBuffer();
        Map<String, Object> mapList = new HashMap<>();
        //mapList.put(RESULT,true);
        //mapList.put(MESSAGE, message);
        //jsonList.add(mapList);
        String path = request.getPathTranslated();
        //
        String basePath=request.getServletContext().getRealPath("/homeslideshow").replaceAll("\\\\", "/");
        //
        String netPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/homeslideshow/";
        ArrayList<String> tempfileList = new ArrayList<String>();
        ArrayList<String> picsList = new ArrayList<>();
        //
        GetHomePicsName.getAllFileName(basePath,tempfileList);
        for (String temStr : tempfileList) {
            temStr=netPath+temStr;
            picsList.add(temStr);
            logger.info(temStr);
        }
        //message.append("获取成功");
        //model.addAttribute(picsList);
        //JSONArray jsonArray = JSONArray.fromObject(picsList);
        mapList.put("bl",picsList);

        return mapList;
    }

    public CategoryService getCategoryService() {
        return categoryService;
    }

    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public BlogsService getBlogsService() {
        return blogsService;
    }

    public void setBlogsService(BlogsService blogsService) {
        this.blogsService = blogsService;
    }
}
