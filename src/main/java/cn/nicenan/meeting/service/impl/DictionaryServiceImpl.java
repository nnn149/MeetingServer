package cn.nicenan.meeting.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.nicenan.meeting.bean.PageBean;
import cn.nicenan.meeting.mapper.DictionaryMapper;
import cn.nicenan.meeting.model.Dictionary;
import cn.nicenan.meeting.model.dto.DictionaryListDto;

import cn.nicenan.meeting.model.vo.DictionaryVo;
import cn.nicenan.meeting.service.DictionaryService;
import cn.nicenan.meeting.service.DictionaryTypeService;
import cn.nicenan.meeting.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
public class DictionaryServiceImpl extends ServiceImpl<DictionaryMapper, Dictionary> implements DictionaryService {


    @Autowired
    private DictionaryTypeService dictionaryTypeService;

    @Override
    public PageBean<List<DictionaryVo>> getLimitList(DictionaryListDto dictionaryListDto) {
        Page<DictionaryVo> dictionaryVoPage = PageUtil.generatePage(DictionaryVo.class, dictionaryListDto.getLimitDto());
        List<DictionaryVo> dictionaryVos = baseMapper.pageListVo(dictionaryVoPage, dictionaryListDto);
        return new PageBean<>(dictionaryVoPage.getTotal(), dictionaryVos);
    }

    @Override
    public List<Dictionary> getMinListByTypeId(long type) {
        return baseMapper.selectList(new QueryWrapper<Dictionary>().lambda().select(Dictionary::getId, Dictionary::getTitle).eq(Dictionary::getType, type));
    }

    @Override
    public List<Dictionary> getTitles(String[] ids) {
        List<Dictionary> dictionaries;
        if (ids == null || ids.length == 0) {
            dictionaries = new ArrayList<>();
        } else {
            dictionaries = baseMapper.selectList(new QueryWrapper<Dictionary>().lambda().select(Dictionary::getId, Dictionary::getTitle, Dictionary::getType).in(Dictionary::getId, ids));
        }
        return dictionaries;
    }
}
