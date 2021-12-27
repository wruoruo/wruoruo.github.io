package com.zsj.es.shop.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zsj.es.common.PageParam;
import com.zsj.es.shop.model.ApplyMerchant;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 表 Mapper 接口
 * </p>
 *
 */
public interface ApplyMerchantMapper extends BaseMapper<ApplyMerchant> {

    List<ApplyMerchant> listFull(@Param("page") PageParam page);
}
