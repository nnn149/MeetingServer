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
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
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

    //当前客户端的ip
    private String ip;

    //当前客户端的userID
    private String userId;

    //当前客户端的roomNo
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
            logger.info("收到来自" + ip + "的信息:" + message);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(Session session) {
        //在线数减1
        logger.info("用户:" + ip + "关闭连接，退出房间" + roomId + "当前在线人数为" + onlineCount.addAndGet(-1));
    }

    /**
     * 连接发生错误时调用的方法
     */
    @OnError
    public void onError(Session session, Throwable error) {

    }


}
