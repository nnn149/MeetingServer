/**
 * FileName: TokenConfig
 * Author:   10418
 * Date:     2019-07-31 20:36
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 牛楠楠
 */
package cn.nicenan.meeting.config;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author 10418
 * @create 2019-07-31
 * @since 1.0.0
 */
public class TokenConfig {
    public static final long EXPIRATION_TIME = 36000;     // 1小时(以秒计)
    public static final String SECRET = ":?D%'iFg0s.6R.Bh";      // JWT密码
    public static final String TOKEN_PREFIX = "Bearer";         // Token前缀
    public static final String HEADER_STRING = "Authorization"; // 存放Token的Header Key

}
