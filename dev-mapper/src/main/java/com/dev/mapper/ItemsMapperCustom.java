package com.dev.mapper;

import com.dev.pojo.vo.ItemCommentVO;
import com.dev.pojo.vo.SearchItemsVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ItemsMapperCustom  {
    public List<ItemCommentVO> queryItemComments(@Param("paramsMap") Map<String,Object> map);
    public List<SearchItemsVO> searchItemsSpec(@Param("paramsMap") Map<String,Object> map);
}