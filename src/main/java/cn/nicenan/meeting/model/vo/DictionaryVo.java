/**
 * FileName: DictionaryDto
 * Author:   10418
 * Date:     2019-08-21 20:00
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 楠楠(Nannan))
 */
package cn.nicenan.meeting.model.vo;


import cn.nicenan.meeting.model.Dictionary;

/**
 * DESC〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author 10418
 * @create 2019-08-21
 * @since 1.0.0
 */
public class DictionaryVo extends Dictionary {
    private String typeTitle;
    private String parentTitle;
    private String statusTitle;

    public String getTypeTitle() {
        return typeTitle;
    }

    public void setTypeTitle(String typeTitle) {
        this.typeTitle = typeTitle;
    }

    public String getParentTitle() {
        return parentTitle;
    }

    public void setParentTitle(String parentTitle) {
        this.parentTitle = parentTitle;
    }

    public String getStatusTitle() {
        return statusTitle;
    }

    public void setStatusTitle(String statusTitle) {
        this.statusTitle = statusTitle;
    }
}
