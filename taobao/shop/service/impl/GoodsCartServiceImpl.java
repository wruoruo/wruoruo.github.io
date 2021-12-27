package com.zsj.es.shop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsj.es.shop.dao.GoodsCartMapper;
import com.zsj.es.shop.model.GoodsCart;

import com.zsj.es.shop.service.GoodsCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 购物车表 服务实现类
 *
 */
@Service
public class GoodsCartServiceImpl extends ServiceImpl<GoodsCartMapper, GoodsCart> implements GoodsCartService {

    @Autowired
    private GoodsCartMapper goodsCartMapper;

    @Override
    public List<GoodsCart> listByUser(Integer userId) {

        return goodsCartMapper.listByUser(userId);
    }

    @Override
    public Integer countByUser(Integer userId) {

        return goodsCartMapper.countByUser(userId);
    }
}
