package com.zsj.es.api.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zsj.es.common.JsonResult;
import com.zsj.es.common.PageParam;
import com.zsj.es.common.PageResult;
import com.zsj.es.shop.model.Goods;
import com.zsj.es.shop.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * api商品相关接口
 */
@RequestMapping("/api/goods")
@RestController
public class ApiGoodsController {

    @Autowired
    private GoodsService goodsService;

    /**
     * 查询商品列表(不含logo商品)
     */
    @ResponseBody
    @RequestMapping("/list")
    public PageResult<Goods> list(HttpServletRequest request){

        return goodsService.listFull(new PageParam(request).setDefaultOrder(null, new String[]{"create_time"}));
    }

    /**
     * 根据商品类型查询商品列表
     */
    @ResponseBody
    @RequestMapping("/listByType")
    public PageResult<Goods> listByType(HttpServletRequest request){

        return goodsService.listByType(new PageParam(request).setDefaultOrder(null, new String[]{"create_time"}));
    }

    /**
     * 查询商品详情
     */
    @ResponseBody
    @RequestMapping("/details")
    public JsonResult details(String goodsSn){
        List<Goods> list=goodsService.list(new QueryWrapper<Goods>().eq("goods_sn",goodsSn));
       return JsonResult.ok(0,"查询成功").put("data",list);
    }

}
