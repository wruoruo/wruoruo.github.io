package com.zsj.es.api.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zsj.es.common.JsonResult;
import com.zsj.es.shop.model.GoodsCart;
import com.zsj.es.shop.service.GoodsCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * api购物车相关接口
 */
@RequestMapping("/api/cart")
@RestController
public class ApiGoodsCartController {

    @Autowired
    private GoodsCartService goodsCartService;

    /**
     * 根据用户id查询购物车信息
     **/
    @ResponseBody
    @GetMapping("/listByUser")
    public JsonResult listByUser(Integer userId){
        List<GoodsCart> list=goodsCartService.listByUser(userId);
        return JsonResult.ok(0,"查询成功").put("data",list);
    }

    /**
     * 根据用户id查询购物车数量
     **/
    @ResponseBody
    @GetMapping("/countByUser")
    public JsonResult countByUser(Integer userId){
        Integer count=goodsCartService.countByUser(userId);
        return JsonResult.ok(0,"查询成功").put("data",count);
    }

    /**
     * 添加购物车
     **/
    @ResponseBody
    @PostMapping("/add")
    public JsonResult addCart(GoodsCart goodsCart){
        GoodsCart g=goodsCartService.getOne(
                new QueryWrapper<GoodsCart>()
                        .eq("user_id",goodsCart.getUserId())
                        .eq("goods_sn",goodsCart.getGoodsSn())
                        .eq("made_logo",goodsCart.getMadeLogo())
                        .eq("made_text",goodsCart.getMadeText())
                        .eq("goods_color",goodsCart.getGoodsColor())
        );
        if(g!=null){
            return JsonResult.error("该商品已在购物车内,请不要重复添加");
        }
        if(goodsCartService.save(goodsCart)){
            return JsonResult.ok("添加成功");
        }
        return JsonResult.error("添加失败");
    }

    /**
     * 删除购物车
     **/
    @ResponseBody
    @PostMapping("/delete")
    public JsonResult delete(Integer cartId){
        if(goodsCartService.removeById(cartId)){
            return JsonResult.ok("删除成功");
        }
        return JsonResult.error("删除失败");
    }
}
