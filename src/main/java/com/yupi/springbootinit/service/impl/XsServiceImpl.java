package com.yupi.springbootinit.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yupi.springbootinit.model.entity.Xs;
import com.yupi.springbootinit.service.XsService;
import com.yupi.springbootinit.mapper.XsMapper;
import org.springframework.stereotype.Service;

@Service
public class XsServiceImpl extends ServiceImpl<XsMapper, Xs>
    implements XsService{

}




