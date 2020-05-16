/**
 * FileName: WebSocketConfig
 * Author:   10418
 * Date:     2020-05-16 17:53
 * Description: 启用ws支持
 * History:
 * <author>          <time>          <version>          <desc>
 * 楠楠(Nannan)
 */
package cn.nicenan.meeting.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * DESC〈一句话功能简述〉<br>
 * 〈启用ws支持〉
 *
 * @author 10418
 * @create 2020-05-16
 * @since 1.0.0
 */
@Configuration
public class WebSocketConfig {
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
