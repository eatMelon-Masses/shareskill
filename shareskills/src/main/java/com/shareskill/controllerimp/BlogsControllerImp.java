package com.shareskill.controllerimp;

import com.shareskill.controller.BlogController;
import com.shareskill.model.*;
import com.shareskill.service.BlogCommentService;
import com.shareskill.service.BlogsService;
import com.shareskill.service.CategoryService;
import com.shareskill.service.UserService;
import com.shareskill.utils.AppContextFactory;
import com.shareskill.utils.InitPage;
import com.shareskill.utils.Page;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
public class BlogsControllerImp implements BlogController {
    @Autowired
    private BlogsService blogService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private BlogCommentService blogCommentService;

    public BlogCommentService getBlogCommentService() {
        return blogCommentService;
    }

    public void setBlogCommentService(BlogCommentService blogCommentService) {
        this.blogCommentService = blogCommentService;
    }

    @Resource
    private UserService userService;
    final static Log logger = LogFactory.getLog(BlogsControllerImp.class);
    /**
     * 新增博文
     * jsp && json
     *
     * @param request
     * @param blog
     */
    @RequestMapping("/addBlogs")
    @Override
    public String addBlog(HttpServletRequest request, TBlog blog) {
        logger.info("addBlog.....");
        //Object tempCateObject= AppContextFactory.getBean("categoryService") ;
        //Object tempCateObject = AppContextFactory.getBean(new String("categoryService"));
        //CategoryService categoryService=(CategoryService)tempCateObject;
        //TBlogcategorytag tempcate=categoryService.loadCategory(blog.getBlogCategoryId());
/*        if (tempcate!=null){
            blog.setBlogCategoryId(tempcate.getId());
            blogService.saveOrUpdateBlog(blog);
            logger.info("addBlog_success");
            return "/webs/index";
}*/
        TUser sessionUser=(TUser) request.getSession().getAttribute("mumber");
        blog.setBwcjsj(new Date());//博客创建时间
        blog.setBwdjcs(new Integer(0));//初始化博客点击次数
        //blog.setEditor(userService.loadMember(blog.getUserId()));
        if (sessionUser!=null){
            blog.setEditor(sessionUser.getYhnc());
            blog.setUserId(sessionUser.getId());
        }
        blogService.saveOrUpdateBlog(blog);
        return "redirect:/showHome";
        }


    /**
     * 删除指定博文
     * json
     */
    @RequestMapping("/delBlog")
    @Override
    public String delBlog(Model model,@Validated(TBlog.BlogRuleD.class) TBlog blog, BindingResult bindingResult) {
        StringBuffer message = new StringBuffer();
        model.addAttribute("massage", message);
        if (bindingResult.hasErrors()) {//遍历错误信息
            List<ObjectError> errorList = bindingResult.getAllErrors();
            for (ObjectError error : errorList) {
                logger.info(error.getDefaultMessage());
                message.append(error.getDefaultMessage());
            }
            return "forward:/showMypersionInf";
        }
        TBlog tempBlog = blogService.loadBlogById(blog.getId());
        if (tempBlog != null) {
            List<TBlogcomment> bcList = blogCommentService.browseBlogCommentByBlogId(blog.getId());
            for (TBlogcomment bc : bcList) {
                blogCommentService.delComment(bc.getId());
            }
            blogService.delBlogById(tempBlog.getId());

            message.append("删除成功");
        } else {
            message.append("没有此博文");
            return "forward:/showMypersionInf";
        }
        return "forward:/showMypersionInf";
    }

    /**
     * 修改指定博文
     * jsp && json
     */
    @Override
    public String updateBlog() {
        return null;
    }



    /**
     * 查看指定博文
     * json
     */
    @RequestMapping("/viewBlog")
    @Override
    public String viewBlog (HttpServletRequest request,Model model,@RequestParam Integer id) {
        logger.info("viewBlog before"+"blogid"+id);
        String massage;
        TUser currentuser =(TUser) request.getSession().getAttribute("mumber");
       // TUser sessionUser=(TUser) request.getSession().getAttribute("mumber");
        if (id != null) {
            TBlog blog = blogService.loadBlogById(id);
            List <TBlogcomment> bcList=blogCommentService.browseBlogCommentByBlogId(id);
            blog.setBwdjcs(blog.getBwdjcs()+1);//增加点击次数
            blogService.saveOrUpdateBlog(blog);//保存修改
            model.addAttribute("bcList", bcList);
            model.addAttribute("user", currentuser);
            model.addAttribute("blog", blog);
            model.addAttribute("bc",new TBlogcomment());//绑定页面表格
            logger.info("viewBlog after"+"bc size"+bcList.size()+"user"+currentuser);

        }

        return "/webs/inner";
    }

