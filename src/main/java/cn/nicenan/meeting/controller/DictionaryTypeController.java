package cn.nicenan.meeting.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.nicenan.meeting.bean.JsonResult;
import cn.nicenan.meeting.bean.PageBean;
import cn.nicenan.meeting.bean.ResultCode;
import cn.nicenan.meeting.model.DictionaryType;
import cn.nicenan.meeting.service.DictionaryTypeService;
import cn.nicenan.meeting.util.PageUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Nannan
 * @since 2019-08-18
 */
@RestController
@RequestMapping("/dictionaryType")
public class DictionaryTypeController {
    private final static Logger logger = LoggerFactory.getLogger(DictionaryTypeController.class);
    @Autowired
    private DictionaryTypeService dictionaryTypeService;

    @PreAuthorize("hasAnyAuthority('admin')")
    @GetMapping(value = "/list")
    public JsonResult dictionaryTypeList(
            @RequestParam(name = "page", value = "page", required = false, defaultValue = "1") long page,
            @RequestParam(name = "limit", value = "limit", required = false, defaultValue = "20") int limit,
            @RequestParam(name = "title", value = "title", required = false, defaultValue = "") String title,
            @RequestParam(name = "sort", value = "sort", required = false, defaultValue = "+sort") String sort,
            @RequestParam(name = "startTime", value = "startTime", required = false, defaultValue = "") String startTime,
            @RequestParam(name = "endTime", value = "endTime", required = false, defaultValue = "") String endTime) {
        Page<DictionaryType> pagePg = PageUtil.generatePage(DictionaryType.class, page, limit, new String[]{sort});
        LambdaQueryWrapper<DictionaryType> queryWrapper = new QueryWrapper<DictionaryType>().lambda();
        if (StringUtils.isNotBlank(title)) {
            queryWrapper.like(DictionaryType::getTitle, title);
        }
        if (StringUtils.isNotBlank(startTime)) {
            queryWrapper.ge(DictionaryType::getUpdateTime, startTime);
        }
        if (StringUtils.isNotBlank(endTime)) {
            queryWrapper.le(DictionaryType::getUpdateTime, endTime);
        }
        queryWrapper.ne(DictionaryType::getId, 1);
        IPage<DictionaryType> pageResult = dictionaryTypeService.page(pagePg, queryWrapper);
        PageBean<List<DictionaryType>> pageBean = new PageBean<>(pageResult.getTotal(), pageResult.getRecords());
        return new JsonResult<>(pageBean);
    }

    @PreAuthorize("hasAnyAuthority('admin')")
    @PostMapping(value = "/add")
    public JsonResult addDictionaryType(@RequestBody DictionaryType dictionaryType) {
        if (dictionaryTypeService.save(dictionaryType)) {
            return new JsonResult();
        } else {
            return new JsonResult(ResultCode.UNKNOWN_ERROR);
        }
    }

    @PreAuthorize("hasAnyAuthority('admin')")
    @PostMapping(value = "/update")
    public JsonResult updateDictionaryType(@RequestBody DictionaryType dictionaryType) {
        if (dictionaryTypeService.update(dictionaryType, new QueryWrapper<DictionaryType>().lambda().eq(DictionaryType::getId, dictionaryType.getId()))) {
            return new JsonResult();
        } else {
            return new JsonResult(ResultCode.UNKNOWN_ERROR);
        }
    }

    @PreAuthorize("hasAnyAuthority('admin')")
    @GetMapping(value = "/delete")
    public JsonResult deleteDictionaryType(@RequestParam(name = "id", value = "id", required = true) long id) {
        if (dictionaryTypeService.removeById(id)) {
            return new JsonResult();
        } else {
            return new JsonResult(ResultCode.UNKNOWN_ERROR);
        }
    }

    @PreAuthorize("hasAnyAuthority('admin')")
    @PostMapping(value = "/deleteList")
    public JsonResult deleteDictionaryTypeList(@RequestBody List<Integer> ids) {
        if (ids.size() > 0) {
            dictionaryTypeService.removeByIds(ids);
            return new JsonResult();
        } else {
            return new JsonResult(ResultCode.UNKNOWN_ERROR);
        }
    }
    @PreAuthorize("hasAnyAuthority('admin')")
    @GetMapping(value = "/baseList")
    public JsonResult getMap() {
        return new JsonResult<List<DictionaryType>>(dictionaryTypeService.getMinList());
    }
}

