package com.zsj.es.api.controller;

import com.zsj.es.common.JsonResult;
import com.zsj.es.common.PageParam;
import com.zsj.es.common.PageResult;
import com.zsj.es.common.exception.BusinessException;
import com.zsj.es.shop.model.Appraise;
import com.zsj.es.shop.model.Order;
import com.zsj.es.shop.service.AppraiseService;
import com.zsj.es.shop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * api评价相关接口
 */
@RequestMapping("/api/appraise")
@RestController
public class ApiAppraiseController {

    @Autowired
    private AppraiseService appraiseService;

    @Autowired
    private OrderService orderService;

    /**
     * 查询评价信息
     */
    @ResponseBody
    @RequestMapping("/list")
    public PageResult<Appraise> list(HttpServletRequest request) {

        return appraiseService.listFull(new PageParam(request).setDefaultOrder(null, new String[]{"appraise_time"}));
    }
    /**
     * 添加评价信息
     **/
    @ResponseBody
    @PostMapping("/add")
    @Transactional(rollbackFor = Exception.class)
    public JsonResult add(Appraise appraise,Integer orderId){
        if(appraiseService.save(appraise)){
            //评价成功修改订单状态
            Order order=new Order();
            order.setOrderId(orderId);
            order.setOrderStatus(4);
            if(!orderService.updateById(order)){
                throw new BusinessException("操作失败，请重试");
            }
            return JsonResult.ok("评价成功");
        }
        return JsonResult.error("评价失败");
    }
}
