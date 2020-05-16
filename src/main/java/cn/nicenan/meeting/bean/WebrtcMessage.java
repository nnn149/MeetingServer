/**
 * FileName: WebrtcMessage
 * Author:   10418
 * Date:     2020-05-16 18:09
 * Description: ws接受webrtc消息类
 * History:
 * <author>          <time>          <version>          <desc>
 * 楠楠(Nannan)
 */
package cn.nicenan.meeting.bean;

/**
 * DESC〈一句话功能简述〉<br>
 * 〈ws接受webrtc消息类〉
 *
 * @author 10418
 * @create 2020-05-16
 * @since 1.0.0
 */
public class WebrtcMessage {
    public static final String TYPE_COMMAND_ROOM_ENTER = "enterRoom";
    public static final String TYPE_COMMAND_ROOM_LIST = "roomList";
    public static final String TYPE_COMMAND_DIALOGUE = "dialogue";
    public static final String TYPE_COMMAND_READY = "ready";
    public static final String TYPE_COMMAND_OFFER = "offer";
    public static final String TYPE_COMMAND_ANSWER = "answer";
    public static final String TYPE_COMMAND_CANDIDATE = "candidate";

    private String command;
    private String userId;
    private String roomId;
    private String message;

    public WebrtcMessage() {

    }

    @Override
    public String toString() {
        return "WebrtcMessage{" +
                "command='" + command + '\'' +
                ", userId='" + userId + '\'' +
                ", roomId='" + roomId + '\'' +
                ", message='" + message + '\'' +
                '}';
    }

    public WebrtcMessage(String command, String userId, String roomId, String message) {
        this.command = command;
        this.userId = userId;
        this.roomId = roomId;
        this.message = message;
    }
}
