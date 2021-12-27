package com.zsj.es.shop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zsj.es.shop.model.GoodsCart;

import java.util.List;

/**
 * 购物车表 服务类
 *
 */
public interface GoodsCartService extends IService<GoodsCart> {

    List<GoodsCart> listByUser(Integer userId);

    Integer countByUser(Integer userId);
}
