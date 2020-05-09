/**
 * FileName: PageBean
 * Author:   10418
 * Date:     2019-08-19 16:01
 * Description: 分页结果
 * History:
 * <author>          <time>          <version>          <desc>
 * 楠楠(Nannan))
 */
package cn.nicenan.meeting.bean;

/**
 * DESC〈一句话功能简述〉<br>
 * 〈分页结果〉
 *
 * @author 10418
 * @create 2019-08-19
 * @since 1.0.0
 */
public class PageBean<T> {
    private long total;
    private T items;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public T getItems() {
        return items;
    }

    public void setItems(T items) {
        this.items = items;
    }

    public PageBean() {
    }

    public PageBean(long total, T items) {
        this.total = total;
        this.items = items;
    }
}
