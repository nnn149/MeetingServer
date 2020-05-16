/**
 * FileName: WebrtcRoomService
 * Author:   10418
 * Date:     2020-05-16 19:40
 * Description: 房间管理
 * History:
 * <author>          <time>          <version>          <desc>
 * 楠楠(Nannan)
 */
package cn.nicenan.meeting.service;

import cn.nicenan.meeting.websocket.WebrtcWS;

/**
 * DESC〈一句话功能简述〉<br>
 * 〈房间管理〉
 *
 * @author 10418
 * @create 2020-05-16
 * @since 1.0.0
 */
public interface WebrtcRoomService {
    /**
     * 查询指定房间存在人数
     *
     * @param roomId 房间ID
     * @return 房间人数
     */
    int countOfUserInRoom(String roomId);

    /**
     * 踢出用户
     *
     * @param roomId   房间ID
     * @param webrtcWS websocket连接
     * @return 成功true，失败false
     */
    boolean kickUser(String roomId, WebrtcWS webrtcWS);

    /**
     * @param roomId   房间ID
     * @param webrtcWS websocket连接
     * @return 成功true，失败false
     */
    boolean userEnter(String roomId, WebrtcWS webrtcWS) throws Exception;

    /**
     * @param roomId   房间ID
     * @param webrtcWS websocket连接
     * @return 成功true，失败false
     */
    boolean userLeave(String roomId, WebrtcWS webrtcWS);


}
