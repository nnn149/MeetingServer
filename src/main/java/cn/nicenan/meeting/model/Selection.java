package cn.nicenan.meeting.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author Nannan
 * @since 2019-12-06
 */
public class Selection implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long type;

    private Long mainId;

    private Long associatedId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public Long getMainId() {
        return mainId;
    }

    public void setMainId(Long mainId) {
        this.mainId = mainId;
    }

    public Long getAssociatedId() {
        return associatedId;
    }

    public void setAssociatedId(Long associatedId) {
        this.associatedId = associatedId;
    }

    public Selection() {
    }

    public Selection( Long type, Long mainId, Long associatedId) {
        this.id = id;
        this.type = type;
        this.mainId = mainId;
        this.associatedId = associatedId;
    }

    @Override
    public String toString() {
        return "Selection{" +
        "id=" + id +
        ", type=" + type +
        ", mainId=" + mainId +
        ", associatedId=" + associatedId +
        "}";
    }
}
