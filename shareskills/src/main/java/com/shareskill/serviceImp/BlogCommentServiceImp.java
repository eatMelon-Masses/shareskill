package com.shareskill.serviceImp;

import com.shareskill.dao.BaseDAO;
import com.shareskill.model.TBlogcomment;
import com.shareskill.service.BlogCommentService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BlogCommentServiceImp implements BlogCommentService {
    @Autowired
    private BaseDAO dao;
    /**
     * 添加评论
     *
     * @param comment
     * @return
     */
    @Override
    public Boolean saveOrUpdate(TBlogcomment comment) {
        Boolean status = false;
        try {
            dao.saveOrUpdate(comment);
            status = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    /**
     * 删除评论通过
     *
     * @param comId
     * @return
     */
    @Override
    public Boolean delComment(int comId) {
        Boolean status = false;
        try {
            dao.delById(TBlogcomment.class, comId);
            status = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    /**
     * 浏览评论按照时间降序
     *
     * @return
     */
    @Override
    public List<TBlogcomment> browseBlogComment() {
        List list=null;
        String hql=null;
        try {
            hql = "from TBlogcomment as bc order by bc.plsj";
            list = dao.query(hql);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 浏览评论通过博客id
     *
     * @param blogId
     * @return
     */
    @Override
    public List<TBlogcomment> browseBlogCommentByBlogId(int blogId) {
        List<TBlogcomment> list = null;
        String hql=null;
        try {
            hql = "from TBlogcomment as bc where bc.blog=" + blogId;
            list = dao.query(hql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**浏览自己的所有评论
     * @param userId
     * @return
     */
    @Override
    public List<TBlogcomment> browseBlogCommentByUserId(int userId) {
        List list=null;
        String hql=null;
        try {
            hql = "from TBlogcomment bc where bc.user.id=" + userId;
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
