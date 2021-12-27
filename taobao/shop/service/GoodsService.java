package com.zsj.es.shop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zsj.es.common.PageParam;
import com.zsj.es.common.PageResult;
import com.zsj.es.shop.model.Goods;
import org.apache.ibatis.annotations.Param;

/**
 * 商品表 服务类
 *
 */
public interface GoodsService extends IService<Goods> {

	PageResult<Goods> list(@Param("page") PageParam page);
	
    PageResult<Goods> listByType(@Param("page") PageParam page);

    PageResult<Goods> listFull(@Param("page") PageParam page);
    
    Goods getByNn(@Param("goodsSn") String goodsSn);
}
