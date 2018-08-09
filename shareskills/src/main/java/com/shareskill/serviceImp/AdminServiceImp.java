package com.shareskill.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shareskill.dao.BaseDAO;
import com.shareskill.dao.BaseDaoImp;
import com.shareskill.model.TAdmin;
import com.shareskill.service.AdminService;

public class AdminServiceImp implements AdminService {
	
	@Autowired
	private BaseDAO dao;
	@Override
	public TAdmin adminLogin(String loginName, String loginPwd) {
		// TODO Auto-generated method stub
		String hql = "from TAdmin as a where a.zh='"+loginName+"' and a.mm='"+loginPwd+"'";
		return (TAdmin) dao.loadObject(hql);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TAdmin> browseAdmin() {
		// TODO Auto-generated method stub
		return dao.listAll("TAdmin");
	}

	@Override
	public TAdmin loadAdmin(Integer id) {
		// TODO Auto-generated method stub
		TAdmin temp=null;
		try{
			temp=(TAdmin)dao.loadById(TAdmin.class, id);	
		}catch(Exception e){
			e.printStackTrace();
		}
		return temp;
	}

	@Override
	public boolean delAdmin(Integer id) {
		// TODO Auto-generated method stub
		boolean status = false;
		try{
			dao.delById(TAdmin.class, id);
			status = true;
		}catch(Exception ex){
			ex.printStackTrace();
		}	
		return status;
	}

	@Override
	public boolean saveOrUpdateAdmin(TAdmin admin) {
		// TODO Auto-generated method stub
		boolean status = false;
		try{
			dao.saveOrUpdate(admin);
			status = true;
		}catch(Exception ex){
			ex.printStackTrace();
		}	
		return status;
	}

	public BaseDAO getDao() {
		return dao;
	}

	public void setDao(BaseDAO dao) {
		this.dao = dao;
	}

	

}
