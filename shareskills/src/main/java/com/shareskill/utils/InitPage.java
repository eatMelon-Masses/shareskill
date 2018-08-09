package com.shareskill.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/*
    为初始化page model提供操作
 */
public class InitPage {
    private static final Log logger = LogFactory.getLog(InitPage.class);
    public static Page getInstence(String uri,int pageNo,int pageSize,int total){
        Page page = new Page(pageSize, uri,total);
        page.setPageNo(pageNo);
        int pageTotal=0;
        int prePageNo=-1;
        int nextPageNo=-1;
        //计算总页数
        if (total>0){
            pageTotal = total / pageSize;
            if ((total%pageSize)>0)pageTotal++;
        }
        page.setPageTotal(pageTotal);
        //计算上一页
        if (pageNo>1){
            prePageNo = pageNo-1;
        }
        //计算下一页
        if (pageNo<pageTotal){
            nextPageNo = pageNo+1;
        }
        page.setPrePageNo(prePageNo);
        page.setNextPageNo(nextPageNo);
        logger.info("page: "+page.toString());
        return page;
    }

}
