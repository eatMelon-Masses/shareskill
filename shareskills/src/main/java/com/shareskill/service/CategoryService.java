package com.shareskill.service;
import com.shareskill.model.TBlogcategorytag;
import java.util.List;


/** 商品分类管理业务逻辑接口 */
public interface CategoryService {
	/** 浏览商品分类 */
	public List<TBlogcategorytag> browseCategory();
	/** 装载指定的商品分类 */	
	public TBlogcategorytag loadCategory(int id);	
	/** 删除指定的商品分类 */
	public boolean delCategory(Integer id);	
	/** 新增或修改商品分类 */
	public boolean saveOrUpdateCategory(TBlogcategorytag category);
}
