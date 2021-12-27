package com.zsj.es.shop.controller;

import com.zsj.es.common.BaseController;
import com.zsj.es.common.JsonResult;
import com.zsj.es.common.PageParam;
import com.zsj.es.common.PageResult;
import com.zsj.es.common.utils.SnowflakeIdWorker;
import com.zsj.es.shop.model.Order;
import com.zsj.es.shop.service.OrderService;
import com.zsj.es.system.model.Role;
import com.zsj.es.system.service.RoleService;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * 订单管理
 */
@Controller
@RequestMapping("/shop/order")
public class OrderController extends BaseController {

    @Autowired
    private OrderService orderService;
    
    @Autowired
    private RoleService roleService;

    @RequiresPermissions("order:view")
    @RequestMapping()
    public String order() {
        return "shop/order.html";
    }

    /**
     * 查询所有订单
     **/
    @RequiresPermissions("order:view")
    @ResponseBody
    @RequestMapping("/list")
    public PageResult<Order> list(HttpServletRequest request) {
    	PageParam pageParam = new PageParam(request);
        List<Role> roles = roleService.listByUserId(getLoginUserId());
        for (Role role : roles) {
        	if (role.getRoleId().equals(3)) {
            	pageParam.put("publish_id", getLoginUserId());
            }
		}
        //return new PageResult<>(orderService.page(pageParam, pageParam.getWrapper()).getRecords(), pageParam.getTotal());
        return orderService.listOrder(pageParam.setDefaultOrder(null, new String[]{"create_time"}));
    }

    /**
     * 更新订单快递信息
     **/
    @RequiresPermissions("order:update")
    @ResponseBody
    @RequestMapping("/updateExpress")
    public JsonResult updateExpress(Order order){
        SnowflakeIdWorker sfw = new SnowflakeIdWorker(0,0);
        Order orderInfo=new Order();
        orderInfo.setOrderId(order.getOrderId());  //设置订单号
        orderInfo.setExpressName(order.getExpressName());  //设置快递公司
        orderInfo.setExpressNo("KD"+sfw.nextId()); //设置快递单号
        orderInfo.setDeliveryTime(new Date()); //设置发货时间
        if(orderService.updateById(orderInfo)){
            //发货成功，修改状态为：配送中
            updateOrderStatus(order.getOrderId(),1);
            return JsonResult.ok("发货成功");
        }
        return JsonResult.error("发货失败");
    }

    /**
     * 修改订单状态
     **/
    @RequiresPermissions("order:update")
    @ResponseBody
    @RequestMapping("/updateOrderStatus")
    public JsonResult updateOrderStatus(Integer orderId,Integer orderStatus){
        Order order=new Order();
        order.setOrderId(orderId);
        order.setOrderStatus(orderStatus);
        if(orderService.updateById(order)){
            return JsonResult.ok("修改成功");
        }
        return JsonResult.error("修改失败");
    }

    /**
     * 删除订单
     **/
    @RequiresPermissions("order:update")
    @ResponseBody
    @RequestMapping("/delete")
    public JsonResult delete(Integer orderId){
        if(orderService.removeById(orderId)){
            return JsonResult.ok("删除成功");
        }
        return JsonResult.error("删除失败");
    }
}
