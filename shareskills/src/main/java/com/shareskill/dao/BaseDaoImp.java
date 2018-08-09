package com.shareskill.dao;
import javax.annotation.Resource;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Service;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

public class BaseDaoImp extends HibernateDaoSupport implements BaseDAO  {
	private static final Log log=LogFactory.getLog(BaseDaoImp.class);
	@Resource
	HibernateTemplate hibernateTemplate;
	@Override
	public Object loadById(Class clazz, int id) {
		// TODO Auto-generated method stub
		
		return hibernateTemplate.get(clazz, id);
	}

	@Override
	public Object loadObject(String hql) {
		// TODO Auto-generated method stub
		final String hql1=hql;
		Object temp=null;
		List list =hibernateTemplate.executeFind(new HibernateCallback(){

			@Override
			public Object doInHibernate(Session arg0) throws HibernateException, SQLException {
				// TODO Auto-generated method stub
				Query query=arg0.createQuery(hql1);
				return query.list();
			}	
		});
		if(list.size()>0)temp=list.get(0);
		return temp;
		
	}

	@Override
	public void delById(Class clazz, Serializable id) {
		// TODO Auto-generated method stub
		hibernateTemplate.delete(hibernateTemplate.get(clazz, id));
	}

	@Override
	public void saveOrUpdate(Object obj) {
		// TODO Auto-generated method stub
		hibernateTemplate.saveOrUpdate(obj);

	}

	@Override
	public List listAll(String clazz) {
		// TODO Auto-generated method stub
	//	return hibernateTemplate.find("from"+clazz+"as a order by a.id");
		return hibernateTemplate.find("from "+clazz);
	}

	@Override
	public List listAll(String clazz, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		final int pNo=pageNo;
		final int pSize=pageSize;
		final String hql="from "+clazz+" as a order by a.id";
		List list=hibernateTemplate.executeFind(new HibernateCallback(){

			@Override
			public Object doInHibernate(Session arg0) throws HibernateException, SQLException {
				// TODO Auto-generated method stub
				
				Query query=arg0.createQuery(hql);
				query.setMaxResults(pSize);
				query.setFirstResult((pNo-1)*pSize);
				List result=query.list();
				if(!Hibernate.isInitialized(result))Hibernate.initialize(result);//解除延迟加载
				return result;
			}});
		
		
		return list;
	}

	@Override
	public int countAll(String clazz) {
		// TODO Auto-generated method stub
		final String hql = "select count(*) from "+clazz+ " as a";
		Long count = (Long)hibernateTemplate.execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException{
				Query query = session.createQuery(hql);
				query.setMaxResults(1);
				return query.uniqueResult();
			}
		});	
		return count.intValue();
	}

	@Override
	public List query(String hql) {
		// TODO Auto-generated method stub
		final String hql1=hql;
		List list=hibernateTemplate.executeFind(new HibernateCallback(){
			@Override
			public Object doInHibernate(Session arg0) throws HibernateException, SQLException {
				// TODO Auto-generated method stub
				Query query=arg0.createQuery(hql1);
				List result=query.list();
				return result;
			}
			
		});
		
		return list;
	}

	@Override
	public List query(String hql, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		final String hql1=hql;
		final int pNo=pageNo;
		final int pSize=pageSize;
		List list = hibernateTemplate.executeFind(new HibernateCallback(){

			@Override
			public Object doInHibernate(Session arg0) throws HibernateException, SQLException {
				// TODO Auto-generated method stub
				Query query=arg0.createQuery(hql1);
				query.setMaxResults(pSize);
				query.setFirstResult((pNo-1)*pSize);
				List result=query.list();
				if(Hibernate.isInitialized(result))Hibernate.isInitialized(result);
				return result;
			}});
		return list;
	}

	@Override
	public int countQuery(String hql) {
		// TODO Auto-generated method stub
		final String  hql1=hql;
		Long count;
		 count = (Long)hibernateTemplate.execute(new HibernateCallback(){

			@Override
			public Object doInHibernate(Session arg0) throws HibernateException, SQLException {
				// TODO Auto-generated method stub
				Query query = arg0.createQuery(hql1);
				query.setMaxResults(1);
				
				return query.uniqueResult();
			}});
		return count.intValue();
	}

	@Override
	public int update(String hql) {
		// TODO Auto-generated method stub
		final String hql1=hql;
		Object updateCount=hibernateTemplate.execute(new HibernateCallback(){

			@Override
			public Object doInHibernate(Session arg0) throws HibernateException, SQLException {
				// TODO Auto-generated method stub
				Query query = arg0.createQuery(hql1);
				
				return query.executeUpdate();
			}});
		return (Integer) updateCount;
	}

}
