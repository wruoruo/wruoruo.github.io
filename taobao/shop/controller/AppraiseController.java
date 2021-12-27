package com.zsj.es.shop.controller;

import com.zsj.es.common.BaseController;
import com.zsj.es.common.JsonResult;
import com.zsj.es.common.PageParam;
import com.zsj.es.common.PageResult;
import com.zsj.es.shop.model.Appraise;
import com.zsj.es.shop.service.AppraiseService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 评价管理
 */
@Controller
@RequestMapping("/shop/appraise")
public class AppraiseController extends BaseController {

    @Autowired
    private AppraiseService appraiseService;

    @RequiresPermissions("appraise:view")
    @RequestMapping()
    public String goods() {
        return "shop/appraise.html";
    }

    /**
     * 查询所有评价
     **/
    @RequiresPermissions("appraise:view")
    @ResponseBody
    @RequestMapping("/list")
    public PageResult<Appraise> list(HttpServletRequest request) {

        return appraiseService.listFull(new PageParam(request).setDefaultOrder(null, new String[]{"appraise_time"}));
    }

    /**
     * 修改评价
     **/
    @RequiresPermissions("appraise:update")
    @ResponseBody
    @RequestMapping("/update")
    public JsonResult update(Appraise appraise) {
        if (appraiseService.updateById(appraise)) {
            return JsonResult.ok("修改成功");
        }
        return JsonResult.error("修改失败");
    }

    /**
     * 删除评价
     **/
    @RequiresPermissions("appraise:update")
    @ResponseBody
    @RequestMapping("/delete")
    public JsonResult delete(Integer appraiseId) {
        if (appraiseService.removeById(appraiseId)) {
            return JsonResult.ok("删除成功");
        }
        return JsonResult.error("删除失败");
    }
}
