package com.zsj.es.shop.controller;

import com.zsj.es.common.BaseController;
import com.zsj.es.common.JsonResult;
import com.zsj.es.common.PageParam;
import com.zsj.es.common.PageResult;
import com.zsj.es.common.utils.SnowflakeIdWorker;
import com.zsj.es.shop.model.Goods;
import com.zsj.es.shop.model.PublicType;
import com.zsj.es.shop.service.GoodsService;
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
import java.util.List;

/**
 * 商品管理
 */
@Controller
@RequestMapping("/shop/goods")
public class GoodsController extends BaseController {

    @Autowired
    private GoodsService goodsService;
    
    @Autowired
    private PublicTypeService publicTypeService;
    
    @Autowired
    private RoleService roleService;
    
    @RequiresPermissions("goods:view")
    @RequestMapping()
    public String goods(Model model) {
        List<PublicType> publics = publicTypeService.list();
        model.addAttribute("publices", publics);
        return "shop/goods.html";
    }

    /**
     * 查询所有商品
     **/
    @RequiresPermissions("goods:view")
    @ResponseBody
    @RequestMapping("/list")
    public PageResult<Goods> list(HttpServletRequest request) {
        PageParam pageParam = new PageParam(request);
        // 管理员看所有，商家加publish_id查询自己发布的
        List<Role> roles = roleService.listByUserId(getLoginUserId());
        for (Role role : roles) {
        	if (role.getRoleId().equals(3)) {
            	pageParam.put("publish_id", getLoginUserId());
            }
		}

    	return goodsService.list(pageParam.setDefaultOrder(null, new String[]{"create_time"}));
    }

    /**
     * 添加商品
     **/
    @RequiresPermissions("goods:update")
    @ResponseBody
    @RequestMapping("/add")
    public JsonResult add(Goods goods) {
        System.out.println(goods);
        SnowflakeIdWorker sfw = new SnowflakeIdWorker(0,0);
        goods.setGoodsSn("CX"+sfw.nextId());    //设置商品唯一编码
        goods.setPublishId(getLoginUserId());
        if (goodsService.save(goods)) {
            return JsonResult.ok("添加成功");
        }
        return JsonResult.error("添加失败");
    }

    /**
     * 修改商品
     **/
    @RequiresPermissions("goods:update")
    @ResponseBody
    @RequestMapping("/update")
    public JsonResult update(Goods goods) {
        if (goodsService.updateById(goods)) {
            return JsonResult.ok("修改成功");
        }
        return JsonResult.error("修改失败");
    }

    /**
     * 删除商品
     **/
    @RequiresPermissions("goods:update")
    @ResponseBody
    @RequestMapping("/delete")
    public JsonResult delete(Integer goodsId) {
        if (goodsService.removeById(goodsId)) {
            return JsonResult.ok("删除成功");
        }
        return JsonResult.error("删除失败");
    }

    /**
     * 修改商品上架状态
     **/
    @RequiresPermissions("goods:update")
    @ResponseBody
    @RequestMapping("/updateState")
    public JsonResult updateState(Goods goods){
        if(goodsService.updateById(goods)){
            return JsonResult.ok("修改成功");
        }
        return JsonResult.error("修改失败");
    }
}
