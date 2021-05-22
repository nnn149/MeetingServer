/**
 * FileName: WebSocketController
 * Author:   10418
 * Date:     2020-05-16 18:19
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 楠楠(Nannan)
 */
package cn.nicenan.meeting.controller;

import cn.nicenan.meeting.bean.JsonResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * DESC〈一句话功能简述〉<br>
 * 〈返回ws url〉
 *
 * @author 10418
 * @create 2020-05-16
 * @since 1.0.0
 */
@RestController
@RequestMapping("/WebrtcWs")
public class WebrtcWsController {


    @Value("${server.port}")
    private Integer port;

    @GetMapping("/url")
    public JsonResult getIpAddress() {
        //http用ws,https用wss,ws可以用ip访问，wss的话，用ip会报证书错误，需要用域名访问,
        // return new JsonResult("ws:192.168.2.108:" + port + "/ws/webrtc");
        return new JsonResult("wss:192.168.2.200:" + port + "/ws/webrtc");
    }
}
