package cn.nicenan.meeting.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import cn.nicenan.meeting.bean.JsonResult;
import cn.nicenan.meeting.bean.ResultCode;
import cn.nicenan.meeting.model.Dictionary;
import cn.nicenan.meeting.model.dto.DictionaryListDto;
import cn.nicenan.meeting.model.dto.LimitDto;
import cn.nicenan.meeting.service.DictionaryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Nannan
 * @since 2019-08-18
 */
@Validated
@RestController
@RequestMapping("/dictionary")
public class DictionaryController {
    private final static Logger logger = LoggerFactory.getLogger(DictionaryController.class);
    @Autowired
    private DictionaryService dictionaryService;

    @PreAuthorize("hasAnyAuthority('admin')")
    @GetMapping(value = "/list")
    public JsonResult dictionaryList(
            @RequestParam(name = "page", value = "page", required = false, defaultValue = "1") @Min(1) long page,
            @RequestParam(name = "limit", value = "limit", required = false, defaultValue = "20") @Min(1) @Max(50) int limit,
            @RequestParam(name = "title", value = "title", required = false, defaultValue = "") String title,
            @RequestParam(name = "sort", value = "sort", required = false, defaultValue = "+sort") String sort,
            @RequestParam(name = "startTime", value = "startTime", required = false, defaultValue = "") String startTime,
            @RequestParam(name = "endTime", value = "endTime", required = false, defaultValue = "") String endTime,
            @RequestParam(name = "type", value = "type", required = false, defaultValue = "0") int type
            ) {

        return new JsonResult<>(
                dictionaryService.getLimitList(
                        new DictionaryListDto(
                                new LimitDto(page, limit, sort), title, startTime, endTime,type
                        )));
    }

    @PreAuthorize("hasAnyAuthority('admin')")
    @PostMapping(value = "/add")
    public JsonResult addDictionary(@RequestBody Dictionary dictionary) {
        if (dictionaryService.save(dictionary)) {
            return new JsonResult();
        } else {
            return new JsonResult(ResultCode.UNKNOWN_ERROR);
        }
    }

    @PreAuthorize("hasAnyAuthority('admin')")
    @PostMapping(value = "/update")
    public JsonResult updateDictionary(@RequestBody Dictionary dictionary) {
        if (dictionaryService.update(dictionary, new QueryWrapper<Dictionary>().lambda().eq(Dictionary::getId, dictionary.getId()))) {
            return new JsonResult();
        } else {
            return new JsonResult(ResultCode.UNKNOWN_ERROR);
        }
    }

    @PreAuthorize("hasAnyAuthority('admin')")
    @GetMapping(value = "/delete")
    public JsonResult deleteDictionary(@RequestParam(name = "id", value = "id", required = true) long id) {
        if (dictionaryService.removeById(id)) {
            return new JsonResult();
        } else {
            return new JsonResult(ResultCode.UNKNOWN_ERROR);
        }
    }

    @PreAuthorize("hasAnyAuthority('admin')")
    @PostMapping(value = "/deleteList")
    public JsonResult deleteDictionaryList(@RequestBody List<Integer> ids) {
        if (ids.size() > 0) {
            dictionaryService.removeByIds(ids);
            return new JsonResult();
        } else {
            return new JsonResult(ResultCode.UNKNOWN_ERROR);
        }
    }

    @PreAuthorize("hasAnyAuthority('vip','admin')")
    @GetMapping(value = "/baseList")
    public JsonResult getMap(@RequestParam(name = "type", value = "type", required = true) long type) {
        return new JsonResult<List<Dictionary>>(dictionaryService.getMinListByTypeId(type));
    }
}

