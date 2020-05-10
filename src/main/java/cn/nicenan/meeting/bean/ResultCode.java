/**
 * FileName: ResultCode
 * Author:   10418
 * Date:     2019-08-07 20:42
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 牛楠楠
 */
package cn.nicenan.meeting.bean;

/**
 * 〈一句话功能简述〉<br>
 * 自定义状态码
 * 〈〉
 *
 * @author 10418
 * @create 2019-08-07
 * @since 1.0.0
 */
public enum ResultCode {
    /**
     * 成功
     */
    OK(200, "成功"),


    /**
     * 请求参数错误
     */
    BAD_REQUEST(400, "请求参数错误 "),

    /**
     * 未授权
     */
    UNAUTHORIZED(401, "未授权"),

    /**
     * url不存在
     */
    NOT_FOUND(404, "访问地址不存在"),

    /**
     * 太频繁的调用
     */
    TOO_FREQUENT(420, "过于频繁调用"),

    /**
     * 用户名或密码错误
     */
    USERNAME_PSW_ERROR(431, "用户名或密码错误"),

    /**
     * 令牌无效
     */
    TOKEN_ERROR(432, "令牌无效"),

    /**
     * 未知的错误
     */
    UNKNOWN_ERROR(500, "未知错误");

    private ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer code() {
        return code;
    }

    public String message() {
        return message;
    }

    private Integer code;
    private String message;
}
