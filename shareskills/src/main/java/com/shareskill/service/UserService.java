package com.shareskill.service;

import java.util.List;
import com.shareskill.model.TUser;

/** 注册会员管理业务逻辑接口 */
public interface UserService {
	/** 注册会员登录 */
	public TUser memberLogin(String loginName,String loginPwd);

	public TUser loadMemberByLoginName(String loginName);
	/** 浏览注册会员 */
	public List<TUser> browseMember();	
	/** 装载指定的注册会员 */
	public TUser loadMember(Integer id);	
	/** 删除指定的注册会员 */
	public boolean delMember(Integer id);	
	/** 新增或修改注册会员 */
	public boolean saveOrUpdateMember(TUser member);
	/** 检查指定登录账号是否可用 */
	public boolean isEnable(String loginname);	
}
