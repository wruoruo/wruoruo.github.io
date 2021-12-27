package com.zsj.es.shop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsj.es.common.PageParam;
import com.zsj.es.common.PageResult;
import com.zsj.es.shop.dao.ApplyMerchantMapper;
import com.zsj.es.shop.model.ApplyMerchant;
import com.zsj.es.shop.service.ApplyMerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * 服务实现类
 *
 */
@Service
public class ApplyMerchantServiceImpl extends ServiceImpl<ApplyMerchantMapper, ApplyMerchant> implements ApplyMerchantService {

    @Autowired
    private ApplyMerchantMapper ApplyMerchantMapper;

    @Override
    public PageResult<ApplyMerchant> listFull(PageParam pageParam) {

        List<ApplyMerchant> list = ApplyMerchantMapper.listFull(pageParam);

        return new PageResult<>(list, pageParam.getTotal());
    }
}
