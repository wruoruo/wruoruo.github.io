package com.zsj.es.shop.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 订单表
 * </p>
 *
 */
@Data
@TableName("e_order")
public class Order implements Serializable {

    private static final long serialVersionUID = 8701378402424363139L;

    /**
     * 主键id
     */
    @TableId(value = "order_id", type = IdType.AUTO)
    private Integer orderId;

    /**
     * 订单编号
     */
    private String orderNo;
    
    /**
     * 卖家id
     */
    private Integer publishId;
    
    /**
     * 卖家电话
     */
    @TableField(exist = false)
    private String publishPhone;
    
    /**
     * 买家id
     */
    private Integer userId;

    /**
     * 用户昵称
     */
    @TableField(exist = false)
    private String nickName;

    /**
     * 用户电话
     */
    @TableField(exist = false)
    private String phone;

    /**
     * 收货地址
     */
    @TableField(exist = false)
    private String receivingAddress;

    /**
     * 商品编号
     */
    private String goodsSn;

    /**
     * 商品名称
     */
    @TableField(exist = false)
    private String goodsName;

    /**
     * 商品图片
     */
    @TableField(exist = false)
    private String goodsImg;

    /**
     * 商品数量
     */
    private Integer goodsNum;

    /**
     * 商品金额
     */
    private BigDecimal goodsMoney;

    /**
     * 订单状态 -3:用户拒收 -2:未付款的订单 -1：用户取消 0:待发货 1:配送中 2:用户确认收货 3：待评价 4：已完结
     */
    private Integer orderStatus;


    /**
     * 发货时间
     */
    private Date deliveryTime;

    /**
     * 快递公司
     */
    private String expressName;

    /**
     * 快递单号
     */
    private String expressNo;

    /**
     * 下单时间
     */
    private Date createTime;

    /**
     * 收货时间
     */
    private Date receiveTime;

    /**
     * 拒收原因
     */
    private String rejectReason;

    /**
     * 取消原因
     */
    private String cancelReason;

    /**
     * 订单类型1：成品订单 2：定制订单
     */
    private Integer orderType;

    /**
     * 定制logo
     */
    private String madeLogo;

    /**
     * 定制logo名称
     */
    @TableField(exist = false)
    private String logoName;

    /**
     * 定制logo图
     */
    @TableField(exist = false)
    private String logoImg;

    /**
     * 定制文字
     */
    private String madeText;

    /**
     * 商品颜色
     */
    private String goodsColor;

    /**
     * 商品尺码
     */
    private String goodsSize;

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getReceivingAddress() {
		return receivingAddress;
	}

	public void setReceivingAddress(String receivingAddress) {
		this.receivingAddress = receivingAddress;
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

	public String getGoodsImg() {
		return goodsImg;
	}

	public void setGoodsImg(String goodsImg) {
		this.goodsImg = goodsImg;
	}

	public Integer getGoodsNum() {
		return goodsNum;
	}

	public void setGoodsNum(Integer goodsNum) {
		this.goodsNum = goodsNum;
	}

	public BigDecimal getGoodsMoney() {
		return goodsMoney;
	}

	public void setGoodsMoney(BigDecimal goodsMoney) {
		this.goodsMoney = goodsMoney;
	}

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Date getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(Date deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public String getExpressName() {
		return expressName;
	}

	public void setExpressName(String expressName) {
		this.expressName = expressName;
	}

	public String getExpressNo() {
		return expressNo;
	}

	public void setExpressNo(String expressNo) {
		this.expressNo = expressNo;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getReceiveTime() {
		return receiveTime;
	}

	public void setReceiveTime(Date receiveTime) {
		this.receiveTime = receiveTime;
	}

	public String getRejectReason() {
		return rejectReason;
	}

	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}

	public String getCancelReason() {
		return cancelReason;
	}

	public void setCancelReason(String cancelReason) {
		this.cancelReason = cancelReason;
	}

	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	public String getMadeLogo() {
		return madeLogo;
	}

	public void setMadeLogo(String madeLogo) {
		this.madeLogo = madeLogo;
	}

	public String getLogoName() {
		return logoName;
	}

	public void setLogoName(String logoName) {
		this.logoName = logoName;
	}

	public String getLogoImg() {
		return logoImg;
	}

	public void setLogoImg(String logoImg) {
		this.logoImg = logoImg;
	}

	public String getMadeText() {
		return madeText;
	}

	public void setMadeText(String madeText) {
		this.madeText = madeText;
	}

	public String getGoodsColor() {
		return goodsColor;
	}

	public void setGoodsColor(String goodsColor) {
		this.goodsColor = goodsColor;
	}

	public String getGoodsSize() {
		return goodsSize;
	}

	public void setGoodsSize(String goodsSize) {
		this.goodsSize = goodsSize;
	}

	public Integer getPublishId() {
		return publishId;
	}

	public void setPublishId(Integer publishId) {
		this.publishId = publishId;
	}

	public String getPublishPhone() {
		return publishPhone;
	}

	public void setPublishPhone(String publishPhone) {
		this.publishPhone = publishPhone;
	}
    
    
}
