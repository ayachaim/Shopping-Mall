package com.dev.service.impl;

import com.dev.mapper.CarouselMapper;
import com.dev.pojo.Carousel;
import com.dev.service.CarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class CarouselServiceImpl implements CarouselService {

    @Autowired
    private CarouselMapper carouselMapper;


    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Carousel> queryAllCarousel(Integer isShow) {
        Example example = new Example(Carousel.class);

        example.orderBy("sort").desc();
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("isShow",isShow);

        List<Carousel> result = carouselMapper.selectByExample(example);

        return result;
    }
}