    /**
     * 浏览博文
     * json
     */
    @Override
    public String browseBlog(Model model) {
        model.addAttribute("blogsList",blogService.browseAllBlogs());
        return null;

    }


    /**80
     * 浏览指定分类博文
     *
     * @param model
     * @param id
     */
    @RequestMapping("/browseBlogsByCate")
    @Override
    public String browseBlogsByCate(HttpServletRequest request,Model model, @RequestParam(defaultValue = "1") String id,@RequestParam(required = false,defaultValue = "0") int order,@RequestParam(defaultValue = "1") Integer pageNo) {
       // logger.info("action"+"browseBlogsByCate"+"id:"+id);
        String uri = new String("/browseBlogsByCate?order="+order+"&id="+id);
        List<TBlog> blogsList=new ArrayList<>();
        int count=0;
        Page page =null;
        //判断用户是否登录
        TUser sessionUser = (TUser) request.getSession().getAttribute("mumber");
        if (sessionUser == null) {
            model.addAttribute("massage", "请先登录");
            return "forward:/showHome";
        }
        if (id != null) {//如果参数正确
            TBlogcategorytag tempCate = categoryService.loadCategory(Integer.valueOf(id));
            //如果有此分类标签
            if (tempCate != null) {
                count = blogService.loadBlogByCategoryCount(tempCate, order);
                if (count != 0) {
                    page = InitPage.getInstence(uri, pageNo, 4, count);
                } else {
                    page=InitPage.getInstence(uri,0,4,count);
                }


                List<TBlog> tempList = blogService.loadBlogsByCategory(tempCate,order,pageNo,page.getPageSize());
                blogsList.addAll(tempList);
            }
        }
        model.addAttribute("catesList", categoryService.browseCategory());
        model.addAttribute("blogsList",blogsList);
        model.addAttribute("cateId", id);
        model.addAttribute("page", page);
        return "webs/boke";

    }

    /**
     * 用户博客相关界面*;
     *
     * @param model
     */
    @RequestMapping("/showUserBlogsModule")
    @Override
    public String showUserBlogsModule(Model model) {
        logger.info("action:"+"showUserBlogsModule");
        model.addAttribute("blogsList", blogService.browseAllBlogs());
        model.addAttribute("catesList", categoryService.browseCategory());
        return "webs/boke";
    }

    /**
     * 用户搜索博文
     *
     * @param model
     */
    @RequestMapping(value = "/searchAllBlogs",method = RequestMethod.POST)
    @Override
    public String searchAllBlogs(HttpServletRequest request,Model model, @RequestParam()String searchContent,@RequestParam(required = false,defaultValue = "1") Integer pageNo) {
        logger.info("action"+"searchAllBlogs");
        String uri = new String("/searchAllBlogs?pageNo=");
        int count = blogService.searchBlogsCount(searchContent);
        Page page = InitPage.getInstence(uri, pageNo, 4, count);
        String searchContentbak = (String) request.getSession().getAttribute("rearchContent");
        if (searchContent != null) {
            request.getSession().setAttribute("rearchContent", searchContent);
            model.addAttribute("blogsList",blogService.searchBlogs(searchContent,pageNo,page.getPageSize()));
        } else if (searchContentbak!=null){
            model.addAttribute("blogsList",blogService.searchBlogs(searchContentbak,pageNo,page.getPageSize()));
        }

        model.addAttribute("catesList", categoryService.browseCategory());
        model.addAttribute("page", page);

        return "webs/boke";
    }

    public BlogsService getBlogService() {
        return blogService;
    }

    public void setBlogService(BlogsService blogService) {
        this.blogService = blogService;
    }

    public CategoryService getCategoryService() {
        return categoryService;
    }

    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
