package com.zsj.es.shop.controller;

import com.zsj.es.common.BaseController;
import com.zsj.es.common.JsonResult;
import com.zsj.es.common.PageParam;
import com.zsj.es.common.PageResult;
import com.zsj.es.common.utils.SnowflakeIdWorker;
import com.zsj.es.shop.model.PublicType;
import com.zsj.es.shop.service.PublicTypeService;
import com.zsj.es.system.model.Role;
import com.zsj.es.system.service.RoleService;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 分类管理
 */
@Controller
@RequestMapping("/shop/publicType")
public class PublicTypeController extends BaseController {

    @Autowired
    private PublicTypeService publicTypeService;
    
    @RequiresPermissions("publicType:view")
    @RequestMapping()
    public String publicType(Model model) {
        return "shop/publicType.html";
    }

    /**
     * 查询所有
     **/
    @RequiresPermissions("publicType:view")
    @ResponseBody
    @RequestMapping("/list")
    public PageResult<PublicType> list(HttpServletRequest request) {
        PageParam pageParam = new PageParam(request);

    	return publicTypeService.list(pageParam.setDefaultOrder(null, new String[]{"id"}));
    }

    /**
     * 添加
     **/
    @RequiresPermissions("publicType:update")
    @ResponseBody
    @RequestMapping("/add")
    public JsonResult add(PublicType publicType) {
        if (publicTypeService.save(publicType)) {
            return JsonResult.ok("添加成功");
        }
        return JsonResult.error("添加失败");
    }

    /**
     * 修改
     **/
    @RequiresPermissions("publicType:update")
    @ResponseBody
    @RequestMapping("/update")
    public JsonResult update(PublicType publicType) {
        if (publicTypeService.updateById(publicType)) {
            return JsonResult.ok("修改成功");
        }
        return JsonResult.error("修改失败");
    }

    /**
     * 删除
     **/
    @RequiresPermissions("publicType:update")
    @ResponseBody
    @RequestMapping("/delete")
    public JsonResult delete(Integer publicTypeId) {
        if (publicTypeService.removeById(publicTypeId)) {
            return JsonResult.ok("删除成功");
        }
        return JsonResult.error("删除失败");
    }

}
