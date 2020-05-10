/**
 * FileName: JwtAuthController
 * Author:   10418
 * Date:     2019-07-31 11:36
 * Description: 登录注册
 * History:
 * <author>          <time>          <version>          <desc>
 * 牛楠楠
 */
package cn.nicenan.meeting.controller;

import cn.nicenan.meeting.bean.JsonResult;
import cn.nicenan.meeting.bean.LoginInfo;
import cn.nicenan.meeting.model.User;
import cn.nicenan.meeting.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 〈一句话功能简述〉<br>
 * 〈登录注册〉
 *
 * @author 10418
 * @create 2019-07-31
 * @since 1.0.0
 */
@RestController
public class JwtAuthController {
    @Autowired
    private AuthService authService;

    // 登录
    @RequestMapping(value = "/authentication/login", method = RequestMethod.POST)
    public JsonResult createToken(@RequestBody LoginInfo loginInfo) {
        // 登录成功会返回JWT Token给用户
        return authService.login(loginInfo.getUsername(), loginInfo.getPassword());
    }

    // 注册
    @RequestMapping(value = "/authentication/register", method = RequestMethod.POST)
    public JsonResult register(@RequestBody User addedUser) {

        return authService.register(addedUser);
    }

    // 登出
    @RequestMapping(value = "/authentication/logout", method = RequestMethod.POST)
    public JsonResult logout() {

        return new JsonResult();
    }

}
