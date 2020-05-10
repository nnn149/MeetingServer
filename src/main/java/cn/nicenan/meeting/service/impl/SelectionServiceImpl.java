package cn.nicenan.meeting.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.nicenan.meeting.mapper.SelectionMapper;
import cn.nicenan.meeting.model.Selection;
import cn.nicenan.meeting.service.SelectionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Nannan
 * @since 2019-12-06
 */
@Service
public class SelectionServiceImpl extends ServiceImpl<SelectionMapper, Selection> implements SelectionService {

    @Override
    public void deleteByMainIdsAndType(List<Long> mainIds, Long typeId) {
        int delete = baseMapper.delete(new UpdateWrapper<Selection>().lambda().and(n -> n.in(Selection::getMainId, mainIds).eq(Selection::getType, typeId)));
    }

    @Override
    public List<Long> associatedIdList(Long type, Long mainId) {
        List<Selection> selections = baseMapper.selectList(new QueryWrapper<Selection>().lambda().select(Selection::getAssociatedId).eq(Selection::getType, type).eq(Selection::getMainId, mainId));
        List<Long> collect = selections.stream().map(s -> s.getAssociatedId()).collect(Collectors.toList());
        return collect;
    }
}
