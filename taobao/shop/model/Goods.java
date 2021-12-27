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
 * 商品表
 * </p>
 *
 */
@Data
@TableName("e_goods")
public class Goods implements Serializable {

    private static final long serialVersionUID = -284644051341858603L;

    /**
     * 主键id
     */
    @TableId(value = "goods_id", type = IdType.AUTO)
    private Integer goodsId;

    /**
     * 商品编号
     */
    private String goodsSn;
    
    /**
     * 发布人id
     */
    private Integer publishId;
    
    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 商品图片
     */
    private String goodsImg;

    /**
     * 商品详情图片
     */
    private String goodsDetailsImgs;

    /**
     * 价格
     */
    private BigDecimal shopPrice;

    /**
     * 参考价格
     */
    private BigDecimal shopRePrice;

    /**
     * 商品分类
     */
    private Integer goodsType;

    /**
     * 商品尺寸
     */
    private String goodsSizes;

    /**
     * 单位
     */
    private String goodsUnit;
    /**
     * 作者
     */
    private String author;
    /**
     * 出版社
     */
    private String press;
    /**
     * 是否上架 0:不上架 1:上架
     */
    private Integer isSale;

    /**
     * 商品描述
     */
    private String goodsDesc;

    /**
     * 总销售量
     */
    private Integer saleNum;

    /**
     * 访问数
     */
    private Integer visitNum;

    /**
     * 评价数
     */
    private Integer appraiseNum;

    /**
     * 创建时间
     */
    private Date createTime;
    
    @TableField(exist = false)
    private String nickName;  // 用户昵称
    
    @TableField(exist = false)
    private String goodsTypeName;  // 类型名称
    /**
     * 搜索关键字
     */
    @TableField(exist = false)
    private String keyword;

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}
	
	public Integer getPublishId() {
        return publishId;
    }

    public void setPublishId(Integer publishId) {
        this.publishId = publishId;
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

	public String getGoodsDetailsImgs() {
		return goodsDetailsImgs;
	}

	public void setGoodsDetailsImgs(String goodsDetailsImgs) {
		this.goodsDetailsImgs = goodsDetailsImgs;
	}

	public BigDecimal getShopPrice() {
		return shopPrice;
	}

	public void setShopPrice(BigDecimal shopPrice) {
		this.shopPrice = shopPrice;
	}

	public BigDecimal getShopRePrice() {
		return shopRePrice;
	}

	public void setShopRePrice(BigDecimal shopRePrice) {
		this.shopRePrice = shopRePrice;
	}

	public Integer getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(Integer goodsType) {
		this.goodsType = goodsType;
	}

	public String getGoodsSizes() {
		return goodsSizes;
	}

	public void setGoodsSizes(String goodsSizes) {
		this.goodsSizes = goodsSizes;
	}

	public String getGoodsUnit() {
		return goodsUnit;
	}

	public void setGoodsUnit(String goodsUnit) {
		this.goodsUnit = goodsUnit;
	}

	public Integer getIsSale() {
		return isSale;
	}

	public void setIsSale(Integer isSale) {
		this.isSale = isSale;
	}

	public String getGoodsDesc() {
		return goodsDesc;
	}

	public void setGoodsDesc(String goodsDesc) {
		this.goodsDesc = goodsDesc;
	}

	public Integer getSaleNum() {
		return saleNum;
	}

	public void setSaleNum(Integer saleNum) {
		this.saleNum = saleNum;
	}

	public Integer getVisitNum() {
		return visitNum;
	}

	public void setVisitNum(Integer visitNum) {
		this.visitNum = visitNum;
	}

	public Integer getAppraiseNum() {
		return appraiseNum;
	}

	public void setAppraiseNum(Integer appraiseNum) {
		this.appraiseNum = appraiseNum;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
    
	public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPress() {
		return press;
	}

	public void setPress(String press) {
		this.press = press;
	}

	public String getGoodsTypeName() {
		return goodsTypeName;
	}

	public void setGoodsTypeName(String goodsTypeName) {
		this.goodsTypeName = goodsTypeName;
	}
    
    
}
