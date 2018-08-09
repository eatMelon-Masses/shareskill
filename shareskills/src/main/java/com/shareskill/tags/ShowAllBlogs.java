package com.shareskill.tags;

import com.shareskill.model.TBlog;
import com.shareskill.model.TBlogcategorytag;
import com.shareskill.service.CategoryService;
import com.shareskill.utils.AppContextFactory;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

public class ShowAllBlogs extends SimpleTagSupport {
    List<TBlog> blogList;
    private String pathBase;
   //  = (CategoryService) AppContextFactory.getBean("categoryService");
    CategoryService categoryService;
   // JspContext jspContext;
   // Writer writer;
    StringBuffer sb = new StringBuffer();
    private final String blogDetailUrl="/viewBlog?id=";

    @Override
    public void doTag() throws JspException, IOException {
        super.doTag();

        categoryService = (CategoryService) WebApplicationContextUtils.getRequiredWebApplicationContext(((PageContext)getJspContext()).getServletContext()).getBean("categoryService");
        TBlogcategorytag tempCate=null;
        for (TBlog blog : blogList) {
            if (blog.getBlogCategoryId() != null) {
                tempCate = categoryService.loadCategory(blog.getBlogCategoryId());
                sb.append("<dl>\n");
                sb.append("<dt><a href=" + blogDetailUrl+blog.getId() + ">" + blog.getBwbt() + "</a> <input></dt>");
                sb.append("<dd>\n");
                sb.append("<div id=\"fenlei\">" + "分类:" + tempCate.getFlmc() + "</div>\n");
                sb.append("<input type=\"datetime\" id=\"dataTime\" value=" + blog.getBwcjsj().toString()+"/>\n");
                sb.append("<input type=\"image\" name=\"criticize\" id=\"criticize\" src=\"../img/评论.png\" /> ");
                sb.append("<p>" + "访问量:" + Integer.toString(blog.getBwdjcs()) + "</p>");
                sb.append("</dd>\n");
                sb.append("</dl>\n");


            }
        }
        getJspContext().getOut().println(sb);
        //.write(sb.toString());

    }

    public List<TBlog> getBlogList() {
        return blogList;
    }

    public void setBlogList(List<TBlog> blogList) {
        this.blogList = blogList;
    }

    public String getPathBase() {
        return pathBase;
    }

    public void setPathBase(String pathBase) {
        this.pathBase = pathBase;
    }
}
