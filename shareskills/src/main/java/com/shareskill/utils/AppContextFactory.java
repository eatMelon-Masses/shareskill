package com.shareskill.utils;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class AppContextFactory {
    static ApplicationContext applicationContext=null;
    private static final String APPCONTEXTURL="classpath:/applicationContext.xml";
  //  private static final String APPCONTEXTURL="web/WEB-INF/applicationContext.xml";
    //private static final String APPCONTEXTURL="applicationContext.xml";

    public static Object getBean(String strBean){
        if (applicationContext==null){

       // applicationContext= new FileSystemXmlApplicationContext(APPCONTEXTURL);
        applicationContext= new ClassPathXmlApplicationContext(APPCONTEXTURL);
        }
        Object tempObject=applicationContext.getBean(strBean);
        if (tempObject==null)//如果工厂中找不到对应bean
            throw new NullPointerException(AppContextFactory.class.toString()+"the bean is not finded by the method");
        return tempObject;
    }
    public static ApplicationContext getApplicationContext(){
        if (applicationContext==null){

            applicationContext= new ClassPathXmlApplicationContext(APPCONTEXTURL);
        }

        return applicationContext;
    }
}
