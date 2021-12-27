package com.zsj.es.common.config;

import com.zsj.es.WebSocket.WebSocketController;
import com.zsj.es.WebSocket.service.MesInfoService;
import com.zsj.es.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@Configuration
public class WebSocketConfig {

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {

        return new ServerEndpointExporter();
    }

    @Autowired
    public void setUserService(UserService userService) {

        WebSocketController.userService = userService;
    }

    @Autowired
    public void setMesInfoService(MesInfoService mesInfoService) {

        WebSocketController.mesInfoService = mesInfoService;
    }
}

