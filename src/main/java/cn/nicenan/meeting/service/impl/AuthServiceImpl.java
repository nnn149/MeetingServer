/**
 * FileName: AuthServiceImpl
 * Author:   10418
 * Date:     2019-07-31 11:17
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 牛楠楠
 */
package cn.nicenan.meeting.service.impl;

import cn.nicenan.meeting.bean.JsonResult;
import cn.nicenan.meeting.bean.ResultCode;
import cn.nicenan.meeting.model.User;
import cn.nicenan.meeting.service.AuthService;
import cn.nicenan.meeting.service.UserService;
import cn.nicenan.meeting.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author 10418
 * @create 2019-07-31
 * @since 1.0.0
 */
@Service
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserService userService;

    public AuthServiceImpl(AuthenticationManager authenticationManager, @Qualifier("jwtUserDetailsServiceImpl") UserDetailsService userDetailsService, JwtTokenUtil jwtTokenUtil, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userService = userService;
    }

    @Override
    public JsonResult register(User user) {
        try {
            userService.add(user);
        } catch (Exception exception) {
            return new JsonResult(ResultCode.UNKNOWN_ERROR).setMessage(exception.getMessage());
        }
        return new JsonResult();
    }

    @Override
    public JsonResult login(String username, String password) {
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);
        final Authentication authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        final String token = jwtTokenUtil.generateToken(userDetails);
        Map<String, Object> map = new HashMap<>(1);
        map.put("token", token);
        return new JsonResult<>(map);
    }
}
