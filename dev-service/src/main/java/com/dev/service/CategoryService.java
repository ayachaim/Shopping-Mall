package com.dev.service;

import com.dev.pojo.Category;
import com.dev.pojo.vo.CategoryVO;

import java.util.List;

public interface CategoryService {
    //查询一级分类
    public List<Category> queryOneLevelCat();
    //查询二级和三级分类
    public List<CategoryVO> querySubCatList(Integer rootCatId);
}
