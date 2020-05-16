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

import cn.nicenan.meeting.service.WebrtcRoomService;
import cn.nicenan.meeting.websocket.WebrtcWS;

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
public class WebrtcRoomServiceImpl implements WebrtcRoomService {

    private Map<String, Set<WebrtcWS>> rooms = new ConcurrentHashMap<>();

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
        return false;
    }

    @Override
    public boolean userEnter(String roomId, WebrtcWS webrtcWS) throws Exception {
        Set<WebrtcWS> room =rooms.get("roomId");
        if (room == null) {
            Set<WebrtcWS> synSet = Collections.synchronizedSet(new HashSet<>());
            synSet.add(webrtcWS);
            rooms.put(roomId, synSet);
        }
        room.add(webrtcWS);
        return true;
    }

    @Override
    public boolean userLeave(String roomId, WebrtcWS webrtcWS) {
        Set<WebrtcWS> room =rooms.get("roomId");
        room.remove(webrtcWS);
        return false;
    }
}
