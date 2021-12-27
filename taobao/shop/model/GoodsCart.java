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
 * 购物车表
 * </p>
 *
 */
@Data
@TableName("e_goods_cart")
public class GoodsCart implements Serializable {

    private static final long serialVersionUID = 5268936437082123054L;

    /**
     * 主键id
     */
    @TableId(value = "cart_id", type = IdType.AUTO)
    private Integer cartId;

    /**
     * 用户id
     */
    private Integer userId;

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
     * 商品数量
     */
    private Integer goodsNum;

    /**
     * 商品总价
     */
    private BigDecimal goodsMoney;
    /**
     * 商品图片
     */
    @TableField(exist = false)
    private String goodsImg;

    /**
     * 商品单价
     */
    @TableField(exist = false)
    private BigDecimal price;

    /**
     * 加入时间
     */
    private Date createTime;

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
     * 定制logo图片
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

	public Integer getCartId() {
		return cartId;
	}

	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
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

	public String getGoodsImg() {
		return goodsImg;
	}

	public void setGoodsImg(String goodsImg) {
		this.goodsImg = goodsImg;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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
    
    
}
