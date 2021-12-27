package com.zsj.es.shop.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 商品评价表
 * </p>
 *
 */
@Data
@TableName("e_appraise")
public class Appraise implements Serializable {

    private static final long serialVersionUID = -9133401464345687054L;

    /**
     * 主键id
     */
    @TableId(value = "appraise_id", type = IdType.AUTO)
    private Integer appraiseId;

    /**
     * 商品编号
     */
    private String goodsSn;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 评价用户id
     */
    private Integer userId;

    /**
     * 评价用户昵称
     */
    @TableField(exist = false)
    private String nickName;

    /**
     * 评价用户头像
     */
    @TableField(exist = false)
    private String avatar;

    /**
     * 物流服务
     */
    private Integer logisticsService;

    /**
     * 商品质量
     */
    private Integer commodityQuality;

    /**
     * 描述相符
     */
    private Integer coincide;

    /**
     * 评价内容
     */
    private String comments;

    /**
     * 评价时间
     */
    private Date appraiseTime;

	public Integer getAppraiseId() {
		return appraiseId;
	}

	public void setAppraiseId(Integer appraiseId) {
		this.appraiseId = appraiseId;
	}

	public String getGoodsSn() {
		return goodsSn;
	}

	public void setGoodsSn(String goodsSn) {
		this.goodsSn = goodsSn;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Integer getLogisticsService() {
		return logisticsService;
	}

	public void setLogisticsService(Integer logisticsService) {
		this.logisticsService = logisticsService;
	}

	public Integer getCommodityQuality() {
		return commodityQuality;
	}

	public void setCommodityQuality(Integer commodityQuality) {
		this.commodityQuality = commodityQuality;
	}

	public Integer getCoincide() {
		return coincide;
	}

	public void setCoincide(Integer coincide) {
		this.coincide = coincide;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Date getAppraiseTime() {
		return appraiseTime;
	}

	public void setAppraiseTime(Date appraiseTime) {
		this.appraiseTime = appraiseTime;
	}
    
    
}
