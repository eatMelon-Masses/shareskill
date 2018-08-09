package com.shareskill.service;

import com.shareskill.model.BlogoSphere;

import java.util.List;

public interface BlogoSphereService {

    /**
     * @param blogoSphere 欲添加一条用户说说
     * @return 成功返回true 失败返回false
     */
    public Boolean addUserWord(BlogoSphere blogoSphere);

    /**删除一条用户说说
     * @param id
     * @return
     */
    public Boolean delUserWordById(Integer id);

    /**浏览所有说说
     * @return
     */
    public List<BlogoSphere> browseAllBlogSphere();

    public List<BlogoSphere> browseBlogSphereByType(int type,int pageNo,int pageSize);
    /**浏览指定用户的的说说*/
    public List<BlogoSphere> browseBlogSphereByUser(Integer userId);
    /**浏览指定用户的的说说*/
    public List<BlogoSphere> browseBlogSphereByUser(Integer userId,int pageNo,int pageSize);
    public int browseBlogSphereByUserCount(Integer userId);

    /**
     * @param blogoSphere
     * @return
     */
    public Boolean saveOrupdate(BlogoSphere blogoSphere);

    /**通过类型查找匹配结合的数量
     * @param type
     * @return
     */
    public int browseBlogSphereByTypeConut(int type);
    public BlogoSphere loadBlogSphereById(Integer id);
}
