package cn.nicenan.meeting.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.nicenan.meeting.model.Selection;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Nannan
 * @since 2019-12-06
 */
public interface SelectionService extends IService<Selection> {

    void deleteByMainIdsAndType(List<Long> mainIds, Long typeId);

    List<Long> associatedIdList(Long type, Long mainId);
}
