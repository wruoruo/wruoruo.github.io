package com.zsj.es.api.controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zsj.es.common.JsonResult;
import com.zsj.es.shop.model.ApplyMerchant;
import com.zsj.es.shop.service.ApplyMerchantService;

/**
 * api申请相关接口
 */
@RequestMapping("/api/applyMerchantMapper")
@RestController
public class ApiApplyMerchantController {

    @Autowired
    private ApplyMerchantService applyMerchantService;

    /**
     * 添加申请信息
     **/
    @ResponseBody
    @PostMapping("/add")
    @Transactional(rollbackFor = Exception.class)
    public JsonResult add(ApplyMerchant applyMerchant){
    	applyMerchant.setStatus(1);
    	applyMerchant.setApplyTime(new Date());
        if(applyMerchantService.save(applyMerchant)){
            return JsonResult.ok("申请成功,等待管理员审核");
        }
        return JsonResult.error("申请失败");
    }
    
    /**
     * 根据用户是否申请过商家
     **/
    @GetMapping("/getIsApply")
    public JsonResult getIsApply(Integer userId){
    	List<ApplyMerchant> list = applyMerchantService.list(new QueryWrapper<ApplyMerchant>().eq("user_id",userId));
    	int result = 1;
    	if (list.size() == 0) {
    		result = 1;
    	} else {
    		List<Integer> statusList = list.stream().map(a->a.getStatus()).collect(Collectors.toList());
    		if (statusList.contains(1)) {
    			result = 2;
    		} else if (statusList.contains(2)){
    			result = 3;
    		}
    	}
        return JsonResult.ok("查询成功").put("data",result);
    }
}
