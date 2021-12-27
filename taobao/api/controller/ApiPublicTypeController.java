package com.zsj.es.api.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zsj.es.common.JsonResult;
import com.zsj.es.common.PageParam;
import com.zsj.es.common.PageResult;
import com.zsj.es.shop.model.Goods;
import com.zsj.es.shop.model.PublicType;
import com.zsj.es.shop.service.GoodsService;
import com.zsj.es.shop.service.PublicTypeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * api分类相关接口
 */
@RequestMapping("/api/publicType")
@RestController
public class ApiPublicTypeController {

    @Autowired
    private PublicTypeService publicService;

    /**
     * 查询列表
     */
    @ResponseBody
    @RequestMapping("/list")
    public PageResult<PublicType> list(HttpServletRequest request){

        return publicService.listFull(new PageParam(request).setDefaultOrder(null, new String[]{"id"}));
    }


}
