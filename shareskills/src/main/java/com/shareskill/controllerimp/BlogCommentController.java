package com.shareskill.controllerimp;

import com.shareskill.model.TBlog;
import com.shareskill.model.TBlogcomment;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface BlogCommentController {
    /**通过博客id查找博客评论
     * @param model
     * @return
     */
    public List<Object> browseBlogCommnetByBlogId(Model model, @RequestParam Integer blogId);

    /**添加博客评论
     * @param model
     * @return
     */
    public String addBlogComment(HttpServletRequest request,Model model,TBlogcomment bc, @RequestParam Integer blogId);

    public String delBlogComment(Model model, @RequestParam Integer bcId,@RequestParam Integer blogId,Integer type);
}
