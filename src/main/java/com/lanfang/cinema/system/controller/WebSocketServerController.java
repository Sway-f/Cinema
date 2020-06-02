package com.lanfang.cinema.system.controller;

import com.lanfang.cinema.system.Domain.Record;
import com.lanfang.cinema.system.Service.RecordService;
import com.lanfang.cinema.system.Service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;


@RestController
@Component
@ServerEndpoint(value = "/websocket/{uid}")
public class WebSocketServerController {
    // 保存 组id->组成员 的映射关系
    private static Map<String, WebSocketServerController> connections = new ConcurrentHashMap<>();
    private static int onlineCount;
    private Session session;
    private String username;
    private Long uid;
    private Record record = new Record();



    private static ApplicationContext applicationContext;
    private RecordService recordService ;
    private UserService userService ;
    public static void setApplicationContext(ApplicationContext context) {
        WebSocketServerController.applicationContext = context;
    }



    // 收到消息调用的方法
    @OnMessage
    public void onMessage(Session session, String message) {
        System.out.println("来自客户端的消息:" + message);
        JSONObject json=JSONObject.fromObject(message);
        String string = null;  //需要发送的信息
        Long to = null;      //发送对象的用户标识
        if(json.has("message")){
            string = (String) json.get("message");
        }
        if(json.has("fid")){
            to =  Long.valueOf((String) json.get("fid")).longValue();
        }
        System.out.println("发送消息“"+string+"===="+username+"===="+to+"===="+uid);
        Date date = new Date(System.currentTimeMillis());
        //设置要获取到什么样的时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //获取String类型的时间
        String createdate = sdf.format(date);
        record.setMessage(string);
        record.setUser_id(uid);
        record.setUser_name(username);
        record.setTime(createdate);
        record.setF_id(to);
        userService = applicationContext.getBean(UserService.class);
        record.setF_name(userService.getUser(to).getUsername());
        record.setMe(1);
        recordService = applicationContext.getBean(RecordService.class);
        recordService.saveRecord(record);
        send(message,uid,to);

        try {
            session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // 建立连接调用的方法
    @OnOpen
    public void onOpen(Session session,@PathParam("uid") String uid) {
        System.out.println("-----------------------");
        this.session = session;
        String[] arr = uid.split(",");
        this.username = arr[0];    //用户名
        this.uid = Long.valueOf(arr[1]).longValue();         //用户id
        System.out.println("Client connected");
        connections.put(uid,this);     //添加到map中
        addOnlineCount();               // 在线数加
        System.out.println("有新连接加入！新用户："+username+",当前在线人数为" + getOnlineCount());

    }

    // 关闭连接调用的方法
    @OnClose
    public void onClose() {
        subOnlineCount();
        System.out.println("Connection closed,当前在线人数为" + getOnlineCount());
    }

    // 传输消息错误调用的方法
    @OnError
    public void OnError(Session session, Throwable error) {
        System.out.println("Connection error");
        error.printStackTrace();
    }

    public void send(String msg,Long uid,Long to){
            //to指定用户
            for (WebSocketServerController item : connections.values()) {
                if (item.uid.equals(to) ){
                    System.out.println("将"+msg+"发送给"+item.username);
                    item.session.getAsyncRemote().sendText(msg);
                }

            }
            System.out.println("接受人的id为："+to);

    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketServerController.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketServerController.onlineCount--;
    }



}
