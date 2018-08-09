package com.shareskill.serviceImp;

import com.shareskill.dao.BaseDaoImp;
import com.shareskill.model.TBlogcategorytag;
import com.shareskill.service.CategoryService;
import org.apache.commons.logging.LogFactory;


import org.apache.commons.logging.Log;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.util.List;


import static org.junit.Assert.*;
/*@RunWith(SpringJUnit4ClassRunner.class)*/
/*@ContextConfiguration({"classpath:/applicationContext.xml","classpath:/spring-servlet.xml"})*/
/*@TestExecutionListeners( { DependencyInjectionTestExecutionListener.class})*/
public class CategoryServiceImpTest {
    List<TBlogcategorytag> list=null;
    CategoryServiceImp categoryService;
    final static Log logger = LogFactory.getLog(CategoryServiceImpTest.class);
    ApplicationContext applicationContext=null;
    AdminServiceImp adminServiceImp;

    @Before
    public void setUp() throws Exception {
//        categoryService.setDao(new BaseDaoImp());
        applicationContext=new ClassPathXmlApplicationContext("classpath:/applicationContext.xml");
/*        HibernateTemplate hibernateTemplate=(HibernateTemplate)applicationContext.getBean("hibernateTemplate");
        ((BaseDaoImp)categoryService.getDao()).setHibernateTemplate(hibernateTemplate);*/
        categoryService = (CategoryServiceImp)applicationContext.getBean("categoryService");
        //adminServiceImp =(AdminServiceImp) applicationContext.getBean("adminService");
    }

    @After
    public void tearDown() throws Exception {   }

    @Test
    public void browseCategory() {

/*        list=categoryService.browseCategory();
        logger.info(list.size());*/
/*    adminServiceImp.browseAdmin();
    logger.info(adminServiceImp.browseAdmin().get(0).getZh());*/
    logger.info(categoryService.browseCategory().size());
    }

    @Test
    public void loadCategory() {
    }

    @Test
    public void delCategory() {
    }

    @Test
    public void saveOrUpdateCategory() {
    }
}