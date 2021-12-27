package com.zsj.es.shop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsj.es.common.PageParam;
import com.zsj.es.common.PageResult;
import com.zsj.es.shop.dao.AppraiseMapper;
import com.zsj.es.shop.model.Appraise;
import com.zsj.es.shop.service.AppraiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 评价表 服务实现类
 *
 */
@Service
public class AppraiseServiceImpl extends ServiceImpl<AppraiseMapper, Appraise> implements AppraiseService {

    @Autowired
    private AppraiseMapper appraiseMapper;

    @Override
    public PageResult<Appraise> listFull(PageParam pageParam) {

        List<Appraise> list = baseMapper.listFull(pageParam);

        return new PageResult<>(list, pageParam.getTotal());
    }
}
