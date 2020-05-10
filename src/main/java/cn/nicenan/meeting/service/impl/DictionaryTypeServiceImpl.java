package cn.nicenan.meeting.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.nicenan.meeting.mapper.DictionaryTypeMapper;
import cn.nicenan.meeting.model.DictionaryType;
import cn.nicenan.meeting.service.DictionaryTypeService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Nannan
 * @since 2019-08-18
 */
@Service
public class DictionaryTypeServiceImpl extends ServiceImpl<DictionaryTypeMapper, DictionaryType> implements DictionaryTypeService {

    @Override
    public List<DictionaryType> getMinList() {
        return baseMapper.selectList(new QueryWrapper<DictionaryType>().lambda().select(DictionaryType::getId, DictionaryType::getTitle));
    }
}
