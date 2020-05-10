/**
 * FileName: ControllerJsonConfig
 * Author:   10418
 * Date:     2019-08-23 21:29
 * Description: 控制器返回的json配置
 * History:
 * <author>          <time>          <version>          <desc>
 * 楠楠(Nannan))
 */
package cn.nicenan.meeting.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * DESC〈一句话功能简述〉<br>
 * 〈控制器返回的json配置〉
 *
 * @author 10418
 * @create 2019-08-23
 * @since 1.0.0
 */
@Configuration
public class ControllerJsonConfig extends WebMvcConfigurationSupport {
    /**
     * springboot
     * 解决long、bigint转json丢失精度
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {

        List<MappingJackson2HttpMessageConverter> originalConverters = new ArrayList<>();

        MappingJackson2HttpMessageConverter jackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
        simpleModule.addSerializer(long.class, ToStringSerializer.instance);
//        simpleModule.addSerializer(Integer.class, ToStringSerializer.instance);
//        simpleModule.addSerializer(Integer.TYPE, ToStringSerializer.instance);
//        simpleModule.addSerializer(int.class, ToStringSerializer.instance);
        objectMapper.registerModule(simpleModule);
        jackson2HttpMessageConverter.setObjectMapper(objectMapper);
        converters.add(jackson2HttpMessageConverter);
    }
}
