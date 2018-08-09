package com.shareskill.controller;

import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;

public interface HomeController {
    /**映射首页请求*/

    public String showHome(HttpServletRequest request,Model model,Integer pageNo);
    /**手机端返回轮播图片地址*/
    public Model showPicForPhone(HttpServletRequest request,Model model);
}
