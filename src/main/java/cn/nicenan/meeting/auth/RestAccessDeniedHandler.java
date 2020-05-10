/**
 * FileName: RestAccessDeniedHandler
 * Author:   10418
 * Date:     2019-07-31 0:33
 * Description: 自定义权限不足处理类
 * History:
 * <author>          <time>          <version>          <desc>
 * 牛楠楠
 */
package cn.nicenan.meeting.auth;

import cn.nicenan.meeting.bean.JsonResult;
import cn.nicenan.meeting.bean.ResultCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 〈一句话功能简述〉<br>
 * 〈自定义权限不足处理类〉
 * * 权限校验处理器，权限不足信息返回给前端。
 *
 * @author 10418
 * @create 2019-07-31
 * @since 1.0.0
 */
@Component
public class RestAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT, GET");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Content-Length, Authorization, Accept, X-Requested-With");
        ServletOutputStream out = response.getOutputStream();
        String str = new ObjectMapper().writeValueAsString(new JsonResult(ResultCode.UNAUTHORIZED));
        out.write(str.getBytes(StandardCharsets.UTF_8));
        out.flush();
        out.close();
    }
}
