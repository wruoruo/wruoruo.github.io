package com.zsj.es.shop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsj.es.common.PageParam;
import com.zsj.es.common.PageResult;
import com.zsj.es.shop.dao.GoodsMapper;
import com.zsj.es.shop.model.Goods;
import com.zsj.es.shop.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品表 服务实现类
 *
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public PageResult<Goods> listByType(PageParam page) {

        List<Goods> list = baseMapper.listByType(page);

        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public PageResult<Goods> listFull(PageParam page) {

        List<Goods> list = baseMapper.listFull(page);

        return new PageResult<>(list, page.getTotal());
    }

	/* (non-Javadoc)
	 * @see com.zsj.es.shop.service.GoodsService#list(com.zsj.es.common.PageParam)
	 */
	@Override
	public PageResult<Goods> list(PageParam pageParam) {
	
		List<Goods> goodsList=baseMapper.list(pageParam);
		return new PageResult<>(goodsList, pageParam.getTotal());
	}

	@Override
	public Goods getByNn(String goodsSn) {
		return goodsMapper.getByNn(goodsSn);
	}
}
