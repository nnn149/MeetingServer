/**
 * FileName: LoginInfo
 * Author:   10418
 * Date:     2019-08-05 17:41
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 牛楠楠
 */
package cn.nicenan.meeting.bean;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author 10418
 * @create 2019-08-05
 * @since 1.0.0
 */
public class LoginInfo {
    private String username;
    private String password;
    private String verificationCode;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    @Override
    public String toString() {
        return "LoginInfo{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", verificationCode='" + verificationCode + '\'' +
                '}';
    }
}
