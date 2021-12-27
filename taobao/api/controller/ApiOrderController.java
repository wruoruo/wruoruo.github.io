package com.zsj.es.api.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zsj.es.common.JsonResult;
import com.zsj.es.common.PageParam;
import com.zsj.es.common.PageResult;
import com.zsj.es.common.exception.BusinessException;
import com.zsj.es.common.utils.MailUtil;
import com.zsj.es.common.utils.SnowflakeIdWorker;
import com.zsj.es.common.utils.StringUtil;
import com.zsj.es.shop.model.Goods;
import com.zsj.es.shop.model.GoodsCart;
import com.zsj.es.shop.model.Order;
import com.zsj.es.shop.service.GoodsCartService;
import com.zsj.es.shop.service.GoodsService;
import com.zsj.es.shop.service.OrderService;
import com.zsj.es.system.model.User;
import com.zsj.es.system.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

/**
 * api订单相关接口
 */
@RequestMapping("/api/order")
@RestController
@Slf4j
public class ApiOrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private GoodsCartService goodsCartService;
    
    @Autowired
    private GoodsService goodsService;

    @Autowired
    private UserService userService;

    @Autowired
    private MailUtil mailUtil;

    /**
     * 根据用户查询订单信息
     **/
    @GetMapping("/listByUser")
    public PageResult<Order> listByUser(HttpServletRequest request){

        return orderService.listOrder(new PageParam(request).setDefaultOrder(null, new String[]{"create_time"}));
    }

    /**
     * 根据用户查询订单数量
     **/
    @GetMapping("/countByUser")
    public JsonResult countByUser(Integer userId){
        Integer count = orderService.count(new QueryWrapper<Order>().eq("user_id",userId));
        return JsonResult.ok("查询成功").put("data",count);
    }

    /**
     * 下单
     **/
    @PostMapping("/addOrder")
    public JsonResult addOrder(Order order){
        SnowflakeIdWorker sfw = new SnowflakeIdWorker(0,0);
        order.setOrderNo("E"+sfw.nextId());
        if(orderService.save(order)){
            return JsonResult.ok("下单成功");
        }
        return JsonResult.error("下单失败");
    }

    /**
     * 批量下单
     **/
    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/addAll")
    public JsonResult addOrderAll(String arrStr){
        List<Order> list=JSON.parseArray(arrStr,Order.class);
        SnowflakeIdWorker sfw = new SnowflakeIdWorker(0,0);
        for(Order o:list){
            o.setOrderNo("E"+sfw.nextId());
            Goods goods = goodsService.getByNn(o.getGoodsSn());
            o.setPublishId(goods.getPublishId());
            o.setPublishPhone(userService.getById(goods.getPublishId()).getPhone());
            if(!orderService.save(o)){
                throw new BusinessException("下单失败");
            }
            //下单成功，清除购物车
           if(!goodsCartService.remove(new QueryWrapper<GoodsCart>()
                    .eq("user_id",o.getUserId())
                    .eq("goods_sn",o.getGoodsSn())
                    .eq("made_logo",o.getMadeLogo())
                    .eq("made_text",o.getMadeText())
                    .eq("goods_color",o.getGoodsColor())
           )
           ){
               throw new BusinessException("订单异常，请重试");
           }
        }

        return JsonResult.ok("下单成功");
    }

    /**
     * 修改订单信息
     **/
    @PostMapping("/updateOrder")
    public JsonResult updateOrder(Order order){
        if(orderService.updateById(order)){
            return JsonResult.ok("操作成功");
        }
        return JsonResult.error("操作失败");
    }

    /**
     * 确认收货
     **/
    @PostMapping("/receive")
    public JsonResult receive(Order order){
        //设置收货时间
        order.setReceiveTime(new Date());
        if(orderService.updateById(order)){
            return JsonResult.ok("操作成功");
        }
        return JsonResult.error("操作失败");
    }
}
