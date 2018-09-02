package com.shareskill.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.shareskill.model.TBlogcomment;
import com.shareskill.model.TUser;
import com.shareskill.utils.JsonDateTimeSerializer;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.io.Serializable;
import java.util.Date;

@JsonIgnoreProperties(value={"hibernateLazyInitializer"})
public class BlogCommentVo implements Serializable {
    public BlogCommentVo(int id) {
        this.id = id;
    }

    private int id;
    private String  userName;
    private Integer blogId;

    private Date plsj;
    private String plnr;

    public BlogCommentVo(TBlogcomment blogcomment) {
        if (null!=blogcomment.getUser())
        this.userName = blogcomment.getUser().getYhnc();
        this.blogId = blogcomment.getBlog();
        this.plsj = blogcomment.getPlsj();
        this.plnr = blogcomment.getPlnr();
    }

    @Override
    public String toString() {
        return "BlogCommentVo{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", blogId=" + blogId +
                ", plsj=" + plsj +
                ", plnr='" + plnr + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getBlogId() {
        return blogId;
    }

    public void setBlogId(Integer blogId) {
        this.blogId = blogId;
    }
    @JsonSerialize(using= JsonDateTimeSerializer.class)
    public Date getPlsj() {
        return plsj;
    }
    @JsonSerialize(using= JsonDateTimeSerializer.class)
    public void setPlsj(Date plsj) {
        this.plsj = plsj;
    }

    public String getPlnr() {
        return plnr;
    }

    public void setPlnr(String plnr) {
        this.plnr = plnr;
    }
}
