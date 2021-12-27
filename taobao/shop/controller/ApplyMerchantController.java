package com.zsj.es.shop.controller;

import com.zsj.es.common.BaseController;
import com.zsj.es.common.JsonResult;
import com.zsj.es.common.PageParam;
import com.zsj.es.common.PageResult;
import com.zsj.es.shop.model.ApplyMerchant;
import com.zsj.es.shop.service.ApplyMerchantService;
import com.zsj.es.system.model.UserRole;
import com.zsj.es.system.service.UserRoleService;

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
@RequestMapping("/shop/applyMerchant")
public class ApplyMerchantController extends BaseController {

    @Autowired
    private ApplyMerchantService applyMerchantService;
    
    @Autowired
    private UserRoleService userRoleService;

    @RequiresPermissions("applyMerchant:view")
    @RequestMapping()
    public String goods() {
        return "shop/applyMerchant.html";
    }

    /**
     * 查询所有申请
     **/
    @RequiresPermissions("applyMerchant:view")
    @ResponseBody
    @RequestMapping("/list")
    public PageResult<ApplyMerchant> list(HttpServletRequest request) {

        return applyMerchantService.listFull(new PageParam(request).setDefaultOrder(null, new String[]{"apply_time"}));
    }

    /**
     * 审核
     **/
    @RequiresPermissions("applyMerchant:update")
    @ResponseBody
    @RequestMapping("/audit")
    public JsonResult audit(ApplyMerchant applyMerchant) {
        if (applyMerchantService.updateById(applyMerchant)) {
        	if (applyMerchant.getStatus() == 2) {
        		// 授予商家权限
        		UserRole userRole = new UserRole();
        		userRole.setUserId(applyMerchant.getUserId());
        		userRole.setRoleId(3);
        		userRoleService.save(userRole);
        	}
            return JsonResult.ok("审核成功");
        }
        return JsonResult.error("审核失败");
    }

    /**
     * 删除
     **/
    @RequiresPermissions("appraise:update")
    @ResponseBody
    @RequestMapping("/delete")
    public JsonResult delete(Integer id) {
        if (applyMerchantService.removeById(id)) {
            return JsonResult.ok("删除成功");
        }
        return JsonResult.error("删除失败");
    }
}
