package cn.nicenan.meeting.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.nicenan.meeting.model.DictionaryType;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Nannan
 * @since 2019-08-18
 */
public interface DictionaryTypeService extends IService<DictionaryType> {
    public List<DictionaryType> getMinList();
}
