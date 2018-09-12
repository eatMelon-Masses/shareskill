package com.shareskill.controllerimp;

import com.shareskill.controller.BlogControllerForPhone;
import com.shareskill.model.TBlog;
import com.shareskill.model.TBlogcategorytag;
import com.shareskill.model.TBlogcomment;
import com.shareskill.model.TUser;
import com.shareskill.service.BlogCommentService;
import com.shareskill.service.BlogsService;
import com.shareskill.service.CategoryService;
import com.shareskill.service.UserService;

import net.sf.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import java.util.*;

@Controller
public class BlogConntrollerForPhoneImp implements BlogControllerForPhone{
    @Autowired
    private BlogsService blogsService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private UserService userService;
    @Autowired
    private BlogCommentService blogCommentService;

    public BlogCommentService getBlogCommentService() {
        return blogCommentService;
    }

    public void setBlogCommentService(BlogCommentService blogCommentService) {
        this.blogCommentService = blogCommentService;
    }

    static final Log logger = LogFactory.getLog(BlogConntrollerForPhoneImp.class);
    final static String RESULT = "result";
    final static String MESSAGE = "message";
    final static String TRUE = "true";
    final static String FALSE = "false";
    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * 手机端浏览博客接口
     */
    @RequestMapping("/browseBlogsByPhone/json")
    @ResponseBody
    @Override
    public List<TBlog> brwoseBlogsByPhone() {
        return blogsService.browseAllBlogsOrderByTime();
    }

    /**
     * 手机端浏览指定分类博客
     *
     * @param model
     */
    @ResponseBody
    @RequestMapping("/browseBlogsByCate/json")
    @Override
    public List<Object> browseBlogsByCateUsePhone(Model model, @RequestParam String id, @RequestParam(required = false) int order) {
       // List<TBlog> blogsList=new ArrayList<>();
        JSONObject massage = new JSONObject();
        List<Object> jsonArray = new ArrayList<>();
        //model.addAttribute("catesList", categoryService.browseCategory());
        if (id != null) {//如果参数正确
            TBlogcategorytag tempCate = categoryService.loadCategory(Integer.valueOf(id));
            //如果有此分类标签
            if (tempCate != null) {
                List<TBlog> tempList = blogsService.loadBlogsByCategory(tempCate, order);
                /*blogsList.addAll(tempList);*/

                massage.put("operation", "success");
                jsonArray.add(massage);
                jsonArray.add(tempList);
            } else {
                massage.put("operation", "failure");
            }
        } else {
            massage.put("operation", "failure");
        }

        //jsonArray.add(massage);

        //model.addAttribute("cateId", id);
       // model.addAttribute("browseBlogsByCate", "success");
        return jsonArray;
    }
    @ResponseBody
    @RequestMapping("/browseMyBlogs_json")
    @Override
    public List<Object> browseMyBlogs_json(Model model, Integer userId) {
        JSONObject massage =new JSONObject();
        massage.put("operation", "success");
        List<Object> list=new ArrayList();
        TUser user = userService.loadMember(userId);
        if (user != null) {
            List<TBlog> temlist = blogsService.loadBlogsByMember(user);

            if (temlist.size() == 0) {//没有记录
                massage.put("operation", "failure");
            } else {
                list.addAll(temlist);
            }
        } else {//没有此用户
            massage.put("operation", "failure");
        }

        list.add(0,massage);
        return list;
    }
    @ResponseBody
    @RequestMapping("/serachBlogs_json")
    @Override
    public List<Object> serarchBlogs_josn(Model model, @RequestParam String keyWord) {
        List<Object> list=new ArrayList();
        logger.info("keyword"+keyWord);
        List<TBlog> tempList=blogsService.searchBlogs_json(keyWord);
        JSONObject massage = new JSONObject();
        massage.put("operation", "success");
        list.add(massage);

        if (tempList.size() == 0) {
            massage.put("operation", "failure");
        } else {
            list.add(tempList);
        }
        return list;
    }
    @ResponseBody
    @RequestMapping("/json/delBlogs")
    @Override
    public List<Object> delBlogs(@Validated(TBlog.BlogRuleD.class) TBlog blog, BindingResult bindingResult) {
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
        TBlog tempBlog = blogsService.loadBlogById(blog.getId());
        if (tempBlog != null) {
            List<TBlogcomment> bcList = blogCommentService.browseBlogCommentByBlogId(blog.getId());
            for (TBlogcomment bc : bcList) {
                blogCommentService.delComment(bc.getId());
            }
            blogsService.delBlogById(tempBlog.getId());
            mapList.put(RESULT, TRUE);
            message.append("删除成功");
        } else {
            message.append("没有此博文");
            return jsonList;
        }

        return jsonList;
    }
    @ResponseBody
    @RequestMapping("/json/addBlog")
    @Override
    public List<Object> addBlog(@Validated(TBlog.BlogRuleB.class) TBlog blog, BindingResult bindingResult) {
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
        TBlogcategorytag blogcate = categoryService.loadCategory(blog.getBlogCategoryId());
        TUser tempUser = userService.loadMember(blog.getUserId());
        if (tempUser != null && blogcate != null) {
            blog.setBwdjcs(new Integer(0));
            blog.setBwcjsj(new Date());
            blog.setEditor(tempUser.getYhnc());
            //
            if (blogsService.saveOrUpdateBlog(blog)) {
                mapList.put(RESULT, TRUE);
                message.append("博文发表成功");
            } else {
                message.append("未知错误,请稍后再发");
                return jsonList;
            }
        } else {
            message.append("非法提交参数");
            return jsonList;
        }
        return jsonList;
    }


    public BlogsService getBlogsService() {
        return blogsService;
    }

    public void setBlogsService(BlogsService blogsService) {
        this.blogsService = blogsService;
    }

    public CategoryService getCategoryService() {
        return categoryService;
    }

    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
}
