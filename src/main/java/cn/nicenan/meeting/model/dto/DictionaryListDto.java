/**
 * FileName: DictionaryListDto
 * Author:   10418
 * Date:     2019-08-22 0:09
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 楠楠(Nannan))
 */
package cn.nicenan.meeting.model.dto;

/**
 * DESC〈一句话功能简述〉<br>
 * 〈〉
 * Dictionary控制器和DictionaryService传递数据用
 *
 * @author 10418
 * @create 2019-08-22
 * @since 1.0.0
 */
public class DictionaryListDto {
    private LimitDto limitDto;
    private String title;
    private String startTime;
    private String endTime;
    private int type;

    public DictionaryListDto() {
    }

    public DictionaryListDto(LimitDto limitDto, String title, String startTime, String endTime, int type) {
        this.limitDto = limitDto;
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
        this.type = type;
    }

    public LimitDto getLimitDto() {
        return limitDto;
    }

    public void setLimitDto(LimitDto limitDto) {
        this.limitDto = limitDto;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
