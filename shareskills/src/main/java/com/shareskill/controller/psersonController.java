package com.shareskill.controller;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface psersonController {
    /**个人信息中心
     * @param request
     * @param model
     * @return
     */
    public String showMyPsersonInf(HttpServletRequest request,Model model);
    public String uploaadPerssonImg(HttpServletRequest request, HttpServletResponse response,Model model, MultipartFile multipartFile);
}
