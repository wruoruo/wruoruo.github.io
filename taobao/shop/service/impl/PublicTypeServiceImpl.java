package com.zsj.es.shop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsj.es.common.PageParam;
import com.zsj.es.common.PageResult;
import com.zsj.es.shop.dao.PublicTypeMapper;
import com.zsj.es.shop.model.PublicType;
import com.zsj.es.shop.service.PublicTypeService;

/**
 * 服务实现类
 *
 */
@Service
public class PublicTypeServiceImpl extends ServiceImpl<PublicTypeMapper, PublicType> implements PublicTypeService {

    @Autowired
    private PublicTypeMapper goodsMapper;
  
    @Override
    public PageResult<PublicType> listByType(PageParam page) {

        List<PublicType> list = baseMapper.listByType(page);

        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public PageResult<PublicType> listFull(PageParam page) {

        List<PublicType> list = baseMapper.listFull(page);

        return new PageResult<>(list, page.getTotal());
    }

	@Override
	public PageResult<PublicType> list(PageParam pageParam) {
	
		List<PublicType> goodsList=baseMapper.list(pageParam);
		return new PageResult<>(goodsList, pageParam.getTotal());
	}
}
