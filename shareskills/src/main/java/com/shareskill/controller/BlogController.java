package com.shareskill.controller;

import com.shareskill.model.BlogoSphere;
import com.shareskill.model.TBlog;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import javax.jws.WebParam;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public interface BlogController {

    /**
     * 新增博文
     * jsp && json
     */
    public String addBlog(HttpServletRequest request,TBlog blog);

    /**删除指定博文
     * json
     * */
    public String delBlog(Model model,@Validated(TBlog.BlogRuleD.class) TBlog blog, BindingResult bindingResult);

    /**修改指定博文
     * jsp && json
     * */
    public String updateBlog();

    /**查看指定博文
     * json
     * */
    public String viewBlog(HttpServletRequest request,Model model,Integer id);

    /**浏览博文
     * json
     * */
    public String browseBlog(Model model);

    /**
     * 浏览指定分类博文
     */
    public String browseBlogsByCate(HttpServletRequest request,Model model, @RequestParam String id,@RequestParam int order,Integer pageNo);
    /**
     *
     * 用户博客相关界面*;
     */
    public String showUserBlogsModule(Model model);

    /**
     * 用户搜索博文
     */
    public String searchAllBlogs(HttpServletRequest request,Model model, @RequestParam(value = "searchContent")String searchContent,@RequestParam("0") Integer pageNo);
}
