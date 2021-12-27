package com.zsj.es.shop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zsj.es.common.PageParam;
import com.zsj.es.shop.model.PublicType;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 */
public interface PublicTypeMapper extends BaseMapper<PublicType> {

	List<PublicType> list(@Param("page") PageParam page);
	
	//这是api调用的
    List<PublicType> listFull(@Param("page") PageParam page);
    
    List<PublicType> listByType(@Param("page") PageParam page);
}
