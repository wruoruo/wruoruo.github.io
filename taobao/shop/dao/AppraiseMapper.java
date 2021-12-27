package com.zsj.es.shop.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zsj.es.common.PageParam;
import com.zsj.es.shop.model.Appraise;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 评价表 Mapper 接口
 * </p>
 *
 */
public interface AppraiseMapper extends BaseMapper<Appraise> {

    List<Appraise> listFull(@Param("page") PageParam page);
}
