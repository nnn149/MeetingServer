/**
 * FileName: IdTitle
 * Author:   10418
 * Date:     2019-08-22 22:46
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 楠楠(Nannan))
 */
package cn.nicenan.meeting.bean;

/**
 * DESC〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author 10418
 * @create 2019-08-22
 * @since 1.0.0
 */
public class IdTitle {
    private long id;
    private String title;

    public IdTitle() {
    }

    public IdTitle(long id, String title) {
        this.id = id;
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
