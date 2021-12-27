package com.zsj.es.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsj.es.system.dao.VisitorMapper;
import com.zsj.es.system.model.Visitor;
import com.zsj.es.system.service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 访问表 服务实现类
 *
 */
@Service
public class VisitorServiceImpl extends ServiceImpl<VisitorMapper, Visitor> implements VisitorService {

    @Autowired
    private VisitorMapper visitorMapper;

    @Override
    public Integer selectNowVisitor() {

        return visitorMapper.selectNowVisitor();
    }
}
