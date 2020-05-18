/**
 * FileName: WebrtcWS
 * Author:   10418
 * Date:     2020-05-16 18:00
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 楠楠(Nannan)
 */
package cn.nicenan.meeting.websocket;

import cn.nicenan.meeting.bean.WebrtcMessage;
import cn.nicenan.meeting.service.WebrtcRoomService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * DESC〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author 10418
 * @create 2020-05-16
 * @since 1.0.0
 */
@ServerEndpoint(value = "/ws/webrtc")
@Component
public class WebrtcWS {
    private final static Logger logger = LoggerFactory.getLogger(WebrtcWS.class);
    //在线总人数
    private static volatile AtomicInteger onlineCount = new AtomicInteger(0);

    private static WebrtcRoomService webrtcRoomService;

    @Autowired
    public void setRoomService(WebrtcRoomService webrtcRoomService) {
        WebrtcWS.webrtcRoomService = webrtcRoomService;
    }

    //当前客户端的ip
    private String ip;

    //当前客户端的userID
    private String userId;

    private String roomId;

    //与当前客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        ip = (String) session.getUserProperties().get("clientIp");
        logger.info("用户:" + ip + "连接到服务器,当前在线人数为" + onlineCount.incrementAndGet());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param stringMessage 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(Session session, String stringMessage) {
        try {
            WebrtcMessage message = new ObjectMapper().readValue(stringMessage, WebrtcMessage.class);
            logger.info(message.toString());
            switch (message.getCommand()) {
                case WebrtcMessage.TYPE_COMMAND_ROOM_CREATE:
                    webrtcRoomService.createRoom(message.getRoomId(), message.getRoomPw(), message.getToken(), this);
                    break;
                case WebrtcMessage.TYPE_COMMAND_ROOM_ENTER:
                    webrtcRoomService.enterRoom(message.getRoomId(), message.getRoomPw(), message.getToken(), this);
                    break;
                case WebrtcMessage.TYPE_COMMAND_CHAT:
                case WebrtcMessage.TYPE_COMMAND_READY:
                    webrtcRoomService.forwardToEveryoneInRoom(roomId, message, "");
                    break;
                case WebrtcMessage.TYPE_COMMAND_OFFER:
                case WebrtcMessage.TYPE_COMMAND_ANSWER:
                case WebrtcMessage.TYPE_COMMAND_CANDIDATE:
                    String toUserId = message.getUserId();
                    // 把userId 设为offer发送方的id
                    message.setUserId(userId);
                    webrtcRoomService.forwardToOneInRoom(roomId, message, toUserId);
                    break;
                default:
                    break;
            }
            logger.info("收到来自" + userId + "的信息:" + message);
        } catch (Exception ex) {
            try {
                ex.printStackTrace();
                session.getBasicRemote().sendText(new ObjectMapper().writeValueAsString(new WebrtcMessage(WebrtcMessage.TYPE_COMMAND_ERROR, userId, ex.getMessage())));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(Session session) {
        //在线数减1
        logger.info("用户:" + userId + "关闭连接,当前在线人数为" + onlineCount.addAndGet(-1));
        if (!"".equals(roomId) && !"0".equals(roomId) && !"".equals(userId) && !"0".equals(userId)) {
            webrtcRoomService.kickUser(roomId, userId);
        }

    }

    /**
     * 连接发生错误时调用的方法
     */
    @OnError
    public void onError(Session session, Throwable error) {
        logger.info("ws错误" + error.getMessage());
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }
}
