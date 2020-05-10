/**
 * FileName: ErrorHandler
 * Author:   10418
 * Date:     2019-08-20 19:33
 * Description: 统一异常处理
 * History:
 * <author>          <time>          <version>          <desc>
 * 楠楠(Nannan))
 */
package cn.nicenan.meeting.exception;


import cn.nicenan.meeting.bean.JsonResult;
import cn.nicenan.meeting.bean.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

/**
 * DESC〈一句话功能简述〉<br>
 * 〈统一异常处理〉
 *
 * @author 10418
 * @create 2019-08-20
 * @since 1.0.0
 */
@ControllerAdvice
@ResponseBody
public class ErrorHandler {
    private final static Logger logger = LoggerFactory.getLogger(ErrorHandler.class);

    /**
     * 输入参数校验异常
     * 对使用{@code @Valid}注释的参数进行验证失败时抛出的异常。
     */
    @ExceptionHandler(value = {MethodArgumentNotValidException.class, ConstraintViolationException.class})
    public JsonResult notValidExceptionHandler(HttpServletRequest req, Exception e) throws Exception {
        return new JsonResult<>(ResultCode.BAD_REQUEST, e.getMessage());
    }

    /**
     * 404异常处理
     */
    @ExceptionHandler(value = NoHandlerFoundException.class)
    public JsonResult noHandlerFoundExceptionHandler(HttpServletRequest req, Exception e) throws Exception {
        return new JsonResult<>(ResultCode.NOT_FOUND);
    }

    /**
     * 权限不足异常
     */
    @ExceptionHandler(value = AccessDeniedException.class)
    public JsonResult accessDeniedExceptionHandler(HttpServletRequest req, Exception e) throws Exception {
        return new JsonResult<>(ResultCode.UNAUTHORIZED, e.getMessage());
    }

    /**
     * 认证异常
     */
    @ExceptionHandler(value = AuthenticationException.class)
    public JsonResult authenticationExceptionHandler(HttpServletRequest req, Exception e) throws Exception {
        return new JsonResult<>(ResultCode.USERNAME_PSW_ERROR, e.getMessage());
    }

    /**
     * 默认异常处理，前面未处理
     */
    @ExceptionHandler(value = Throwable.class)
    public JsonResult defaultHandler(HttpServletRequest req, Exception e) throws Exception {
        return new JsonResult<>(ResultCode.UNKNOWN_ERROR, e.getMessage());
    }
}
