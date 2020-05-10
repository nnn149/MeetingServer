/**
 * FileName: MybatisPlusConfig
 * Author:   10418
 * Date:     2019-08-20 11:21
 * Description: mybatis plus配置
 * History:
 * <author>          <time>          <version>          <desc>
 * 楠楠(Nannan))
 */
package cn.nicenan.meeting.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * DESC〈一句话功能简述〉<br>
 * 〈mybatis plus配置〉
 *
 * @author 10418
 * @create 2019-08-20
 * @since 1.0.0
 */
//指定要扫描的mybatis映射类的路径

@EnableTransactionManagement
@Configuration
public class MybatisPlusConfig {
    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        // paginationInterceptor.setLimit(你的最大单页限制数量，默认 500 条，小于 0 如 -1 不受限制);
        paginationInterceptor.setLimit(50);
        return paginationInterceptor;
    }
}
