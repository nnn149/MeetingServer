/**
 * FileName: UserListDto
 * Author:   10418
 * Date:     2019-12-05 15:18
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 楠楠(Nannan))
 */
package cn.nicenan.meeting.model.dto;

import cn.nicenan.meeting.model.User;

/**
 * DESC〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author 10418
 * @create 2019-12-05
 * @since 1.0.0
 */
public class UserListDto extends User {
    private LimitDto limit;

    public LimitDto getLimit() {
        return limit;
    }

    public void setLimit(LimitDto limit) {
        this.limit = limit;
    }
}
