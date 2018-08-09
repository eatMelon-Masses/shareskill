package com.shareskill.tags;

import com.shareskill.model.TUser;
import com.shareskill.service.UserService;
import com.shareskill.utils.AppContextFactory;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;
import java.util.List;

public class ShowAllUser extends SimpleTagSupport{
    //private UserService userService = (UserService) AppContextFactory.getBean("userService");
    JspContext jspContext;
    List<TUser> userList;
    String delUserUrl = "/";

    @Override
    public void doTag() throws JspException, IOException {
        super.doTag();
        StringBuffer sb = new StringBuffer();
        //userList =userService.browseMember();
        jspContext=getJspContext();
        Writer writer =jspContext.getOut();

        //Iterator iterable = userList.iterator();
                int i=1;
                for (TUser tempUser : userList) {
                    sb.append("<tr>\n" +
                            "<td>" + i + "</td>\n" +
                            "<td>" + tempUser.getZh() + "</td>\n" +
                            "<td>" + tempUser.getXb() + "</td>\n" +
                            "<td>" + tempUser.getCsny().toLocaleString() + "</td>\n" );
                    sb.append("<td>" + new String("可用".getBytes(), "GBK")+"</td>\n");
                    sb.append("<td><a href=>"+new String("删除".getBytes("GBK"),"GBK")+
                            "</a><a href=>"+new String("修改".getBytes("GBK"),"GBK")+"</a>");
                    i++;
                }
        //String tempStr = new String(sb.toString().getBytes(), "gbk");
        //writer.write(new String(sb.toString().getBytes("GBK"),"GBK"));
        getJspContext().getOut().println(sb);
    }

    public List<TUser> getUserList() {
        return userList;
    }
    public void setUserList(List<TUser> userList) {
        this.userList = userList;
    }
}
