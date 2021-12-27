package com.zsj.es.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zsj.es.system.model.Visitor;

/**
 * 访问表 服务类
 *
 */
public interface VisitorService extends IService<Visitor> {

    /**
     * 获取当日访问量
     */
    Integer selectNowVisitor();
}
