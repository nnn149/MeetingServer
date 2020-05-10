package cn.nicenan.meeting.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import cn.nicenan.meeting.auth.JwtUser;
import cn.nicenan.meeting.bean.JsonResult;
import cn.nicenan.meeting.bean.ResultCode;
import cn.nicenan.meeting.model.User;
import cn.nicenan.meeting.model.dto.UserListDto;
import cn.nicenan.meeting.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Nannan
 * @since 2019-08-18
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;


    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public JsonResult info() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.getUserByUsername(userDetails.getUsername());
        Map<String, Object> info = new HashMap<>(4);
        info.put("introduction", user.getRemark());
        info.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        info.put("name", user.getNickname());
        info.put("roles", userService.getRoleNameListById(user.getId()));
        return new JsonResult<>(info);
    }

    @PreAuthorize("hasAnyAuthority('admin')")
    @PostMapping(value = "/list")
    public JsonResult userList(@RequestBody UserListDto userListDto) {
        JwtUser user = (JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userListDto.setId(user.getUserId());
        return new JsonResult<>(userService.getListByDto(userListDto));
    }
    @PreAuthorize("hasAnyAuthority('admin')")
    @PostMapping(value = "/add")
    public JsonResult addUser(@RequestBody User user) throws JsonProcessingException {
        if (userService.add(user)) {
            return new JsonResult();
        } else {
            return new JsonResult(ResultCode.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasAnyAuthority('admin')")
    @PostMapping(value = "/update")
    public JsonResult updateUser(@RequestBody User user) {
        if (userService.updateOne(user)) {
            return new JsonResult();
        } else {
            return new JsonResult(ResultCode.UNKNOWN_ERROR);
        }
    }
    @PreAuthorize("hasAnyAuthority('admin')")
    @GetMapping(value = "/delete")
    public JsonResult deleteUser(@RequestParam(name = "id", value = "id", required = true) long id) {
        List<Long> ids = new ArrayList<>();
        ids.add(id);
        if (userService.deleteByIds(ids) > 0) {
            return new JsonResult();
        } else {
            return new JsonResult(ResultCode.UNKNOWN_ERROR);
        }
    }
    @PreAuthorize("hasAnyAuthority('admin')")
    @PostMapping(value = "/deleteList")
    public JsonResult deleteUserList(@RequestBody List<Long> ids) {
        if (userService.deleteByIds(ids) > 0) {
            return new JsonResult();
        } else {
            return new JsonResult(ResultCode.UNKNOWN_ERROR);
        }
    }
}

