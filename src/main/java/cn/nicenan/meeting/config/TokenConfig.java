/**
 * FileName: TokenConfig
 * Author:   10418
 * Date:     2020-01-02 16:28
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 楠楠(Nannan))
 */
package cn.nicenan.meeting.config;

/**
 * DESC〈token配置〉<br>
 * 〈〉
 *
 * @author 10418
 * @create 2020-01-02
 * @since 1.0.0
 */
public class TokenConfig {
    public static final long EXPIRATION_TIME = 3600;     // 1小时(以秒计)
    public static final String SECRET = ":?E%'iFg3s.6R.Bh";      // JWT密码
    public static final String TOKEN_PREFIX = "Bearer";         // Token前缀
    public static final String HEADER_STRING = "Authorization"; // 存放Token的Header Key
}
