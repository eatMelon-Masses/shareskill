package com.shareskill.controllerimp;

import com.shareskill.controller.ManageHomeController;
import com.shareskill.service.BlogsService;
import com.shareskill.service.CategoryService;
import com.shareskill.service.UserService;
import com.shareskill.serviceImp.CategoryServiceImp;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ManageHomeControllerImp implements ManageHomeController {
    @Autowired
    private UserService userService;//账号服务
    @Autowired
    private BlogsService blogsService;//博文服务
    @Autowired
    private CategoryService categoryService;//分类服务
    private final static String MANAGEHOMEVIEW = "admin/manage";
    private final static Log logger = LogFactory.getLog(ManageHomeControllerImp.class);
    /**
     * 主页请求处理器
     *
     * @param model
     */
    @RequestMapping("/showManageHome")
    @Override
    public String showManageHome(Model model) {
        model.addAttribute("userList", userService.browseMember());
        model.addAttribute("cateList", categoryService.browseCategory());

        return MANAGEHOMEVIEW;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public BlogsService getBlogsService() {
        return blogsService;
    }

    public void setBlogsService(BlogsService blogsService) {
        this.blogsService = blogsService;
    }

    public CategoryService getCategoryService() {
        return categoryService;
    }

    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
}
