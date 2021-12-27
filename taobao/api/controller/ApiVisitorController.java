package com.zsj.es.api.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zsj.es.common.utils.UserAgentGetter;
import com.zsj.es.system.model.Visitor;
import com.zsj.es.system.service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * api访问相关接口
 */
@RequestMapping("/api/visitor")
@RestController
public class ApiVisitorController {

    @Autowired
    private VisitorService visitorService;

    /**
     * 新增访问信息,重复ip地址不添加
     */
    @PostMapping("/add")
    public void addVisitor(HttpServletRequest request){
        UserAgentGetter agentGetter = new UserAgentGetter(request);
        String ip=agentGetter.getIpAddr();

        Visitor v=visitorService.getOne(new QueryWrapper<Visitor>()
                .eq("visitor_ip",ip));
        if(v==null){
            Visitor v2=new Visitor();
            v2.setVisitorIp(ip);
            visitorService.save(v2);
        }
    }
}
