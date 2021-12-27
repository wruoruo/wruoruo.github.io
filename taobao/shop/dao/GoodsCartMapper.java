package com.zsj.es.shop.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zsj.es.shop.model.GoodsCart;

import java.util.List;

/**
 * <p>
 * 购物车表 Mapper 接口
 * </p>
 *
 */
public interface GoodsCartMapper extends BaseMapper<GoodsCart> {

    List<GoodsCart> listByUser(Integer userId);

    Integer countByUser(Integer userId);
}
