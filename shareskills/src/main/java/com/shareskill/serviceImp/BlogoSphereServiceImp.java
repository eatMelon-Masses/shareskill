package com.shareskill.serviceImp;

import com.shareskill.dao.BaseDAO;
import com.shareskill.model.BlogoSphere;
import com.shareskill.service.BlogoSphereService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.List;

public class BlogoSphereServiceImp implements BlogoSphereService {
    @Resource
    BaseDAO dao;
    final static Log logger = LogFactory.getLog(BlogoSphereServiceImp.class);
    /**
     * @param blogoSphere 欲添加一条用户说说
     * @return 成功返回true 失败返回false
     */
    @Override
    public Boolean addUserWord(BlogoSphere blogoSphere) {
        Boolean status = false;
        try {
            dao.saveOrUpdate(blogoSphere);
            status=true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    /**
     * 删除一条用户说说
     *
     * @param id
     * @return
     */
    @Override
    public Boolean delUserWordById(Integer id) {
        Boolean status=false;
        try {
            dao.delById(BlogoSphere.class, id);
            status = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    /**
     * 浏览所有说说
     *
     * @return
     */
    @Override
    public List<BlogoSphere> browseAllBlogSphere() {
        return dao.listAll("BlogoSphere");
    }

    /**
     * @param type
     * type 1:通过用户赞做排序
     * Type2:通过用户发说说时间评论
     * @return
     */
    @Override
    public List<BlogoSphere> browseBlogSphereByType(int type,int pageNo,int pageSize) {
        List<BlogoSphere> list=null;
        String hql=null;
        try {
            switch (type) {
                case 1://通过用户点赞次数评论
                    hql = "from BlogoSphere as bs order by bs.wordDate desc";
                    break;
                case 2://通过用户发说说时间评论
                    hql = "from BlogoSphere as bs order by bs.wordDate desc";

                    break;
            }
           // list = dao.listAll(hql, pageNo, pageSize);
            list = dao.query(hql, pageNo, pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<BlogoSphere> browseBlogSphereByUser(Integer userId) {
        List<BlogoSphere> list=null;
        String hql=null;
        try {
            hql = "from BlogoSphere as bs where bs.user=" + userId;
            list = dao.query(hql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 浏览指定用户的的说说
     *
     * @param userId
     */
    @Override
    public List<BlogoSphere> browseBlogSphereByUser(Integer userId,int pageNo,int pageSize) {
        List<BlogoSphere> list=null;
        String hql=null;
        try {
            hql = "from BlogoSphere as bs where bs.user=" + userId;
            list = dao.query(hql,pageNo,pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int browseBlogSphereByUserCount(Integer userId) {
        int count=0;
        String hql=null;
        try {
            hql = "select count(*) from BlogoSphere as bs where bs.user=" + userId;
            count = dao.countQuery(hql);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return count;

    }

    /**
     * @param blogoSphere
     * @return
     */
    @Override
    public Boolean saveOrupdate(BlogoSphere blogoSphere) {
        return null;
    }

    @Override
    public int browseBlogSphereByTypeConut(int type) {
        int count=0;
        String hql=null;
        try {
            switch (type) {
                case 1://通过用户点赞次数评论
                    hql = "select count(*) from BlogoSphere as bs order by bs.wordDate desc";
                    break;
                case 2://通过用户发说说时间评论
                    hql = "select count(*) from BlogoSphere as bs order by bs.wordDate desc";

                    break;
            }
            count = dao.countQuery(hql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public BlogoSphere loadBlogSphereById(Integer id) {
        BlogoSphere bs=null;
        try {
            bs = (BlogoSphere) dao.loadById(BlogoSphere.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bs;
    }

    public BaseDAO getDao() {
        return dao;
    }

    public void setDao(BaseDAO dao) {
        this.dao = dao;
    }
}
