/**
 * FileName: JwtTokenFilter
 * Author:   10418
 * Date:     2019-07-31 20:40
 * Description: 创建Token过滤器，用于每次外部对接口请求时的Token处理
 * History:
 * <author>          <time>          <version>          <desc>
 * 牛楠楠
 */
package cn.nicenan.meeting.auth;


import cn.nicenan.meeting.config.TokenConfig;
import cn.nicenan.meeting.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 〈一句话功能简述〉<br>
 * 〈创建Token过滤器，用于每次外部对接口请求时的Token处理 〉
 *
 * @author 10418
 * @create 2019-07-31
 * @since 1.0.0
 */
@Component
public class JwtTokenFilter extends OncePerRequestFilter {
    @Qualifier("jwtUserDetailsServiceImpl")
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String authHeader = request.getHeader(TokenConfig.HEADER_STRING);
        if (authHeader != null && authHeader.startsWith(TokenConfig.TOKEN_PREFIX)) {
            final String authToken = authHeader.substring(TokenConfig.TOKEN_PREFIX.length());
            String username = jwtTokenUtil.getUsernameFromToken(authToken);
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                JwtUser userDetails = new JwtUser(jwtTokenUtil.getUserIdFromToken(authToken), username, "", jwtTokenUtil.getRolesFromToken(authToken));
                if (jwtTokenUtil.validateToken(authToken, userDetails)) {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(
                            request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        chain.doFilter(request, response);
    }
}
