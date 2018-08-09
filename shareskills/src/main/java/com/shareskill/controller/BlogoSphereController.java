package com.shareskill.controller;

import com.shareskill.model.BlogoSphere;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

public interface BlogoSphereController {
    public String showUserBlogSphereByType(HttpServletRequest request,Model model,Integer type,Integer pageNo);
    /**
     * @param model
     * @return
     */
    public String writeUserWordForm(Model model);

    /**
     * @param model
     * @param blogoSphere
     * @return
     */
    public String addUserWord(HttpServletRequest request,Model model, BlogoSphere blogoSphere);

    /**
     * @param model
     * @return
     */
    public String browseAllBlogoSphere(Model model);

    /**
     * @param model
     * @param id
     * @return
     */
    public String delUserWordByid(Model model, Integer id);

    public String showMyBlogSphere(HttpServletRequest request,Model model,@RequestParam(defaultValue = "1") Integer pageNo);
}
