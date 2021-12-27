package com.zsj.es.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zsj.es.system.model.Visitor;

/**
 * <p>
 * 访问表 Mapper 接口
 * </p>
 *
 */
public interface VisitorMapper extends BaseMapper<Visitor> {

    /**
     * 获取当日访问量
     */
    Integer selectNowVisitor();
}
