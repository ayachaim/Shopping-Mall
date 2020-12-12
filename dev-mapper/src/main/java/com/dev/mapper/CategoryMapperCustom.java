package com.dev.mapper;

import com.dev.pojo.vo.CategoryVO;
import com.dev.pojo.vo.LazyItemsVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CategoryMapperCustom {
    /**
     * 查询index一级分类下的二级列表
     * @param rootCatId
     * @return
     */
    public List<CategoryVO> getSubCatList(Integer rootCatId);

    /**
     * 查询懒加载一级下的六条商品数据
     * @param map
     * @return
     */
    public List<LazyItemsVO> queryLazyItems(@Param("paramsMap") Map<String,Object> map);
}