
package com.shareskill.serviceImp;

import java.util.List;

import javax.annotation.Resource;

import com.shareskill.dao.BaseDAO;
import com.shareskill.model.TUser;
import com.shareskill.service.UserService;

public class UserServiceImp implements UserService {
	@Resource
	private BaseDAO dao;
/*	private final String REGISTERVIEW="views/UserEditForm";
	private final String LOGINUSERVIEW="";
	private final String LOGOUTUSERVIEW="";
	private final String SAVEUSERVIEW="index";
	private	final String HOMEVIEW="";*/
	@Override
	public TUser memberLogin(String loginName, String loginPwd) {
		// TODO Auto-generated method stub
		//String hql ="from TUser as a where a.zh='"+loginName+"' and a.Dlmm='"+loginPwd+"'";
		String hql ="from TUser as a where a.zh='"+loginName+"' and a.dlmm='"+loginPwd+"'";
		return (TUser)dao.loadObject(hql);
	}

    @Override
    public TUser loadMemberByLoginName(String loginName) {
		String hql ="from TUser as a where a.zh='"+loginName+"'";
		return (TUser)dao.loadObject(hql);

    }

    @Override
	public List<TUser> browseMember() {
		// TODO Auto-generated method stub
		
		return dao.listAll("TUser");
	}

	@Override
	public TUser loadMember(Integer id) {
		// TODO Auto-generated method stub
		TUser temp=null;
		try{
			temp=(TUser)dao.loadById(TUser.class, id);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return temp;
	}

	@Override
	public boolean delMember(Integer id) {
		// TODO Auto-generated method stub
		boolean status = false;
		try {
			dao.delById(TUser.class, id);
			status=true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			status=false;
		}
		return status;
	}

	@Override
	public boolean saveOrUpdateMember(TUser member) {
		// TODO Auto-generated method stub
		boolean status=false;
		try {
			dao.saveOrUpdate(member);
			status=true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			status=false;
		}
		return status;
	}

	@Override
	public boolean isEnable(String loginname) {
		// TODO Auto-generated method stub
		return false;
	}

	public BaseDAO getDao() {
		return dao;
	}
	@Resource
	public void setDao(BaseDAO dao) {
		this.dao = dao;
	}

}
