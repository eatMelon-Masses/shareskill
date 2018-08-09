package com.shareskill.service;
import java.util.List;

import com.shareskill.model.DataCategory;
import com.shareskill.model.TDataresource;

public interface DataResourceService{
	
	/**浏览数据资源
	 * orfer=0时间顺序 1点击顺序
	 * */
	public List<TDataresource> browseDataRes(int order);
	/**加载指定数据资源*/
	public TDataresource loadDataResById(int id);
	/**加载指定分类的所有数据资源*/
	public List<TDataresource> loadDataResByDataCategory(DataCategory datacategory);

	/**分页加载指定分类的所有数据*/
	public List<TDataresource> loadDataResByDataCategory(DataCategory datacategory,String keyWord,int pageNo,int pageSize);
	/**删除指定数据资源*/
	public Boolean delDataResById(Integer id);
	/**新增或修改指定数据资源*/
	public Boolean saveOrUpdateDataRes(TDataresource data);

	/**
	 * 统计指定分类的数据条数
	 */
	public int loadDataResByDataCategoryCount(int  cateId, String keyWord);

	/**统计当前登录用户的资源
	 * @param userId
	 * @return
	 */
	public int browseDataResourceByUserCount(int userId);

	/** 当前已登录用户的所有资源
	 * @param userId
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<TDataresource> browseDataResourceByUser(int userId, int pageNo, int pageSize);
	public List<TDataresource> browseAllDataResource();

}