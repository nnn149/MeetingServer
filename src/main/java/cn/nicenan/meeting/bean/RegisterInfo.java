/**
 * FileName: RegisterInfo
 * Author:   10418
 * Date:     2020-05-15 16:09
 * Description: 注册信息类
 * History:
 * <author>          <time>          <version>          <desc>
 * 楠楠(Nannan)
 */
package cn.nicenan.meeting.bean;

/**
 * DESC〈一句话功能简述〉<br>
 * 〈注册信息类〉
 *
 * @author 10418
 * @create 2020-05-15
 * @since 1.0.0
 */
public class RegisterInfo {
    private String username;
    private String pass;
    private String checkPass;
    private String nickname;
    private String email;

    public RegisterInfo() {
    }

    public RegisterInfo(String username, String pass, String checkPass, String nickname, String email) {
        this.username = username;
        this.pass = pass;
        this.checkPass = checkPass;
        this.nickname = nickname;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getCheckPass() {
        return checkPass;
    }

    public void setCheckPass(String checkPass) {
        this.checkPass = checkPass;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "RegisterInfo{" +
                "username='" + username + '\'' +
                ", pass='" + pass + '\'' +
                ", checkPass='" + checkPass + '\'' +
                ", nickname='" + nickname + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
