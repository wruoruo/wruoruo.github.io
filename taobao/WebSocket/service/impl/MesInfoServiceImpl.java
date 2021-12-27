package com.zsj.es.WebSocket.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsj.es.WebSocket.dao.MesInfoMapper;
import com.zsj.es.WebSocket.model.MesInfo;
import com.zsj.es.WebSocket.service.MesInfoService;
import org.springframework.stereotype.Service;

@Service
public class MesInfoServiceImpl extends ServiceImpl<MesInfoMapper, MesInfo> implements MesInfoService {
}
