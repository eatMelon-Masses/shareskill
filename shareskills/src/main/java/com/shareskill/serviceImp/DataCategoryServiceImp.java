package com.shareskill.serviceImp;

import com.shareskill.dao.BaseDAO;
import com.shareskill.model.DataCategory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.xml.crypto.Data;
import java.util.List;

public class DataCategoryServiceImp implements com.shareskill.service.DataCategoryService {
   @Autowired
    private BaseDAO dao;
    /**
     * 浏览所有数据分类资源
     */
    @Override
    public List<DataCategory> browseAllDataCategory() {
        List<DataCategory> tempList=null;
        try {
            tempList=dao.listAll("DataCategory");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tempList;
    }

    /**
     * 通过id查数据分类
     */
    @Override
    public DataCategory loadDataCategoryById(Integer id) {
        DataCategory dataCategory=null;
        try {
            dataCategory = (DataCategory) dao.loadById(DataCategory.class,id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataCategory;
    }

    public BaseDAO getDao() {
        return dao;
    }

    public void setDao(BaseDAO dao) {
        this.dao = dao;
    }
}
