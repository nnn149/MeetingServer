/**
 * FileName: AuthService
 * Author:   10418
 * Date:     2019-07-31 11:15
 * Description: 登陆注册
 * History:
 * <author>          <time>          <version>          <desc>
 * 牛楠楠
 */
package cn.nicenan.meeting.service;

import cn.nicenan.meeting.bean.JsonResult;
import cn.nicenan.meeting.model.User;

/**
 * 〈一句话功能简述〉<br>
 * 〈登陆注册〉
 *
 * @author 10418
 * @create 2019-07-31
 * @since 1.0.0
 */
public interface AuthService {
    JsonResult register(User user);

    JsonResult login(String username, String password);
}
