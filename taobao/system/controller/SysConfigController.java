package com.zsj.es.system.controller;

import com.zsj.es.common.JsonResult;
import com.zsj.es.system.model.SysConfig;
import com.zsj.es.system.service.SysConfigService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 系统配置管理
 **/
@Controller
@RequestMapping("/system/config")
public class SysConfigController {

    @Autowired
    private SysConfigService sysConfigService;

    @RequiresPermissions("config:view")
    @RequestMapping()
    public String config(Model model) {
        SysConfig mails=sysConfigService.getById(1);
        model.addAttribute("mails", mails);
        return "system/config.html";
    }

    /**
     * 修改系统配置
     */
    @RequiresPermissions("config:update")
    @ResponseBody
    @RequestMapping("/update")
    public JsonResult update(SysConfig config){
        if(sysConfigService.updateById(config)){
            return JsonResult.ok("修改成功");
        }
        return JsonResult.error("修改失败");
    }
}
