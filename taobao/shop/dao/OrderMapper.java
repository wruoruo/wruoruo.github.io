package com.zsj.es.shop.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zsj.es.common.PageParam;
import com.zsj.es.shop.model.Order;
import com.zsj.es.statistics.model.YearMonthModel;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 订单表 Mapper 接口
 * </p>
 *
 */
public interface OrderMapper extends BaseMapper<Order> {

    List<Order> selectListFull(@Param("page") PageParam page);

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
    List<YearMonthModel> selectYearMonthMoneyTotal();
}
