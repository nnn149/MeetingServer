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
    SUCCESS(200, "成功"),
    /**
     * 数据已存在
     */
    SUCCESS_IS_HAVE(208, "数据已存在"),
    /**
     * 系统错误
     */
    SYS_ERROR(510, "系统错误"),

    /**
     * 参数错误
     */
    PARAMS_ERROR(408, "参数错误 "),
    /**
     * 参数校验错误
     */
    VALIDATED_ERROR(405, "参数校验错误"),
    /**
     * 身份验证失败
     */
    INVALID_AUTH(400, "身份验证失败"),
    /**
     * 太频繁的调用
     */
    TOO_FREQUENT(445, "过于频繁调用"),

    /**
     * 权限不足
     */
    NO_AUTH(403, "权限不足"),
    /**
     * url不存在
     */
    NOT_FOUND(404, "访问地址不存在"),
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
