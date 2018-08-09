package com.shareskill.controllerimp;

import com.shareskill.controller.CategoryController;
import com.shareskill.model.TBlog;
import com.shareskill.model.TBlogcategorytag;
import com.shareskill.serviceImp.CategoryServiceImp;
import com.shareskill.utils.JsonUtils;
import net.sf.json.JSONArray;
import net.sf.json.util.JSONUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class CategoryContorllerImp implements CategoryController {
    @Resource
    private CategoryServiceImp categoryService;

    /**
     * 新增文章分类
     *
     * @param response
     * @param category
     */
    @Override
    public void addBlogCategory(HttpServletResponse response, TBlogcategorytag category) {
        JSONArray  jsonArray =new JSONArray();

            if(categoryService.saveOrUpdateCategory(category)){
                JsonUtils.addDateToJsonArray(jsonArray,"addBlogCategory","操作成功");
            }else {
                JsonUtils.addDateToJsonArray(jsonArray,"addBlogCategory","操作失败");
            }
            JsonUtils.ajaxJson(jsonArray.toString(),response);
    }

    /**
     * 删除文章分类
     *
     * @param response
     */
    @Override
    public void delBlogCategory(HttpServletResponse response,Integer id) {
        JSONArray jsonArray =new JSONArray();
        if (categoryService.delCategory(id)) {
            JsonUtils.addDateToJsonArray(jsonArray,"delBlogCategory","success");

        }else{
            JsonUtils.addDateToJsonArray(jsonArray,"delBlogCategory","failure");
        }
        JsonUtils.ajaxJson(jsonArray.toString(),response);
    }

    /**
     * 修改文章分类
     *
     * @param response
     * @param category
     */
    @Override
    public void updateBlogCategory(HttpServletResponse response, TBlogcategorytag category) {
        JSONArray jsonArray = new JSONArray();
        TBlogcategorytag tempCategory=null;
        //用户填写的分类id是否符合要求
        if(category.getId()!=null){
            //去数据库中查找此id的数据
            tempCategory=categoryService.loadCategory(category.getId());
            //数据库中是否有此用户信息
            if(tempCategory!=null){
                //对比更新用户信息
                if (category.getFlmc()!=null&&category.getFlmc().toString().trim().length()>0)tempCategory.setFlmc(category.getFlmc());
                //更新hibernate实体
                if (categoryService.saveOrUpdateCategory(tempCategory))
                    JsonUtils.addDateToJsonArray(jsonArray,"updaeBlogCategory","success");
                else
                    JsonUtils.addDateToJsonArray(jsonArray,"updaeBlogCategory","failure1");


            }else{
                JsonUtils.addDateToJsonArray(jsonArray,"updateBlogCategory","failure2");
            }
        }else{
            JsonUtils.addDateToJsonArray(jsonArray,"updateBlogCategory","failure3");
        }
        //写入输出流
        JsonUtils.ajaxJson(jsonArray.toString(),response);
    }

    /**
     * 查看指定文章分类
     *
     * @param response
     * @param id
     */
    @Override
    public void viewBlogCategory(HttpServletResponse response, Integer id) {
       JSONArray jsonArray = new JSONArray();
        TBlogcategorytag tempCategory = null;
        try{
           tempCategory = categoryService.loadCategory(id);
            JsonUtils.addDateToJsonArray(jsonArray,"viewBlogCategory","success");
            //写入查询结果
            jsonArray.add(tempCategory);
       }catch (Exception e){
           e.printStackTrace();

            JsonUtils.addDateToJsonArray(jsonArray,"viewBlogCategory","failure");
       }
       JsonUtils.ajaxJson(jsonArray.toString(),response);
    }

    /**
     * 浏览文章分类
     *
     * @param response
     */
    @Override
    public void browseBlogCategory(HttpServletResponse response) {
        JSONArray jsonArray = new JSONArray();
        List<TBlogcategorytag> tempList=null;
        try{
            tempList = categoryService.browseCategory();
            JsonUtils.addDateToJsonArray(jsonArray,"browseBlogCategory","success");
            jsonArray.add(tempList);
        }catch (Exception e){
            e.printStackTrace();
            JsonUtils.addDateToJsonArray(jsonArray,"browseBlogCategory","failure");

        }
        JsonUtils.ajaxJson(jsonArray.toString(),response);
    }

    public CategoryServiceImp getCategoryService() {
        return categoryService;
    }

    public void setCategoryService(CategoryServiceImp categoryService) {
        this.categoryService = categoryService;
    }
}
