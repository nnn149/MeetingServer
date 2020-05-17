/**
 * FileName: WebrtcRoomServiceImpl
 * Author:   10418
 * Date:     2020-05-16 19:53
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 楠楠(Nannan)
 */
package cn.nicenan.meeting.service.impl;

import cn.nicenan.meeting.bean.WebrtcMessage;
import cn.nicenan.meeting.service.WebrtcRoomService;
import cn.nicenan.meeting.util.JwtTokenUtil;
import cn.nicenan.meeting.websocket.WebrtcWS;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * DESC〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author 10418
 * @create 2020-05-16
 * @since 1.0.0
 */
@Service
public class WebrtcRoomServiceImpl implements WebrtcRoomService {
    private final static Logger logger = LoggerFactory.getLogger(WebrtcRoomServiceImpl.class);
    private Map<String, Set<WebrtcWS>> rooms = new ConcurrentHashMap<>();
    private Map<String, String> roomsPw = new ConcurrentHashMap<>();

    @Override
    public int countOfUserInRoom(String roomId) {
        Set<WebrtcWS> room = rooms.get(roomId);
        if (room == null) {
            return 0;
        } else {
            return room.size();
        }
    }

    @Override
    public boolean kickUser(String roomId, WebrtcWS webrtcWS) {
        Set<WebrtcWS> users = rooms.get(roomId);
        if (users.size() > 0) {
            users.remove(webrtcWS);
            logger.info("用户:" + webrtcWS.getUserId() + "被移除,房间:" + roomId + "\n房间有: " + getRoomUsers(roomId));
            if (users.size() == 0) {
                rooms.remove(roomId);
                logger.info("房间:" + roomId + "  无人了被移除");
            }
        }
        return true;
    }

    @Override
    public boolean enterRoom(String roomId, String roomPw, String token, WebrtcWS webrtcWS) throws Exception {
        String userId = String.valueOf(new JwtTokenUtil().getUserIdFromToken(token));
        if (userId.equals("0")) {
            throw new Exception("身份验证失败");
        } else {
            webrtcWS.setUserId(userId);
        }
        Set<WebrtcWS> room = rooms.get(roomId);
        if (room == null) {
            throw new Exception("房间不存在");
        } else {
            if (roomsPw.get(roomId).equals(roomPw)) {
                webrtcWS.setRoomId(roomId);
                room.add(webrtcWS);
                webrtcWS.getSession().getBasicRemote().sendText(new ObjectMapper().writeValueAsString(new WebrtcMessage(WebrtcMessage.TYPE_COMMAND_SUCCESS, userId, "enter")));
                logger.info("用户:" + webrtcWS.getUserId() + "进入房间:" + roomId + "\n房间有: " + getRoomUsers(roomId));
                return true;
            } else {
                throw new Exception("密码错误");
            }
        }
    }

    @Override
    public boolean createRoom(String roomId, String roomPw, String token, WebrtcWS webrtcWS) throws Exception {
        String userId = String.valueOf(new JwtTokenUtil().getUserIdFromToken(token));
        if (userId.equals("0")) {
            throw new Exception("身份验证失败");
        } else {
            webrtcWS.setUserId(userId);
        }
        Set<WebrtcWS> room = rooms.get(roomId);
        if (room == null) {
            Set<WebrtcWS> synSet = Collections.synchronizedSet(new HashSet<>());
            //把自己创建人添加到房间
            webrtcWS.setRoomId(roomId);
            synSet.add(webrtcWS);
            //把房间加到房间map
            rooms.put(roomId, synSet);
            //设置房间密码
            roomsPw.put(roomId, roomPw);
            webrtcWS.getSession().getBasicRemote().sendText(new ObjectMapper().writeValueAsString(new WebrtcMessage(WebrtcMessage.TYPE_COMMAND_SUCCESS, userId, "create")));
            logger.info("用户:" + webrtcWS.getUserId() + "创建房间:" + roomId + "\n房间有: " + getRoomUsers(roomId));
            return true;
        } else {
            throw new Exception("房间已经存在");
        }
    }

    @Override
    public boolean userLeave(String roomId, WebrtcWS webrtcWS) {
        Set<WebrtcWS> room = rooms.get("roomId");
        room.remove(webrtcWS);
        return false;
    }

    @Override
    public String getRoomUsers(String roomId) {
        Set<WebrtcWS> users = rooms.get(roomId);
        StringBuffer stringBuffer = new StringBuffer();
        if (users.size() > 0) {
            for (WebrtcWS user : users) {
                stringBuffer.append(user.getUserId() + "\n");
            }
        }
        return stringBuffer.toString();
    }

}
