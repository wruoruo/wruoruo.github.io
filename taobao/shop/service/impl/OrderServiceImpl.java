package com.zsj.es.shop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsj.es.common.PageParam;
import com.zsj.es.common.PageResult;
import com.zsj.es.shop.dao.OrderMapper;
import com.zsj.es.shop.model.Order;
import com.zsj.es.shop.service.OrderService;
import com.zsj.es.statistics.model.YearMonthModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * 订单表 服务实现类
 *
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public PageResult<Order> listOrder(PageParam pageParam) {

        List<Order> orderList = baseMapper.selectListFull(pageParam);

        return new PageResult<>(orderList, pageParam.getTotal());
    }

    @Override
    public Integer selectMonthConut() {

        return orderMapper.selectMonthConut();
    }

    @Override
    public BigDecimal selectMonthMoney() {

        return orderMapper.selectMonthMoney();
    }

    @Override
    public BigDecimal selectMoneyTotal() {

        return orderMapper.selectMoneyTotal();
    }

    @Override
    public List<YearMonthModel>  selectYearMonthMoneyTotal() {

        return orderMapper.selectYearMonthMoneyTotal();
    }
}
