package com.zsj.es.shop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zsj.es.common.PageParam;
import com.zsj.es.common.PageResult;
import com.zsj.es.shop.model.Order;
import com.zsj.es.statistics.model.YearMonthModel;

import java.math.BigDecimal;
import java.util.List;

/**
 * 订单表 服务类
 *
 */
public interface OrderService extends IService<Order> {

    PageResult<Order> listOrder(PageParam pageParam);

    /**
     * 查询月订单数
     */
    Integer selectMonthConut();

    /**
     * 查询月销售额
     */
    BigDecimal selectMonthMoney();

    /**
     * 查询总销售额
     */
    BigDecimal selectMoneyTotal();

    /**
     * 查询本年十二个月各个销售额总和
     */
    List<YearMonthModel>  selectYearMonthMoneyTotal();
}
