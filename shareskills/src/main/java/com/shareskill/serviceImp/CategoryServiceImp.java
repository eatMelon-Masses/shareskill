package com.shareskill.serviceImp;

import com.shareskill.dao.BaseDAO;
import com.shareskill.model.TBlogcategorytag;
import com.shareskill.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

public class CategoryServiceImp implements CategoryService{
    @Autowired
    BaseDAO dao;
    @SuppressWarnings("unchecked")
    @Override
    public List<TBlogcategorytag> browseCategory() {

        return dao.listAll("TBlogcategorytag");
        //return new LinkedList<>();
    }

    @Override
    public TBlogcategorytag loadCategory(int id) {
        return (TBlogcategorytag) dao.loadById(TBlogcategorytag.class,id);
    }

    @Override
    public boolean delCategory(Integer id) {
            try{
                dao.delById(TBlogcategorytag.class,id);
                return true;
            }catch (Exception  e){
                e.printStackTrace();
                return false;
            }

    }

    @Override
    public boolean saveOrUpdateCategory(TBlogcategorytag category) {
        try{
            dao.saveOrUpdate(category);
            return true;
        }catch (Exception  e){
            e.printStackTrace();
            return false;
        }
    }

    public BaseDAO getDao() {
        return dao;
    }

    public void setDao(BaseDAO dao) {
        this.dao = dao;
    }
}
