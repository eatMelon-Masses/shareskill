package com.shareskill.controller;

import com.shareskill.model.BlogoSphere;
import com.shareskill.model.TUser;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface BlogoSphereController_json {
    /**返回所有说说按照时间降序排
     * @return
     */
    public List<Object> browseAllBlogoSphere_json();
    public List<Object> delBlogSphereById(HttpServletRequest request,BlogoSphere bsId, BindingResult bindingResult);

    /**浏览指定用户的说说
     * @param
     * @return
     */
    public List<BlogoSphere> browseMyBlogoSphere(TUser user, BindingResult bindingResult);
}

