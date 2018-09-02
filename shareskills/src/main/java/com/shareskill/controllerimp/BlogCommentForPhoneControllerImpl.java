package com.shareskill.controllerimp;

import com.shareskill.controller.BlogCommentForPhoneController;
import com.shareskill.model.TBlog;
import com.shareskill.model.TBlogcomment;
import com.shareskill.model.TUser;
import com.shareskill.service.BlogCommentService;
import com.shareskill.service.BlogsService;
import com.shareskill.service.UserService;
import com.shareskill.vo.BlogCommentVo;
import net.sf.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Controller
public class BlogCommentForPhoneControllerImpl implements BlogCommentForPhoneController {
    @Autowired
    private BlogsService blogService;
    @Autowired
    private UserService userService;
    @Autowired
    private BlogCommentService blogCommentService;
    private static final Log logger = LogFactory.getLog(BlogCommentForPhoneController.class);
    /**
     * 通过博客id查找博客评论
     *
     * @param model
     * @param blogId
     * @return
     */
    @ResponseBody
    @RequestMapping("/browseBlogCommentByBlogIdForPhone")
    @Override
    public List<Object> browseBlogCommnetByBlogId(Model model, @RequestParam Integer blogId) {
        List<Object> list=new ArrayList<>();
        JSONObject massage =new JSONObject();
        massage.put("operation", "success");
        if (blogId != null) {
            for (TBlogcomment Tblogcomment:blogCommentService.browseBlogCommentByBlogId(blogId)){
                list.add(new BlogCommentVo(Tblogcomment));
            }

        }else{
            massage.put("operation", "failure");
        }
        list.add(0,massage);
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
    @ResponseBody
    @RequestMapping("/addBlogCommentForPhone")
    @Override
    public List<Object> addBlogComment(HttpServletRequest request, Model model, @ModelAttribute TBlogcomment bc, @RequestParam Integer blogId) {
        JSONObject massage =new JSONObject();
        massage.put("operation", "success");
        List<Object> list=new ArrayList();
        TBlog blog = blogService.loadBlogById(blogId);
        TUser user=(TUser) request.getSession().getAttribute("mumber");
        bc.setUser(user);
        bc.setBlog(blogId);
        bc.setPlsj(new Date());
        logger.info("blog"+blog.toString());
        logger.info("bc:"+bc.toString());
        //blogCommentService.saveOrUpdate(bc);
        if (blog == null || !blogCommentService.saveOrUpdate(bc)) {
            massage.put("operation", "failure");
        }
        list.add(massage);
        logger.info("bc"+bc.toString());
        return list;
    }

}
