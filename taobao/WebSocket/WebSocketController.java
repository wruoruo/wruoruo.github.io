package com.zsj.es.WebSocket;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zsj.es.WebSocket.model.MesInfo;
import com.zsj.es.WebSocket.service.MesInfoService;
import com.zsj.es.system.model.User;
import com.zsj.es.system.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@ServerEndpoint("/websocket/{userId}")
@Component
@Slf4j
public class WebSocketController {

    private static int onlineCount = 0;
    public static User user;
    private static ConcurrentHashMap<String, WebSocketController> webSocketSet = new ConcurrentHashMap<String, WebSocketController>();
    private Session session;
    private String userId="";

    public static UserService userService;

    public static MesInfoService mesInfoService;
    /**
     * 浏览器连服务器时触发此方法
     * @param session
     * @param userId
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId) {
        userId = userId;//接收到发送消息的人员编号
        this.session = session;
        webSocketSet.put(userId, this);//加入map中
        addOnlineCount();           //在线数加1
        System.out.println("有新连接加入！"+"加入用户id"+userId+";当前在线人数为" + getOnlineCount());
        if (!userId.equals("")) {
            List<MesInfo> list=mesInfoService.list(
                    new QueryWrapper<MesInfo>()
                            .eq("collect_user_id",userId)
                            .eq("is_already_read",0));
            if(list!=null && list.size()>0){
                for(MesInfo mes:list){
                    try {
                        webSocketSet.get(mes.getCollectUserId()).sendMessage(JSONObject.toJSONString(mes));
                        //发送成功将此消息改为已读
                        MesInfo m2=new MesInfo();
                        m2.setMesId(mes.getMesId());
                        m2.setIsAlreadyRead(1);
                        mesInfoService.updateById(m2);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(@PathParam("userId") String userId) {
        if (!userId.equals("")) {
            //连接关闭用户下线，修改数据库状态
            User u=new User();
            u.setUserId(Integer.parseInt(userId));
            u.setUserOnlineType(0);
            userService.updateById(u);
            webSocketSet.remove(userId);  //从set中删除
            subOnlineCount();           //在线数减1
            System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount()+";关闭用户id："+userId);
        }
    }


    /**
     * 服务器收到消息时触发此方法
     * @param requestJson
     * @param session
     * @param userId
     */
    @OnMessage
    public void onMessage(String requestJson, Session session, @PathParam("userId") String userId) {
        //log.info("来自客户端的消息:" + requestJson);
        //此处通过requestJson消息对象可获取收信人id或者群id
        JSONObject messageObject = JSONObject.parseObject(requestJson);
        String username=messageObject.getString("nickName"); //消息来源用户名
        String collectUserId=messageObject.getString("collectUserId");//消息接收者可以是群id也可以是用户id
        String collectUserName=messageObject.getString("collectUserName");
        String type=messageObject.getString("type");
        String content=messageObject.getString("content");//消息内容
        String avatar=messageObject.getString("avatar");//消息来源头像
        String collectAvatar =messageObject.getString("collectAvatar");//消息接收者头像
        MesInfo mesInfo=new MesInfo();
        mesInfo.setAvatar(avatar);
        mesInfo.setUsername(username);
        mesInfo.setContent(content);
        mesInfo.setMine(false);
        mesInfo.setType(type);
        mesInfo.setId(userId);
        String jsonStrs=JSONObject.toJSONString(mesInfo);
        //log.info("发出的消息:" + jsonStrs);
        try {
            //通过收件人id获取连接session进行消息推送
            if (webSocketSet.get(collectUserId) != null) {
                webSocketSet.get(collectUserId).sendMessage(jsonStrs);
            }else{
                MesInfo mesInfo1=new MesInfo();
                mesInfo1.setUsername(collectUserName);
                mesInfo1.setContent("不好意思，我这会没在线");
                mesInfo1.setMine(false);
                mesInfo1.setType(type);
                mesInfo1.setId(collectUserId);
                mesInfo1.setAvatar(collectAvatar);
                String jsonStrss=JSONObject.toJSONString(mesInfo1);
                mesInfo.setCollectUserId(collectUserId);
                mesInfoService.save(mesInfo);
                webSocketSet.get(userId).sendMessage(jsonStrss);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 这个方法与上面几个方法不一样。没有用注解，是根据自己需要添加的方法。
     *
     * @param message
     * @throws IOException
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
        //this.session.getAsyncRemote().sendText(message);
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketController.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketController.onlineCount--;
    }

}