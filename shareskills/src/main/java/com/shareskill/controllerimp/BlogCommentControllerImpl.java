package com.shareskill.controllerimp;

import com.shareskill.model.TBlog;
import com.shareskill.model.TBlogcomment;
import com.shareskill.model.TUser;
import com.shareskill.service.BlogCommentService;
import com.shareskill.service.BlogsService;
import com.shareskill.service.UserService;
import net.sf.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Controller
public class BlogCommentControllerImpl implements BlogCommentController {
    @Autowired
    private BlogsService blogService;
    @Autowired
    private UserService userService;
    @Autowired
    private BlogCommentService blogCommentService;
    private static final Log logger = LogFactory.getLog(BlogCommentControllerImpl.class);

    public BlogCommentService getBlogCommentService() {
        return blogCommentService;
    }

    public void setBlogCommentService(BlogCommentService blogCommentService) {
        this.blogCommentService = blogCommentService;
    }

    public BlogsService getBlogService() {
        return blogService;
    }

    public void setBlogService(BlogsService blogService) {
        this.blogService = blogService;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * 通过博客id查找博客评论
     *
     * @param model
     * @param blogId
     * @return
     */
    @ResponseBody
    @RequestMapping("/browseBlogCommentByBlogId")
    @Override
    public List<Object> browseBlogCommnetByBlogId(Model model, @RequestParam Integer blogId) {
        List<Object> list=new ArrayList<>();
        String massage="处理错误";
        if (blogId != null) {
            list.addAll(blogCommentService.browseBlogCommentByBlogId(blogId));
            massage ="处理成功";
        }
        return list;
    }

    /**
     * 添加博客评论
     *
     *
     * @param bc
     * @param blogId
     * @return
     */
    @RequestMapping("/addBlogComment")
    @Override
    public String addBlogComment(HttpServletRequest request, Model model,@ModelAttribute TBlogcomment bc, @RequestParam() Integer blogId) {

        String massage = "评论成功";
        TBlog blog = blogService.loadBlogById(blogId);
        TUser user=(TUser) request.getSession().getAttribute("mumber");
        bc.setUser(user);
        bc.setBlog(blogId);
        bc.setPlsj(new Date());
        logger.info("blog"+blog.toString());
        logger.info("bc:"+bc.toString());
        //blogCommentService.saveOrUpdate(bc);
        if (user==null||blog == null || !blogCommentService.saveOrUpdate(bc)) {
            massage = "评论失败,请先登录博客账号";
        }
        logger.info("bc"+bc.toString());
        model.addAttribute("massage", massage);
        return "forward:/viewBlog?id="+blogId;
    }
    @RequestMapping("/delBlogComment")
    @Override
    public String delBlogComment(Model model, @RequestParam Integer bcId,@RequestParam Integer blogId,@RequestParam(defaultValue = "0") Integer type) {

        String massage = new String("评论删除成功");
        List<Object> list = new ArrayList();

        if (!blogCommentService.delComment(bcId)) {
            massage = "评论删除失败";
        }
        model.addAttribute("massage", massage);
        logger.info("forword:/viewBlog?id="+blogId);
        if (type.intValue()==0)
          return "forward:/viewBlog?id="+blogId;
        else
            return "forward:/showMypersionInf";
    }
}
