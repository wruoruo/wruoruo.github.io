package com.zsj.es.system.service;

import com.zsj.es.common.PageParam;
import com.zsj.es.common.PageResult;
import com.zsj.es.system.model.LoginRecord;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * 登录日志表 服务类
 *
 */
public interface LoginRecordService extends IService<LoginRecord> {

    // 分页查询
    PageResult<LoginRecord> listFull(PageParam page);

    // 不分页
    List<LoginRecord> listAll(Map pageData);

}
