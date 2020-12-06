package com.dev.controller;

import com.dev.Utils.JSONResult;
import com.dev.enums.YesOrNo;
import com.dev.pojo.Carousel;
import com.dev.service.CarouselService;
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

    @ApiOperation(value = "首页轮播图",notes = "首页轮播图",httpMethod = "GET")
    @GetMapping("/carousel")
    public JSONResult carousel() {
        List<Carousel> list =  carouselService.queryAllCarousel(YesOrNo.Yes.type);
        return JSONResult.ok(list);
    }




}
