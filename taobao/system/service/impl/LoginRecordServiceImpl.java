package com.zsj.es.system.service.impl;

import com.zsj.es.common.PageParam;
import com.zsj.es.common.PageResult;
import com.zsj.es.system.dao.LoginRecordMapper;
import com.zsj.es.system.model.LoginRecord;
import com.zsj.es.system.service.LoginRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 登录日志表 服务实现类
 *
 */
@Service
public class LoginRecordServiceImpl extends ServiceImpl<LoginRecordMapper, LoginRecord> implements LoginRecordService {

    @Override
    public PageResult<LoginRecord> listFull(PageParam pageParam) {
        List<LoginRecord> records = baseMapper.listFull(pageParam);
        return new PageResult<>(records, pageParam.getTotal());
    }

    @Override
    public List<LoginRecord> listAll(Map pageData) {
        return baseMapper.listAll(pageData);
    }
}
