/**
 * FileName: UserVo
 * Author:   10418
 * Date:     2019-12-05 15:26
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 楠楠(Nannan))
 */
package cn.nicenan.meeting.model.vo;

import cn.nicenan.meeting.model.User;

/**
 * DESC〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author 10418
 * @create 2019-12-05
 * @since 1.0.0
 */
public class UserVo extends User {
    private String roleTitle;

    public String getRoleTitle() {
        return roleTitle;
    }

    public void setRoleTitle(String roleTitle) {
        this.roleTitle = roleTitle;
    }


}
