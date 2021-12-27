package com.zsj.es.system.dao;

import com.zsj.es.common.PageParam;
import com.zsj.es.system.model.LoginRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 */
public interface LoginRecordMapper extends BaseMapper<LoginRecord> {

    // 分页查询
    List<LoginRecord> listFull(@Param("page") PageParam page);

    // 不分页
    List<LoginRecord> listAll(@Param("pageData") Map pageData);

}
