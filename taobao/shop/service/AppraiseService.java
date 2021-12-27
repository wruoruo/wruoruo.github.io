package com.zsj.es.shop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zsj.es.common.PageParam;
import com.zsj.es.common.PageResult;
import com.zsj.es.shop.model.Appraise;

/**
 * 评价表 服务类
 *
 */
public interface AppraiseService extends IService<Appraise> {

    PageResult<Appraise> listFull(PageParam pageParam);
}
