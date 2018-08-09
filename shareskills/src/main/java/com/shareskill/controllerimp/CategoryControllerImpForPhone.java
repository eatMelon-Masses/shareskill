package com.shareskill.controllerimp;

import com.shareskill.controller.CategoryControllerForPhone;
import com.shareskill.model.AbstractTBlogcategorytag;
import com.shareskill.model.TBlogcategorytag;
import com.shareskill.service.CategoryService;
import net.sf.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;


import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CategoryControllerImpForPhone  implements CategoryControllerForPhone {
    @Resource
    private CategoryService categoryService;
    final static String RESULT = "result";
    final static String MESSAGE = "message";
    final static String TRUE = "true";
    final static String FALSE = "false";
    final static Log logger = LogFactory.getLog(CategoryControllerForPhone.class);
    @ResponseBody
    @RequestMapping("/json/addBlogCategory")
    @Override
    public List<Object> addBlogCategory(@Validated(AbstractTBlogcategorytag.BlogCateRuleA.class) TBlogcategorytag category, BindingResult bindingResult) {
        List<Object> jsonList = new ArrayList<>();
        StringBuffer message = new StringBuffer();
        Map<String, Object> mapList = new HashMap<>();
        mapList.put(RESULT,FALSE);
        mapList.put(MESSAGE, message);
        jsonList.add(mapList);
        if (bindingResult.hasErrors()) {//遍历错误信息
            List<ObjectError> errorList = bindingResult.getAllErrors();
            for (ObjectError error : errorList) {
                logger.info(error.getDefaultMessage());
                message.append(error.getDefaultMessage());
            }
            return jsonList;
        }
        if (categoryService.saveOrUpdateCategory(category)) {
            mapList.put(RESULT, TRUE);
            message.append("保存成功");
        } else {
            message.append("保存失败,未知原因");
        }
        return jsonList;
    }



    /**
     * 删除文章分类
     *
     * @param id
     */
    @ResponseBody
    @RequestMapping("/josn/delBlogCategory")
    @Override
    public List<Object> delBlogCategory(@RequestParam Integer id) {
        List<Object> jsonList = new ArrayList<>();
        StringBuffer message = new StringBuffer();
        Map<String, Object> mapList = new HashMap<>();
        mapList.put(RESULT,FALSE);
        mapList.put(MESSAGE, message);
        jsonList.add(mapList);

        mapList.put(RESULT, TRUE);
        return jsonList;
    }


    /**
     * 修改文章分类
     *
     * @param category
     */
    @ResponseBody
    @RequestMapping("/json/updateBlogCategory")
    @Override
    public List<Object> updateBlogCategory(@Validated(AbstractTBlogcategorytag.BlogCateRuleC.class) TBlogcategorytag category, BindingResult bindingResult) {
        List<Object> jsonList = new ArrayList<>();
        StringBuffer message = new StringBuffer();
        Map<String, Object> mapList = new HashMap<>();
        mapList.put(RESULT,FALSE);
        mapList.put(MESSAGE, message);
        jsonList.add(mapList);
        if (bindingResult.hasErrors()) {//遍历错误信息
            List<ObjectError> errorList = bindingResult.getAllErrors();
            for (ObjectError error : errorList) {
                logger.info(error.getDefaultMessage());
                message.append(error.getDefaultMessage());
            }
            return jsonList;
        }
        TBlogcategorytag tempCate = categoryService.loadCategory(category.getId());
        if (tempCate != null) {//如果存在此分类
            tempCate.setFlmc(category.getFlmc());
            if ( categoryService.saveOrUpdateCategory(tempCate)) {
                message.append("修改成功");
            }
        } else {//不存在
            message.append("不存在此分类");
            return jsonList;
        }
        return jsonList;
    }

    /**
     * 查看指定文章分类
     *
     * @param id
     */
    @ResponseBody
    @RequestMapping("/json/viewBlogCategory")
    @Override
    public List<Object> viewBlogCategory(@Validated(AbstractTBlogcategorytag.BlogCateRuleB.class) TBlogcategorytag category, BindingResult bindingResult) {
        List<Object> jsonList = new ArrayList<>();
        StringBuffer message = new StringBuffer();
        Map<String, Object> mapList = new HashMap<>();
        mapList.put(RESULT,FALSE);
        mapList.put(MESSAGE, message);

        if (bindingResult.hasErrors()) {//遍历错误信息
            List<ObjectError> errorList = bindingResult.getAllErrors();
            for (ObjectError error : errorList) {
                logger.info(error.getDefaultMessage());
                message.append(error.getDefaultMessage());
            }
            jsonList.add(mapList);
            return jsonList;
        }
        TBlogcategorytag blogcategorytag = categoryService.loadCategory(category.getId());
        jsonList.add(blogcategorytag);
        return jsonList;
    }

    /**
     * 浏览文章分类
     *
     * @param model
     */
    @RequestMapping("/json/browseBlogCategory")
    @ResponseBody
    @Override
    public List<TBlogcategorytag> browseBlogCategory(Model model) {
       // List<Object> jsonList = new ArrayList<>();
        //jsonList.add(categoryService.browseCategory());
        return categoryService.browseCategory();
    }

    public CategoryService getCategoryService() {
        return categoryService;
    }

    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
}
