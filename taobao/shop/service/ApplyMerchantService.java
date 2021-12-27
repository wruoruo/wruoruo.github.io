package com.zsj.es.shop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zsj.es.common.PageParam;
import com.zsj.es.common.PageResult;
import com.zsj.es.shop.model.ApplyMerchant;

/**
 * 表 服务类
 *
 */
public interface ApplyMerchantService extends IService<ApplyMerchant> {

    PageResult<ApplyMerchant> listFull(PageParam pageParam);
}
