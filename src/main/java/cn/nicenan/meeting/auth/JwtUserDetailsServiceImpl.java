/**
 * FileName: JwtUserDetailsServiceImpl
 * Author:   10418
 * Date:     2019-07-30 23:57
 * Description: Spring Security 进行身份验证的时候会使用.得到当前登录用户的一些用户名、密码、用户所拥有的角色等等一些信息。
 * History:
 * <author>          <time>          <version>          <desc>
 * 牛楠楠
 */
package cn.nicenan.meeting.auth;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 〈一句话功能简述〉<br>
 * 〈Spring Security 进行身份验证的时候会使用.得到当前登录用户的一些用户名、密码、用户所拥有的角色等等一些信息。〉
 *
 * @author 10418
 * @create 2019-07-30
 * @since 1.0.0
 */
@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

//    @Autowired
//    private UserService userService;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userService.getUserByUsername(username);
//        if (user == null) {
//            throw new UsernameNotFoundException(String.format("%s.这个用户不存在", username));
//        }
//        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
//        List<String> roleNames= userService.getRoleNameListById(user.getId());
//        for (String roleName : roleNames) {
//            authorities.add(new SimpleGrantedAuthority(roleName));
//        }
//        return new JwtUser(user.getId(), user.getUsername(), user.getPassword(), authorities);
        return null;
    }
}
