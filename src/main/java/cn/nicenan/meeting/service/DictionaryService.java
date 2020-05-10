package cn.nicenan.meeting.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.nicenan.meeting.bean.PageBean;
import cn.nicenan.meeting.model.Dictionary;
import cn.nicenan.meeting.model.dto.DictionaryListDto;
import cn.nicenan.meeting.model.vo.DictionaryVo;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Nannan
 * @since 2019-08-18
 */
public interface DictionaryService extends IService<Dictionary> {
    public PageBean<List<DictionaryVo>> getLimitList(DictionaryListDto dictionaryListDto);

    public List<Dictionary> getMinListByTypeId(long type);

    List<Dictionary> getTitles(String[] ids);
}
