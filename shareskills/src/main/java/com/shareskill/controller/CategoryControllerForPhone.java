package com.shareskill.controller;

import com.shareskill.model.TBlogcategorytag;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface CategoryControllerForPhone {
    public List<Object> addBlogCategory(@ModelAttribute TBlogcategorytag category, BindingResult bindingResult);

    /**删除文章分类*/
    public List<Object> delBlogCategory(Integer id);

    /**修改文章分类*/
    public List<Object> updateBlogCategory( TBlogcategorytag category,BindingResult bindingResult);

    /**查看指定文章分类*/
    public List<Object> viewBlogCategory( TBlogcategorytag category,BindingResult bindingResult);

    /**浏览文章分类*/
    public List<TBlogcategorytag> browseBlogCategory(Model model);
}
