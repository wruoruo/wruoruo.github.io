package com.zsj.es.system.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("sys_config")
public class SysConfig implements Serializable {

    private static final long serialVersionUID = 3826431687691601081L;

    //配置ID
    @TableId(value = "config_id", type = IdType.AUTO)
    private Integer configId;

    //配置类型 1:邮件配置 2：oss配置 3：推送配置
    private Integer configType;

    //配置名称
    private String configName;

    //参数一：用于邮箱smtp服务器
    private String configPar1;

    //参数二：smtp服务端口
    private String configPar2;

    //参数三：发件邮箱
    private String configPar3;

    //参数四：授权码
    private String configPar4;

    //参数五：发件人名称
    private String configPar5;

    //配置状态 0：禁用 1：启用
    private Integer state;

	public Integer getConfigId() {
		return configId;
	}

	public void setConfigId(Integer configId) {
		this.configId = configId;
	}

	public Integer getConfigType() {
		return configType;
	}

	public void setConfigType(Integer configType) {
		this.configType = configType;
	}

	public String getConfigName() {
		return configName;
	}

	public void setConfigName(String configName) {
		this.configName = configName;
	}

	public String getConfigPar1() {
		return configPar1;
	}

	public void setConfigPar1(String configPar1) {
		this.configPar1 = configPar1;
	}

	public String getConfigPar2() {
		return configPar2;
	}

	public void setConfigPar2(String configPar2) {
		this.configPar2 = configPar2;
	}

	public String getConfigPar3() {
		return configPar3;
	}

	public void setConfigPar3(String configPar3) {
		this.configPar3 = configPar3;
	}

	public String getConfigPar4() {
		return configPar4;
	}

	public void setConfigPar4(String configPar4) {
		this.configPar4 = configPar4;
	}

	public String getConfigPar5() {
		return configPar5;
	}

	public void setConfigPar5(String configPar5) {
		this.configPar5 = configPar5;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
    
    
}
