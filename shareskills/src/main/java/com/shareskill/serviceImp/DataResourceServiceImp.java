package com.shareskill.serviceImp;

import com.shareskill.dao.BaseDAO;
import com.shareskill.model.DataCategory;
import com.shareskill.model.TDataresource;
import com.shareskill.service.DataResourceService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;

public class DataResourceServiceImp implements DataResourceService{
    @Autowired
    private BaseDAO dao;
    /**
     * 浏览数据资源
     */
    @Override
    public List<TDataresource> browseDataRes(int order) {
        List<TDataresource> list;
        String hql=null;
        if (order == 0) {//时间顺序
            hql = "from TDataresource as data order by data.scsj desc";
        } else {//点击顺序
            hql = "TDataresource as data order by data.djcs desc";
        }
        try {
            list=dao.query(hql);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 加载指定数据资源
     *
     * @param id
     */
    @Override
    public TDataresource loadDataResById(int id) {
        TDataresource data=null;
        try {
            data = (TDataresource) dao.loadById(TDataresource.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    /**
     * 加载指定分类的所有数据资源
     *
     * @param datacategory
     */
    @Override
    public List<TDataresource> loadDataResByDataCategory(DataCategory datacategory) {
        List<TDataresource> list;
        String hql;

        try {
            hql = "from TDataresource as data where data.zylx=" + datacategory.getId();
            list = dao.query(hql);
        } catch (Exception e) {
            e.printStackTrace();
            list=new ArrayList<>();

        }
        return list;
    }

    @Override
    public List<TDataresource> loadDataResByDataCategory(DataCategory datacategory,String keyWord, int pageNo, int pageSize) {
        List<TDataresource> list;
        String hql;

        try {
            hql = "from TDataresource as data where data.zylx=" + datacategory.getId()+"and ( data.zybt like"+"'%"+keyWord+"%' or data.zyjj like '%"+keyWord+"%')";
            list = dao.query(hql,pageNo,pageSize);
        } catch (Exception e) {
            e.printStackTrace();
            list=new ArrayList<>();

        }
        return list;
    }

    /**
     * 删除指定数据资源
     *
     * @param id
     */
    @Override
    public Boolean delDataResById(Integer id) {
        Boolean status=false;
        try {
                dao.delById(TDataresource.class,id);
            status = true;
        } catch (Exception e) {
            e.printStackTrace();

        }
        return status;
    }

    /**
     * 新增或修改指定数据资源
     *
     * @param data
     */
    @Override
    public Boolean saveOrUpdateDataRes(TDataresource data) {
        Boolean status=false;
        try {
            dao.saveOrUpdate(data);
            status = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  status;
    }

    @Override
    public int loadDataResByDataCategoryCount(int cateId, String keyWord) {
        int count=0;
        String hql;
        try {
            hql = "select count(*) from TDataresource as data where data.zylx=" + cateId + " and ( data.zybt like" + "'%" + keyWord + "%' or data.zyjj like '%" + keyWord + "%')";
            count = dao.countQuery(hql);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return count;
    }

    /**
     * 统计当前登录用户的资源
     *
     * @param userId
     * @return
     */
    @Override
    public int browseDataResourceByUserCount(int userId) {
        int count=0;
        String hql=null;
        try {
            hql = "select count(*) from TDataresource as data where data.userId=" + userId;
           // hql = "select count(*) from TDataresource as data" ;
          //  hql="select count(*) from TDataresource";
            count = dao.countQuery(hql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    /**
     * 当前已登录用户的所有资源
     *
     * @param userId
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public List<TDataresource> browseDataResourceByUser(int userId, int pageNo, int pageSize) {
        List<TDataresource> list=null;
        String hql= null;
        try {
            hql="from TDataresource as data where data.userId="+userId;
            list = dao.query(hql, pageNo, pageSize);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<TDataresource> browseDataResourceByUser(int userId) {
        List<TDataresource> list=null;
        String hql= null;
        try {
            hql="from TDataresource as data where data.userId="+userId;
            list = dao.query(hql);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<TDataresource> browseAllDataResource() {
        List<TDataresource> list =null;
        String hql=null;
        try {
            hql = "from TDataresource as data order by data.scsj desc";
            list = dao.query(hql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public BaseDAO getDao() {
        return dao;
    }

    public void setDao(BaseDAO dao) {
        this.dao = dao;
    }
}
