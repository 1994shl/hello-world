package com.shl.ssa.shop.product.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 施海林
 * @create 2023-09-20 17:00
 * @Description
 */
@Component
@Slf4j
@ServerEndpoint("/inspection/execute/{authSessionId}")
public class WebSocketService {

    /**
     * 与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    private Session session;

    /**
     * session集合,存放对应的session
     */
    private static ConcurrentHashMap<String, Session> sessionPool = new ConcurrentHashMap<>();

    /**
     * 建立WebSocket连接
     *
     * @param session
     */
    @OnOpen
    public void onOpen(Session session, @PathParam(value = "authSessionId") String authSessionId) {
        log.info("WebSocket建立连接中,连接用户鉴权sessionId :{}", authSessionId);
        try {
            Session historySession = sessionPool.get(authSessionId);
            // historySession不为空,说明已经有人登陆账号,应该删除登陆的WebSocket对象
            if (historySession != null) {
                log.info("用户{}重复登录，关闭原连接会话！", authSessionId);
                historySession.close();
            }
        } catch (IOException e) {
            log.error("重复登录异常,错误信息：{}", e.toString());
        }
        // 建立连接
        this.session = session;
        sessionPool.put(authSessionId, session);
        log.info("建立连接完成,当前在线人数为：{}", sessionPool.size());
    }

    /**
     * 发生错误
     *
     * @param throwable e
     */
    @OnError
    public void onError(Throwable throwable, @PathParam(value = "authSessionId") String authSessionId) {
        log.error("websocket error happened ,authSessionId:{}，and error info is {}",
                authSessionId, throwable.getMessage());
        Session remove = sessionPool.remove(authSessionId);
        try {
            remove.close();
        } catch (IOException e) {
            log.error("close异常,错误信息：{}", e.toString());
        }
    }

    /**
     * 连接关闭
     */
    @OnClose
    public void onClose(@PathParam(value = "authSessionId") String authSessionId) {
        Session remove = sessionPool.remove(authSessionId);
        try {
            remove.close();
        } catch (IOException e) {
            log.error("close异常,错误信息：{}", e.toString());
        }
        log.info("用户{}连接断开,当前在线人数为：{}", authSessionId, sessionPool.size());
    }

    /**
     * 接收客户端消息
     *
     * @param message 接收的消息
     */
    @OnMessage
    public void onMessage(String message, @PathParam(value = "authSessionId") String authSessionId) {
        //连接保持
        if ("ping".equals(message)) {
            sendMessage("pong", authSessionId);
            return;
        }
        //暂无其他业务
        sendMessage("hello,world!", authSessionId);
    }

    /**
     * 服务端发送消息给客户端
     */
    private static void sendMessage(String message, String authSessionId) {
        Session session = sessionPool.get(authSessionId);
        try {
            session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            log.error("推送消息到指定用户发生错误：{}", e.toString());
        }
    }

    /**
     * 群发消息
     *
     * @param message 发送的消息
     */
    public static void sendAllMessage(String message) {
        Set<String> keySet = sessionPool.keySet();
        keySet.forEach(key -> sendMessage(message, key));
    }

}