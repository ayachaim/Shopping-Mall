package com.dev.service.impl;

import com.dev.mapper.ItemsImgMapper;
import com.dev.pojo.ItemsImg;
import com.dev.service.StuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
public class StuServiceImpl implements StuService {

    @Autowired
    private ItemsImgMapper itemsImgMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public ItemsImg getItemsImgInfo(int id) {
        return itemsImgMapper.selectByPrimaryKey(id);
    }

}
