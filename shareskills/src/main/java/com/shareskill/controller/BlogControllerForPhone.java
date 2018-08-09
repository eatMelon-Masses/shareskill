package com.shareskill.controller;

import com.shareskill.model.TBlog;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface BlogControllerForPhone {
    /**手机端浏览博客接口*/
    public List<TBlog> brwoseBlogsByPhone();
    /** 手机端浏览指定分类博客*/
    public List<Object> browseBlogsByCateUsePhone(Model model, @RequestParam String id, @RequestParam(required = false) int order);

    /**浏览我的博客
     * @param model
     * @param userId
     * @return
     */
    public List<Object> browseMyBlogs_json(Model model,@RequestParam Integer userId);

    public List<Object> serarchBlogs_josn(Model model, @RequestParam String keyWord);
    public List<Object> delBlogs (TBlog blog, BindingResult bindingResult);
    public List<Object> addBlog(TBlog blog,BindingResult bindingResult);
}
