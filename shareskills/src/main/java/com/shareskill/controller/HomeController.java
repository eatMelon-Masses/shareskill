package com.shareskill.controller;

import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface HomeController {
    /**映射首页请求*/

    public String showHome(HttpServletRequest request,Model model,Integer pageNo);
    /**手机端返回轮播图片地址*/
    public Map showPicForPhone(HttpServletRequest request, Model model);
}
