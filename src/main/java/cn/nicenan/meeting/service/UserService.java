package cn.nicenan.meeting.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.nicenan.meeting.bean.PageBean;
import cn.nicenan.meeting.model.User;
import cn.nicenan.meeting.model.dto.UserListDto;
import cn.nicenan.meeting.model.vo.UserVo;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Nannan
 * @since 2019-08-18
 */
public interface UserService extends IService<User> {
    User getUserByUsername(String username);
    User getUserByEmail(String email);
    List<String> getRoleNameListById(Long userId);

    PageBean<List<UserVo>> getListByDto(UserListDto userListDto);
    int deleteByIds(List<Long> ids);
    boolean add(User user) throws Exception;
    boolean updateOne(User user);
}
