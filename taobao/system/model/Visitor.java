package com.zsj.es.system.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 网站访问表
 * </p>
 *
 */
@Data
@TableName("sys_visitor")
public class Visitor implements Serializable {

    private static final long serialVersionUID = 5347507588757889197L;

    /**
     * 访问id
     */
    @TableId(value = "visitor_id", type = IdType.AUTO)
    private Integer visitorId;

    /**
     * 访问IP
     */
    private String visitorIp;

    /**
     * 访问地址
     */
    private String visitorAddress;

    /**
     * 访问时间
     */
    private Date visitorTime;

	public Integer getVisitorId() {
		return visitorId;
	}

	public void setVisitorId(Integer visitorId) {
		this.visitorId = visitorId;
	}

	public String getVisitorIp() {
		return visitorIp;
	}

	public void setVisitorIp(String visitorIp) {
		this.visitorIp = visitorIp;
	}

	public String getVisitorAddress() {
		return visitorAddress;
	}

	public void setVisitorAddress(String visitorAddress) {
		this.visitorAddress = visitorAddress;
	}

	public Date getVisitorTime() {
		return visitorTime;
	}

	public void setVisitorTime(Date visitorTime) {
		this.visitorTime = visitorTime;
	}
    
    

}
