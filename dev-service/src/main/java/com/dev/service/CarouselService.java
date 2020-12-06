package com.dev.service;

import com.dev.pojo.Carousel;

import java.util.List;

public interface CarouselService {
    public List<Carousel> queryAllCarousel(Integer isShow);
}
