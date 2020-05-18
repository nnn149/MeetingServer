/**
 * FileName: HttpsConfig
 * Author:   10418
 * Date:     2020-05-18 21:26
 * Description: 配置启用本地https
 * History:
 * <author>          <time>          <version>          <desc>
 * 楠楠(Nannan)
 */
package cn.nicenan.meeting.config;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * DESC〈一句话功能简述〉<br>
 * 〈配置启用本地https 将http请求，自动转换成https请求〉
 *
 * @author 10418
 * @create 2020-05-18
 * @since 1.0.0
 */
@Configuration
public class HttpsConfig {

    @Bean
    public Connector connector(){
        Connector connector=new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setScheme("http");
        connector.setPort(80);
        connector.setSecure(false);
        connector.setRedirectPort(8443);
        return connector;
    }
    @Bean
    public TomcatServletWebServerFactory tomcatServletWebServerFactory(Connector connector){
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
            @Override
            protected void postProcessContext(Context context) {
                SecurityConstraint securityConstraint = new SecurityConstraint();
                securityConstraint.setUserConstraint("CONFIDENTIAL");
                SecurityCollection collection = new SecurityCollection();
                collection.addPattern("/*");
                securityConstraint.addCollection(collection);
                context.addConstraint(securityConstraint);
            }
        };
        tomcat.addAdditionalTomcatConnectors(connector);
        return tomcat;
    }

}
