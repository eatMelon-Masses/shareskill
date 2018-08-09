package com.shareskill.tags;

import com.shareskill.model.TBlogcategorytag;
import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.JspTag;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import javax.servlet.jsp.tagext.Tag;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

public class ShowAllCategorys extends SimpleTagSupport{
    private List<TBlogcategorytag> cateList;
    private Writer writer;
    StringBuffer sb = new StringBuffer();
    String viewBlogListByCateUrl="/browseBlogsByCate?order=0&id=";
    ServletRequest request;
    @Override
    public void doTag() throws JspException, IOException {
        super.doTag();
        writer = getJspContext().getOut();
/*        request=((PageContext)getJspContext()).getRequest();
        cateList = (List<TBlogcategorytag>) request.getAttribute("cateList");*/
        if (cateList.size()>0){//第一个选项默认被选中
        TBlogcategorytag tempCate =  cateList.get(0);
            sb.append("<li style=\"background-color: #7AC9CB;\"><a href=\"" + viewBlogListByCateUrl +tempCate.getId()+ "\">" + tempCate.getFlmc() + "</a></li>");
        }
        for (int i=1;i<cateList.size();i++) {//遍历其他选项
            TBlogcategorytag tempCate2 = cateList.get(i);
            sb.append("<li><a href=\"" + viewBlogListByCateUrl+tempCate2.getId() + "\">" +tempCate2.getFlmc()+ "</a></li>");

        }
        //
        writer.write(sb.toString());
    }

    public List<TBlogcategorytag> getCateList() {
        return cateList;
    }

    public void setCateList(List<TBlogcategorytag> cateList) throws JspException {
        //this.cateList =(List<TBlogcategorytag>) ExpressionEvaluatorManager.evaluate("cateList",cateList.toString(),Object.class,(PageContext) getJspContext());
        this.cateList=cateList;
    }
}
