package com.dev.service.impl;

import com.dev.enums.Type;
import com.dev.mapper.CategoryMapper;
import com.dev.pojo.Category;
import com.dev.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> queryOneLevelCat() {
        Example example = new Example(Category.class);
        //不需要排序
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("type", Type.One);
        List<Category> result = categoryMapper.selectByExample(example);
        return result;
    }
}
