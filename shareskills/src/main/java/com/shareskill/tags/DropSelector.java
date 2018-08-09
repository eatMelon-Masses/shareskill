package com.shareskill.tags;

import com.shareskill.model.TBlogcategorytag;
import com.shareskill.service.CategoryService;
import com.shareskill.utils.AppContextFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

import java.io.Writer;
import java.util.Iterator;
import java.util.List;

public class DropSelector extends SimpleTagSupport {

    List<TBlogcategorytag> list;
    JspContext jspContext;
    CategoryService categoryService=(CategoryService) AppContextFactory.getBean("categoryService");
    @Override
    public void doTag() throws JspException, IOException {
        jspContext=getJspContext();
        Writer writer =jspContext.getOut();
        list = categoryService.browseCategory();
        Iterator<TBlogcategorytag> i =list.iterator();

        while(i.hasNext()){
            TBlogcategorytag temp =i.next();
            writer.write("<option value="+ temp.getId()+">"+temp.getFlmc()+"</option>");

        }

    }




}
