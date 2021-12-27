package com.zsj.es.shop.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zsj.es.common.PageParam;
import com.zsj.es.shop.model.Goods;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 商品表 Mapper 接口
 * </p>
 *
 */
public interface GoodsMapper extends BaseMapper<Goods> {

	List<Goods> list(@Param("page") PageParam page);
	
	//这是api调用的
    List<Goods> listFull(@Param("page") PageParam page);

    List<Goods> listByType(@Param("page") PageParam page);
    
    Goods getByNn(@Param("goodsSn") String goodsSn);
    
}
