package com.shareskill.tags;


import com.shareskill.dao.BaseDAO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/** 新闻搜索分页控制自定义标签类 */
public class SearchDataByCateTag extends SimpleTagSupport {
	String hql= null;
	String keyword;	 		//搜索关键字
	Integer cateId;
	String url = "";		//分页调用的URL,此项为必填项
	int pageNo = 1;			//页号
	int pageSize=2;      	//每页新闻条数,,默认值24
	int pageTotal=1;      	//总页数

	int prePageNo = 1;		//上一页页号
	int nextPageNo = 1;		//下一页页号
	private static final Log logger = LogFactory.getLog(SearchDataByCateTag.class);
	/** 标签体处理 */
    public void doTag() throws JspException, IOException{
    	//使用WebApplicationContextUtils工具类获取Spring IOC容器中的dao实例
    	BaseDAO dao = (BaseDAO) WebApplicationContextUtils.getRequiredWebApplicationContext(((PageContext)getJspContext()).getServletContext()).getBean("dao");
    	//hql="from TDataresource as data where data.zylx="+cateId+"and ( data.zybt like"+"'%"+keyword+"%' or data.zyjj like '%"+keyword+"%')";
		hql="select count(*) from TDataresource as data where data.zylx="+cateId+" and ( data.zybt like"+"'%"+keyword+"%' or data.zyjj like '%"+keyword+"%')";
		StringBuffer sb=new StringBuffer();
		//统计满足条件的数据条数
		int total=dao.countQuery(hql);
		logger.info("keyword:"+keyword);
		logger.info("total:"+total);
		//计算总页数
		if (total>0){
			pageTotal = total / pageSize;
			if ((total%pageSize)>0)pageTotal++;
		}
		//计算上一页
		if (pageNo>1){
			prePageNo = pageNo-1;
		}else{
			prePageNo = 1;
		}
		//计算下一页
		if (pageNo<pageTotal){
			nextPageNo = pageNo+1;
		}else{
			nextPageNo = pageTotal;
		}
		//构造出url
		if (url.indexOf("?")!=-1){
			url = url+"&";
		}else{
			url = url+"?";
		}
		sb.append("<div>当前第["+pageNo+"/"+pageTotal+"]页 [<a target='_self' href='"+url+"pageNo=1'>首页</a>] ");
		//第一页时,"上一页"链接无效
		if (pageNo==1){
			sb.append("[上一页] ");
		}else{
			sb.append("[<a target='_self' href='"+url+"pageNo="+prePageNo+"'>上一页</a>] ");
		}
		//最末页时,"下一页"链接无效
		if (pageNo==pageTotal){
			sb.append("[下一页] ");
		}else{
			sb.append("[<a target='_self' href='"+url+"pageNo="+nextPageNo+"'>下一页</a>] ");
		}
		sb.append("[<a target='_self' href='"+url+"pageNo="+pageTotal+"'>尾页</a>]</div>");
    	//输出处理结果到页面上
    	getJspContext().getOut().println(sb); 
	}
	
	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageTotal() {
		return pageTotal;
	}

	public void setPageTotal(int pageTotal) {
		this.pageTotal = pageTotal;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Integer getCateId() {
		return cateId;
	}

	public void setCateId(Integer cateId) {
		this.cateId = cateId;
	}
}
