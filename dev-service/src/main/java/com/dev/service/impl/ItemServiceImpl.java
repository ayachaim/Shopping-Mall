package com.dev.service.impl;

import com.dev.Utils.DesensitizationUtil;
import com.dev.Utils.PagedGridResult;
import com.dev.enums.CommentLevel;
import com.dev.enums.YesOrNo;
import com.dev.mapper.*;
import com.dev.pojo.*;
import com.dev.pojo.vo.CommentCountsVO;
import com.dev.pojo.vo.ItemCommentVO;
import com.dev.pojo.vo.SearchItemsVO;
import com.dev.pojo.vo.ShopCartVO;
import com.dev.service.ItemService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemsMapper itemsMapper;

    @Autowired
    private ItemsImgMapper itemsImgMapper;

    @Autowired
    private ItemsSpecMapper itemsSpecMapper;

    @Autowired
    private ItemsParamMapper itemsParamMapper;

    @Autowired
    private ItemsCommentsMapper itemsCommentsMapper;

    @Autowired
    private ItemsMapperCustom itemsMapperCustom;


    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Items queryItemById(String id) {
        return itemsMapper.selectByPrimaryKey(id);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<ItemsImg> quertItemImgList(String itemId) {
        Example example = new Example(ItemsImg.class);
        Example.Criteria criteria = example.createCriteria();
        //根据外键itemId查询
        criteria.andEqualTo("itemId", itemId);
        return itemsImgMapper.selectByExample(example);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<ItemsSpec> queryItemSpecList(String itemId) {
        Example example = new Example(ItemsSpec.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("itemId", itemId);
        return itemsSpecMapper.selectByExample(example);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public ItemsParam queryItemParam(String itemId) {
        Example example = new Example(ItemsParam.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("itemId", itemId);
        //只返回一条商品记录
        return itemsParamMapper.selectOneByExample(example);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public CommentCountsVO queryCommentCounts(String itemId) {
        //好评
        Integer goodCounts = getCommentCounts(itemId, CommentLevel.Good.type);
        //好评
        Integer normalCounts = getCommentCounts(itemId, CommentLevel.Normal.type);
        //好评
        Integer badCounts = getCommentCounts(itemId, CommentLevel.Bad.type);
        //总评价数量
        Integer totalCounts = goodCounts + normalCounts + badCounts ;

        //数据放入countsVO中
        CommentCountsVO countsVO = new CommentCountsVO();
        countsVO.setGoodCounts(goodCounts);
        countsVO.setNormalCounts(normalCounts);
        countsVO.setBadCounts(badCounts);
        countsVO.setTotalCounts(totalCounts);
        return countsVO;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    Integer getCommentCounts(String itemId,Integer level){
        ItemsComments condition = new ItemsComments();
        condition.setItemId(itemId);
        //判断level不为空
        if(level != null){
            condition.setCommentLevel(level);
        }
        return itemsCommentsMapper.selectCount(condition);
    }

    /**
     * 模糊搜索
     * @param keyWords
     * @param sort
     * @param page
     * @param pageSize
     * @return
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PagedGridResult searchItemSpec(String keyWords, String sort, Integer page, Integer pageSize) {
        Map<String,Object> map = new HashMap<>();
        map.put("keyWords",keyWords);
        map.put("sort",sort);
        //分页插件
        PageHelper.startPage(page, pageSize);
        List<SearchItemsVO> list = itemsMapperCustom.searchItemsSpec(map);
        return setterGridResult(list,page);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PagedGridResult queryItemComments(String itemId,Integer level,Integer page,Integer pageSize) {
        Map<String,Object> map = new HashMap<>();
        map.put("itemId",itemId);
        map.put("level",level);
        //分页插件
        PageHelper.startPage(page, pageSize);
        List<ItemCommentVO> list = itemsMapperCustom.queryItemComments(map);
        //脱敏
        for(ItemCommentVO item: list){
            item.setNickName(DesensitizationUtil.commonDisplay(item.getNickName()));
        }
        return setterGridResult(list,page);
    }

    /**
     * 根据三级标签点击搜索商品列表
     * @param catId
     * @param sort
     * @param page
     * @param pageSize
     * @return
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PagedGridResult searchItemSpec(Integer catId, String sort, Integer page, Integer pageSize) {
        Map<String,Object> map = new HashMap<>();
        map.put("catId",catId);
        map.put("sort",sort);
        //分页插件
        PageHelper.startPage(page, pageSize);
        List<SearchItemsVO> list = itemsMapperCustom.searchItemsByThirdCat(map);
        return setterGridResult(list,page);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<ShopCartVO> queryItemsBySpecIds(String specIds) {
        String ids[] = specIds.split(",");
        List<String> specList = new ArrayList<>();
        Collections.addAll(specList,ids);
        List<ShopCartVO> list = itemsMapperCustom.queryItemsBySpecIds(specList);
        return list;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public ItemsSpec queryItemSpecById(String id) {
        return itemsSpecMapper.selectByPrimaryKey(id);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public String queryItemMainImg(String id) {
        ItemsImg itemImg = new ItemsImg();
        itemImg.setItemId(id);
        itemImg.setIsMain(YesOrNo.Yes.type);
        ItemsImg result = itemsImgMapper.selectOne(itemImg);
        return result != null ? result.getUrl() : "";
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void descreaseItemStock(String specId, int buyCount) {
        //TODO lock
        //result是update matched的条数
        int result = itemsMapperCustom.descreaseItemStock(specId,buyCount);
        if(result != 1){
            throw new RuntimeException("创建失败,库存不足！");
        }
    }

    /**
     * 通用的分页插件配置
     * @param list
     * @param page
     * @return
     */
    private PagedGridResult setterGridResult(List<?> list,Integer page){
        PageInfo<?> pageList = new PageInfo<>(list);
        PagedGridResult grid = new PagedGridResult();
        grid.setPage(page);
        grid.setRows(list);
        grid.setTotal(pageList.getPages());
        grid.setRecords(pageList.getTotal());
        return grid;
    }
}
