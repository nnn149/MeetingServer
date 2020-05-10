package cn.nicenan.meeting.mapper;

import cn.nicenan.meeting.model.Dictionary;
import cn.nicenan.meeting.model.dto.DictionaryListDto;
import cn.nicenan.meeting.model.vo.DictionaryVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author Nannan
 * @since 2019-08-18
 */
public interface DictionaryMapper extends BaseMapper<Dictionary> {
    List<DictionaryVo> pageListVo(Page page, @Param("dictionaryListDto") DictionaryListDto dictionaryListDto);
}
