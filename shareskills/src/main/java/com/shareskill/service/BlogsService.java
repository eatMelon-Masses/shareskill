package com.shareskill.service;
import java.util.List;

import com.shareskill.model.TBlog;
import com.shareskill.model.TBlogcategorytag;
import com.shareskill.model.TUser;
import org.springframework.web.bind.annotation.RequestParam;


public interface BlogsService{
	/** 浏览所有博文*/
	public List<TBlog> browseAllBlogs();
	/** 浏览指定博文*/
	public TBlog loadBlogById(Integer id);
	/** 浏览指定会员的博文*/
	public List<TBlog> loadBlogsByMember(TUser user);
	public List<TBlog> loadBlogsByCategory(TBlogcategorytag category, @RequestParam int order);
	/**浏览指定属于指定分类的所有博文*/
	public List<TBlog> loadBlogsByCategory(TBlogcategorytag category, @RequestParam int order,int pageNo,int pageSize);

	/** 新增或修改博文*/
	public Boolean saveOrUpdateBlog(TBlog blog);
	/**删除指定博文*/
	public Boolean delBlogById(int id);

	/**分页浏览所有博文*/

	public List<TBlog> browseAllBlogs(int  pageNo,int pageSize);


	/**
	 * 分页搜索指定博文
	 */
	public List<TBlog> searchBlogs(String searchBlogs,int pageNo,int pageSize);

	public List<TBlog> searchBlogs_json(String searchBlogs);
	/**统计所有博文总数*/
	public int browseAllBlogsCount();

	/**统计指定分类下的所有博文书数
	 * @param category
	 * @param order
	 * @return
	 */
	public int loadBlogByCategoryCount(TBlogcategorytag category, int order);

	/**统计搜素结果条数
	 * @param searchBlogs
	 * @return
	 */
	public int searchBlogsCount(String searchBlogs);
	}