package com.shareskill.service;

import com.shareskill.model.TBlogcomment;

import java.util.List;

public interface BlogCommentService {
    /**添加评论
     * @return
     */
    public Boolean saveOrUpdate(TBlogcomment comment);

    /**删除评论通过
     * @param comId
     * @return
     */
    public Boolean delComment(int comId);

    /**浏览评论按照时间降序
     * @return
     */
    public List<TBlogcomment> browseBlogComment();

    /**浏览评论通过博客id
     * @param blogId
     * @return
     */
    public List<TBlogcomment> browseBlogCommentByBlogId(int blogId);
    public List<TBlogcomment> browseBlogCommentByUserId(int userId);
}
