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
import cn.nicenan.meeting.model.User;
import cn.nicenan.meeting.service.UserService;
import cn.nicenan.meeting.service.WebrtcRoomService;
import cn.nicenan.meeting.util.JwtTokenUtil;
import cn.nicenan.meeting.websocket.WebrtcWS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;
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
    private Map<String, Map<String, WebrtcWS>> rooms = new ConcurrentHashMap<>();
    private Map<String, String> roomsPw = new ConcurrentHashMap<>();
    @Autowired
    private UserService userService;

    @Override
    public int countOfUserInRoom(String roomId) {
        Map<String, WebrtcWS> room = rooms.get(roomId);
        if (room == null) {
            return 0;
        } else {
            return room.size();
        }
    }

    @Override
    public boolean kickUser(String roomId, String userId) {
        try {
            Map<String, WebrtcWS> room = rooms.get(roomId);
            if (room.size() > 0) {
                room.remove(userId);
                logger.info("用户:" + userId + "被移除,房间:" + roomId + "\n房间内现有用户: " + getRoomUsers(roomId));
            }
            if (room.size() == 0) {
                rooms.remove(roomId);
                logger.info("房间:" + roomId + " 没有人，房间被移除");
            }
        } catch (Exception exception) {
            logger.info("无法提出用户" + exception.getMessage());
        }
        return true;
    }

    @Override
    public boolean enterRoom(String roomId, String roomPw, String token, WebrtcWS webrtcWS, String nickname) throws Exception {
        String userId = String.valueOf(new JwtTokenUtil().getUserIdFromToken(token));
        if (userId.equals("0")) {
            throw new Exception("身份验证失败");
        } else {
            webrtcWS.setUserId(userId);
        }
        Map<String, WebrtcWS> room = rooms.get(roomId);
        if (room == null) {
            throw new Exception("房间不存在");
        } else {
            if (roomsPw.get(roomId).equals(roomPw)) {
                userService.update(new User(Long.parseLong(userId), nickname), new QueryWrapper<User>().lambda().eq(User::getId, userId));
                webrtcWS.setRoomId(roomId);
                room.put(userId, webrtcWS);
                webrtcWS.getSession().getBasicRemote().sendText(new ObjectMapper().writeValueAsString(new WebrtcMessage(WebrtcMessage.TYPE_COMMAND_SUCCESS, userId, roomId, "enter")));
                logger.info("用户:" + webrtcWS.getUserId() + "进入房间:" + roomId + "\n房间有: " + getRoomUsers(roomId));
                return true;
            } else {
                throw new Exception("密码错误");
            }
        }
    }

    @Override
    public boolean createRoom(String roomId, String roomPw, String token, WebrtcWS webrtcWS, String nickname) throws Exception {
        String userId = String.valueOf(new JwtTokenUtil().getUserIdFromToken(token));
        if (userId.equals("0")) {
            throw new Exception("身份验证失败");
        } else {
            webrtcWS.setUserId(userId);
        }
        Map<String, WebrtcWS> room = rooms.get(roomId);
        if (room == null) {
            userService.update(new User(Long.parseLong(userId), nickname), new QueryWrapper<User>().lambda().eq(User::getId, userId));
            Map<String, WebrtcWS> newRoom = new ConcurrentHashMap<>();
            //把自己创建人添加到房间
            webrtcWS.setRoomId(roomId);
            newRoom.put(userId, webrtcWS);
            //把房间加到房间map
            rooms.put(roomId, newRoom);
            //设置房间密码
            roomsPw.put(roomId, roomPw);
            webrtcWS.getSession().getBasicRemote().sendText(new ObjectMapper().writeValueAsString(new WebrtcMessage(WebrtcMessage.TYPE_COMMAND_SUCCESS, userId, roomId, "create")));
            logger.info("用户:" + webrtcWS.getUserId() + "创建房间:" + roomId + "\n房间有: " + getRoomUsers(roomId));
            return true;
        } else {
            throw new Exception("房间已经存在");
        }
    }

    @Override
    public boolean userLeave(String roomId, String userId) {
        Map<String, WebrtcWS> room = rooms.get("roomId");
        room.remove(userId);
        return false;
    }

    @Override
    public String getRoomUsers(String roomId) {
        Map<String, WebrtcWS> room = rooms.get(roomId);
        StringBuffer stringBuffer = new StringBuffer();
        if (room.size() > 0) {
            room.forEach((k, v) -> stringBuffer.append(k + "\n"));
        }
        return stringBuffer.toString();
    }

    @Override
    public boolean forwardToEveryoneInRoom(String roomId, WebrtcMessage message, String excludeUserId) {
        Map<String, WebrtcWS> room = rooms.get(roomId);
        try {
            final String msg = new ObjectMapper().writeValueAsString(message);
            room.forEach((k, v) -> {
                if (!k.equals(excludeUserId)) {
                    try {
                        v.getSession().getBasicRemote().sendText(msg);
                        logger.info("转发:" + msg);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean forwardToOneInRoom(String roomId, WebrtcMessage message, String userId) {
        try {
            String msg = new ObjectMapper().writeValueAsString(message);
            rooms.get(roomId).get(userId).getSession().getBasicRemote().sendText(msg);
            logger.info("转发到:" + userId + " ,内容: " + msg);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
