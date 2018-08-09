package com.shareskill.service;

import com.shareskill.model.DataCategory;

import java.util.List;

public interface DataCategoryService {
    /**浏览所有数据分类资源*/
    public List<DataCategory> browseAllDataCategory();

    /**通过id查数据分类*/
    public DataCategory loadDataCategoryById(Integer id);
}
