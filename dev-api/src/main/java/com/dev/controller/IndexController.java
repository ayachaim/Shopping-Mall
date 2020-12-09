package com.dev.controller;

import com.dev.Utils.JSONResult;
import com.dev.enums.YesOrNo;
import com.dev.pojo.Carousel;
import com.dev.pojo.Category;
import com.dev.service.CarouselService;
import com.dev.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "首页",tags = {"首页展示的内容"})
@RestController
@RequestMapping("index")
public class IndexController {

    @Autowired
    private CarouselService carouselService;
    @Autowired
    private CategoryService categoryService;

    @ApiOperation(value = "首页轮播图",notes = "首页轮播图",httpMethod = "GET")
    @GetMapping("/carousel")
    public JSONResult carousel() {
        List<Carousel> list =  carouselService.queryAllCarousel(YesOrNo.Yes.type);
        return JSONResult.ok(list);
    }

    @ApiOperation(value = "获取商品分类(一级分类)",notes = "获取商品分类(一级分类)",httpMethod = "GET")
    @GetMapping("/category")
    public JSONResult Category() {
        List<Category> list =  categoryService.queryOneLevelCat();
        return JSONResult.ok(list);
    }




}
