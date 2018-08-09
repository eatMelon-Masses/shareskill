package com.shareskill.controller;

import com.shareskill.model.TBlogcategorytag;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CategoryController {
    /**新增文章分类*/
    public void addBlogCategory(HttpServletResponse response, @ModelAttribute TBlogcategorytag category);

    /**删除文章分类*/
    public void delBlogCategory(HttpServletResponse response,@RequestParam Integer id);

    /**修改文章分类*/
    public  void updateBlogCategory(HttpServletResponse response,@ModelAttribute TBlogcategorytag category);

    /**查看指定文章分类*/
    public void viewBlogCategory(HttpServletResponse response, @RequestParam Integer id);

    /**浏览文章分类*/
    public void browseBlogCategory(HttpServletResponse response);
}
