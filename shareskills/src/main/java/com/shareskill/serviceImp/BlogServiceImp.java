package com.shareskill.serviceImp;

import com.shareskill.dao.BaseDAO;
import com.shareskill.model.TBlog;
import com.shareskill.model.TBlogcategorytag;
import com.shareskill.model.TUser;
import com.shareskill.service.BlogsService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

public class BlogServiceImp implements BlogsService{
    @Resource
    BaseDAO dao;
    private final static Log logger= LogFactory.getLog(BlogServiceImp.class);
    /**
     * 浏览所有博文
     */
    @Override
    public List<TBlog> browseAllBlogs() {
        return dao.listAll("TBlog");
    }

    /**
     * 浏览指定博文
     *
     * @param id
     */
    @Override
    public TBlog loadBlogById(Integer id) {
        TBlog temp=null;
        try{
            temp= (TBlog) dao.loadById(TBlog.class,id);
        }catch (Exception e){
            e.printStackTrace();

        }
        return temp;
    }

    /**
     * 浏览指定会员的博文
     *
     * @param user
     */
    @Override
    public List<TBlog> loadBlogsByMember(TUser user) {
        Integer temId=user.getId();
        List<TBlog> tempList=null;
        String hql=null;
        try{
            if (temId!=null){
                hql=new String("from TBlog as blog where blog.userId="+temId.toString());
            tempList=  dao.query(hql);
            }
        }catch (Exception e){
            e.printStackTrace();

        }
        return tempList;
    }

    @Override
    public List<TBlog> loadBlogsByCategory(TBlogcategorytag category, int order) {
        List<TBlog> tempList=null;
        Integer tempCateId=category.getId();
        String hql =null;
        if(category!=null){
            if (order==0)
                hql=new String("from TBlog as blog where blog.blogCategoryId="+tempCateId.toString()+"order by blog.bwcjsj desc");
            else if(order==1)
                hql=new String("from TBlog as blog where blog.blogCategoryId="+tempCateId.toString()+"order by blog.bwdjcs desc");
            else
                hql=new String("from TBlog as blog where blog.blogCategoryId="+tempCateId.toString());
            try{
                tempList=dao.query(hql);
            }catch (Exception e){
                e.printStackTrace();
            }
        }else{
            logger.info(this.toString()+"failure");
        }

        return tempList;

    }

    /**
     * 浏览指定属于指定分类的所有博文
     *@param order 排序方式 1按照时间排序,2按照搜索热度排序
     * @param category
     */
    @Override
    public List<TBlog> loadBlogsByCategory(TBlogcategorytag category, @RequestParam int order,int pageNo,int pageSize) {
        List<TBlog> tempList=null;
        Integer tempCateId=category.getId();
        String hql =null;
        if(category!=null){
            if (order==0)
                hql=new String("from TBlog as blog where blog.blogCategoryId="+tempCateId.toString()+"order by blog.bwcjsj desc");
            else if(order==1)
                hql=new String("from TBlog as blog where blog.blogCategoryId="+tempCateId.toString()+"order by blog.bwdjcs desc");
            else
                hql=new String("from TBlog as blog where blog.blogCategoryId="+tempCateId.toString());
            try{
            tempList=dao.query(hql,pageNo,pageSize);
            }catch (Exception e){
                e.printStackTrace();
            }
        }else{
            logger.info(this.toString()+"failure");
        }

        return tempList;
    }

    /**
     * 新增或修改博文
     *
     * @param blog
     */
    @Override
    public Boolean saveOrUpdateBlog(TBlog blog) {
        Boolean status =false;
        try {
            dao.saveOrUpdate(blog);
            status=true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return status;
    }

    /**
     * 删除指定博文
     *
     * @param id
     */
    @Override
    public Boolean delBlogById(int id) {
        Boolean status=false;
        try{
            dao.delById(TBlog.class,id);
            status=true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public List<TBlog> browseAllBlogs(int pageNo, int pageSize) {
        List<TBlog> list=null;
        String hql=null;
        try {
            hql = "from TBlog as blog order by blog.bwcjsj desc";
            list = dao.query(hql, pageNo, pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    /**
     * 搜索指定博文
     *
     * @param searchBlogs
     */
    @Override
    public List<TBlog> searchBlogs(String searchBlogs,int pageNo,int pageSize) {
        List<TBlog> list=null;
        String hql=null;
        if (searchBlogs != null) {
            hql = " from TBlog as blog where blog.bwnr like '%" + searchBlogs + "%' or blog.bwbt like '%"+searchBlogs+"%'";
            list = dao.query(hql,pageNo,pageSize);
        } else {
            new Throwable("search var is null");
            logger.info("search var is null");
        }
        return list;
    }

    @Override
    public List<TBlog> searchBlogs_json(String searchBlogs) {
        List<TBlog> list=null;
        String hql=null;
        if (searchBlogs != null) {
            hql = " from TBlog as blog where blog.bwnr like '%" + searchBlogs + "%' or blog.bwbt like '%"+searchBlogs+"%'";
            list = dao.query(hql);
        } else {
            new Throwable("search var is null");
            logger.info("search var is null");
        }
        return list;
    }

    @Override
    public int browseAllBlogsCount() {
        String hql=null;
        int count=0;
        try {
            hql = "select count(*) from TBlog ";
            count = dao.countQuery(hql);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public int loadBlogByCategoryCount(TBlogcategorytag category, int order) {
        int count=0;
        Integer tempCateId=category.getId();
        String hql =null;
        if(category!=null){
            if (order==0)
                hql=new String("select count(*) from TBlog as blog where blog.blogCategoryId="+tempCateId.toString()+"order by blog.bwcjsj desc");
            else if(order==1)
                hql=new String("select count(*) from TBlog as blog where blog.blogCategoryId="+tempCateId.toString()+"order by blog.bwdjcs desc");
            else
                hql=new String("select count(*) from TBlog as blog where blog.blogCategoryId="+tempCateId.toString());
            try{
                count=dao.countQuery(hql);
            }catch (Exception e){
                e.printStackTrace();
            }
        }else{
            logger.info(this.toString()+"failure");
        }
        return count;

    }

    @Override
    public int searchBlogsCount(String searchBlogs) {
        int count=0;
        String hql=null;
        if (searchBlogs != null) {
            hql = " select count(*) from TBlog as blog where blog.bwnr like '%" + searchBlogs + "%' or blog.bwbt like '%"+searchBlogs+"%'";
            count = dao.countQuery(hql);
        } else {
            new Throwable("search var is null");
            logger.info("search var is null");
        }
        return count;

    }

    public BaseDAO getDao() {
        return dao;
    }

    public void setDao(BaseDAO dao) {
        this.dao = dao;
    }

    public static Log getLogger() {
        return logger;
    }
}
