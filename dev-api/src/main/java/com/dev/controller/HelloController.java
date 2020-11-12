package com.dev.controller;

import com.dev.service.StuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@RestController
public class HelloController {
    @Autowired
    private StuService stuService;

    @GetMapping("/getItemsImgInfo")
    public Object hello(int id) {
        return stuService.getItemsImgInfo(id);
    }
}
