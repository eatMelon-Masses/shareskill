package com.shareskill.controllerimp;

import com.shareskill.controller.BlogoSphereController;
import com.shareskill.model.BlogoSphere;
import com.shareskill.model.TUser;
import com.shareskill.service.BlogoSphereService;
import com.shareskill.utils.CurrentLineInfo;
import com.shareskill.utils.InitPage;
import com.shareskill.utils.Page;
import net.sf.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

@Controller
public class BlogoSphereControllerImp implements BlogoSphereController {

    @Autowired
    private BlogoSphereService blogoSphereService;
    private static final Log logger = LogFactory.getLog(BlogoSphereControllerImp.class);
    public BlogoSphereService getBlogoSphereService() {
        return blogoSphereService;
    }

    public void setBlogoSphereService(BlogoSphereService blogoSphereService) {
        this.blogoSphereService = blogoSphereService;
    }

    /**
     * //通过类型访问博客圈内容
     * @param model
     * @param type
     * @return
     */
    @RequestMapping("/showUserBlogSphereByType")
    @Override
    public String showUserBlogSphereByType(HttpServletRequest request,Model model, @RequestParam(defaultValue = "1") Integer type,@RequestParam(defaultValue = "1") Integer pageNo) {
        List<BlogoSphere> bSList =null;
        String uri=new String("/showUserBlogSphereByType?type="+type);
        Page page=null;
        logger.info("method"+CurrentLineInfo.getMethodName()+"line: "+CurrentLineInfo.getMethodName());
        logger.info("type:"+type+" page "+pageNo);
        try {
            request.setCharacterEncoding("iso8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        bSList = blogoSphereService.browseBlogSphereByType(type, pageNo, 4);
        int count = blogoSphereService.browseBlogSphereByTypeConut(type);
        page = InitPage.getInstence(uri, pageNo, 4, count);
        model.addAttribute("bSList", bSList);
        model.addAttribute("page", page);
        model.addAttribute("blogsSphere", new BlogoSphere());
/*        model.addAttribute("curPageNo", pageNo);
        model.addAttribute("uri", uri);
        model.addAttribute("total", count);*/
        logger.info("bSlist's size is"+bSList.size()+" ");
        return "/webs/blogoSphere/noteshare";
    }

    /**填写用户表单
     * @param model
     * @return
     */
    @RequestMapping("/showUserBlogoSphere")
    @Override
    public String writeUserWordForm(Model model) {
        logger.info("method"+CurrentLineInfo.getMethodName()+"line: "+CurrentLineInfo.getMethodName());
        model.addAttribute("blogsSphere", new BlogoSphere());
        return "redirect:/showUserBlogoSphere";

    }

    /**
     * @param model
     * @param blogoSphere
     * @return
     */
    @RequestMapping("/addUserWord")
    @Override
    public String addUserWord(HttpServletRequest request,Model model, @ModelAttribute BlogoSphere blogoSphere) {
        String massage =null;
        blogoSphere.setWordDate(new Date());
        TUser sessionUser=(TUser) request.getSession().getAttribute("mumber");
        //logger.info("userId"+sessionUser.getZh());
        // logger.info("userId"+sessionUser.toString());
        //判断用户是否登录
        if (sessionUser == null) {
            model.addAttribute("massage", "请先登录");
            return "forward:/showHome";
        }
        blogoSphere.setUser(sessionUser);
        if (blogoSphereService.addUserWord(blogoSphere)) {

            massage = "success";
        } else {
            massage = "error";
        }
        logger.info("Method"+CurrentLineInfo.getMethodName()+"line:"+CurrentLineInfo.getLineNumber()+"massage"+massage);
        try {
            massage=new String(massage.getBytes(),"iso8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        // model.addAttribute(massage);
        return "redirect:/showUserBlogSphereByType";
    }

    /**
     * @param model
     * @return
     */
    @Override
    public String browseAllBlogoSphere(Model model) {
        return null;
    }

    /**
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("/delUserWordById")
    @Override
    public String delUserWordByid(Model model, @RequestParam() Integer id) {
        String massage = "操作成功";
        if (!blogoSphereService.delUserWordById(id)) {
            massage = "操作失败";
        }
        model.addAttribute("massage",massage);
        return "redirect:/showMyBlogSphere";
    }
    @RequestMapping("/showMyBlogSphere")
    @Override
    public String showMyBlogSphere(HttpServletRequest request,Model model,@RequestParam(defaultValue = "1") Integer pageNo) {
        String massage =null;
        String error = "";
        TUser sessionUser = (TUser) request.getSession().getAttribute("mumber");
        int count=0;
        Page  page=null;
        List<BlogoSphere> dataList=null;
        String uri = new String("/showMyBlogSphere");
        if (sessionUser != null) {
            count = blogoSphereService.browseBlogSphereByUserCount(sessionUser.getId());
            page = InitPage.getInstence(uri, pageNo, 5,count);
            dataList = blogoSphereService.browseBlogSphereByUser(sessionUser.getId(), pageNo, page.getPageSize());
            logger.info("Page "+page);

        } else {
            massage = "请您先登录";
        }
        logger.info("page"+page+sessionUser);
        model.addAttribute("blogsSphere", new BlogoSphere());
        model.addAttribute("massage", massage);
        model.addAttribute("page", page);
        model.addAttribute("bSList", dataList);
        return "/webs/blogoSphere/noteshare_my";
    }
}
