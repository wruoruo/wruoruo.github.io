package com.zsj.es.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsj.es.system.dao.SysConfigMapper;
import com.zsj.es.system.model.SysConfig;
import com.zsj.es.system.service.SysConfigService;
import org.springframework.stereotype.Service;

@Service
public class SysConfigServiceImpl extends ServiceImpl<SysConfigMapper, SysConfig> implements SysConfigService {
}
