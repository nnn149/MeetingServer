/**
 * FileName: TestController
 * Author:   10418
 * Date:     2019-07-31 11:40
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 牛楠楠
 */
package cn.nicenan.meeting.controller;

import cn.nicenan.meeting.auth.JwtUser;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author 10418
 * @create 2019-07-31
 * @since 1.0.0
 */
@RestController
public class TestController {
    // 测试普通权限 hasAnyAuthority([auth1,auth2])
    @PreAuthorize("hasAnyAuthority('user','admin')")
    @RequestMapping( value="/normal/test", method = RequestMethod.GET )
    public String test1()
    {
        JwtUser user = (JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(user.getUserId());
        return "ROLE_NORMAL /normal/test接口调用成功！";
    }

    // 测试管理员权限
    @PreAuthorize("hasAuthority('admin')")

    @RequestMapping( value = "/admin/test", method = RequestMethod.GET )
    public String test2() {
        return "ROLE_ADMIN /admin/test接口调用成功！";
    }
}
