package com.dev.service;

import com.dev.Utils.PagedGridResult;
import com.dev.pojo.Items;
import com.dev.pojo.ItemsImg;
import com.dev.pojo.ItemsParam;
import com.dev.pojo.ItemsSpec;
import com.dev.pojo.vo.CommentCountsVO;
import com.dev.pojo.vo.ShopCartVO;

import java.util.List;

public interface ItemService {
    /**
     *  根据id查询商品详情
     * @param id
     * @return
     */
    public Items queryItemById(String id);

    /**
     * 根据id查询商品图片
     * @param id
     * @return
     */
    public List<ItemsImg> quertItemImgList(String id);

    /**
     * 根据商品id查询商品详情
     * @param id
     * @return
     */
    public List<ItemsSpec> queryItemSpecList(String id);

    /**
     * 根据商品id查询商品参数
     * @param id
     * @return
     */
    public ItemsParam queryItemParam(String id);

    /**
     * 根据商品id查询商品评价数量(好评，中评，差评)
     * @param itemId
     */
    public CommentCountsVO queryCommentCounts(String itemId);

    /**
     * 根据商品id和level分类查询商品具体评价
     * @param itemId
     */
    public PagedGridResult queryItemComments(String itemId, Integer level, Integer page, Integer pageSize);

    /**
     * 模糊搜索
     * @param keyWords
     */
    public PagedGridResult searchItemSpec(String keyWords,String sort, Integer page, Integer pageSize);

    /**
     * 根据三级标签点击搜索商品列表
     * @param catId
     */
    public PagedGridResult searchItemSpec(Integer catId,String sort, Integer page, Integer pageSize);

    /**
     * 购物车
     * @param specIds
     * @return  List
     */
    public List<ShopCartVO> queryItemsBySpecIds(String specIds);

    /**
     * 商品规格表，根据主键id查询商品的
     * @param specId
     * @return
     */
    public ItemsSpec queryItemSpecById(String specId);

    /**
     * 根据id查询商品主图
     * @param id
     * @return
     */
    public String queryItemMainImg(String id);

    /**
     * 商品规格表，下单后删除对应库存
     * @param specId
     * @param buyCount
     */
    public int descreaseItemStock(String specId,int buyCount);
}
