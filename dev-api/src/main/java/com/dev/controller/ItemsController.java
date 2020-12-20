package com.dev.controller;

import com.dev.Utils.JSONResult;
import com.dev.Utils.PagedGridResult;
import com.dev.pojo.Items;
import com.dev.pojo.ItemsImg;
import com.dev.pojo.ItemsParam;
import com.dev.pojo.ItemsSpec;
import com.dev.pojo.vo.CommentCountsVO;
import com.dev.pojo.vo.ItemInfoVO;
import com.dev.service.ItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "商品详情页面",tags = {"商品详情页面"})
@RestController
@RequestMapping("items")
public class ItemsController extends BaseController {

    @Autowired
    private ItemService itemService;


    private static final Logger logger = LoggerFactory.getLogger(ItemsController.class);

    @ApiOperation(value = "查询商品详情",notes = "查询商品详情",httpMethod = "GET")
    @GetMapping("/detail")
    public JSONResult detail(
            @ApiParam(name = "itemId",value = "商品id",required = true)
            @RequestParam String itemId) {
        //判断非空
        if(StringUtils.isBlank(itemId)){
            return JSONResult.errorMsg("商品序号不能为空");
        }

        Items items = itemService.queryItemById(itemId);
        List<ItemsImg> itemsImgs = itemService.quertItemImgList(itemId);
        List<ItemsSpec> itemsSpecs = itemService.queryItemSpecList(itemId);
        ItemsParam itemParam = itemService.queryItemParam(itemId);
        //设置VO，data返回对象
        ItemInfoVO itemInfoVO = new ItemInfoVO();
        itemInfoVO.setItems(items);
        itemInfoVO.setItemsImgs(itemsImgs);
        itemInfoVO.setItemsSpecs(itemsSpecs);
        itemInfoVO.setItemParam(itemParam);
        return JSONResult.ok(itemInfoVO);
    }

    @ApiOperation(value = "查询商品评价数量",notes = "查询商品评价数量",httpMethod = "GET")
    @GetMapping("/comment")
    public JSONResult comment(
            @ApiParam(name = "itemId",value = "商品id",required = true)
            @RequestParam String itemId) {
        //判断非空
        if(StringUtils.isBlank(itemId)){
            return JSONResult.errorMsg("商品序号不能为空");
        }
        CommentCountsVO commentResult = itemService.queryCommentCounts(itemId);
        return JSONResult.ok(commentResult);
    }

    @ApiOperation(value = "查询商品具体评价",notes = "查询商品具体评价",httpMethod = "GET")
    @GetMapping("/commentDetail")
    public JSONResult commentDetail (
            @ApiParam(name = "itemId",value = "商品id",required = true)
            @RequestParam String itemId,
            @ApiParam(name = "level",value = "分类",required = true)
            @RequestParam Integer level,
            @ApiParam(name = "page",value = "页数",required = true)
            @RequestParam Integer page,
            @ApiParam(name = "pageSize",value = "每页条数",required = true)
            @RequestParam Integer pageSize
    ) {
        //判断非空
        if(StringUtils.isBlank(itemId) || level == null){
            return JSONResult.errorMsg("商品序号和评论等级不能为空");
        }
        //默认的limit和offset
        if(page == null){
            page = OFFSET;
        }
        if(pageSize == null){
            pageSize = LIMIT;
        }
        PagedGridResult commentsResult = itemService.queryItemComments(itemId,level,page,pageSize);
        return JSONResult.ok(commentsResult);
    }

    /**
     * 模糊搜索
     * @param keyWords
     * @param sort
     * @param page
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "模糊搜索",notes = "模糊搜索",httpMethod = "GET")
    @GetMapping("/searchItems")
    public JSONResult searchItems (
            @ApiParam(name = "keyWords",value = "输入值",required = true)
            @RequestParam String keyWords,
            @ApiParam(name = "sort",value = "排序",required = true)
            @RequestParam String sort,
            @ApiParam(name = "page",value = "页数",required = true)
            @RequestParam Integer page,
            @ApiParam(name = "pageSize",value = "每页条数",required = true)
            @RequestParam Integer pageSize
    ) {
//        //判断非空
//        if(StringUtils.isBlank(keyWords)){
//            return JSONResult.errorMsg("搜索值不能为空");
//        }
        //默认的limit和offset
        if(page == null){
            page = OFFSET;
        }
        if(pageSize == null){
            pageSize = PAGE_SIZE;
        }
        PagedGridResult Result = itemService.searchItemSpec(keyWords,sort,page,pageSize);
        return JSONResult.ok(Result);
    }


}
