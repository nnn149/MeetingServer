/**
 * FileName: LimitDto
 * Author:   10418
 * Date:     2019-08-22 0:10
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 楠楠(Nannan))
 */
package cn.nicenan.meeting.model.dto;

/**
 * DESC〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author 10418
 * @create 2019-08-22
 * @since 1.0.0
 */
public class LimitDto {
    private long page;
    private int limit;
    private String sort;
    private String[] sorts;

    public LimitDto() {
    }

    public LimitDto(long page, int limit) {
        this.page = page;
        this.limit = limit;
    }

    public LimitDto(long page, int limit, String sort) {
        this.page = page;
        this.limit = limit;
        this.sort = sort;
    }

    public LimitDto(long page, int limit, String[] sorts) {
        this.page = page;
        this.limit = limit;
        this.sorts = sorts;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public long getPage() {
        return page;
    }

    public void setPage(long page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String[] getSorts() {
        return sorts;
    }

    public void setSorts(String[] sorts) {
        this.sorts = sorts;
    }
}
