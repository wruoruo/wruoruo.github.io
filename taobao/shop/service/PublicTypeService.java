package com.zsj.es.shop.service;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zsj.es.common.PageParam;
import com.zsj.es.common.PageResult;
import com.zsj.es.shop.model.PublicType;

/**
 *  服务类
 *
 */
public interface PublicTypeService extends IService<PublicType> {

	PageResult<PublicType> list(@Param("page") PageParam page);
	
    PageResult<PublicType> listByType(@Param("page") PageParam page);

    PageResult<PublicType> listFull(@Param("page") PageParam page);
}
