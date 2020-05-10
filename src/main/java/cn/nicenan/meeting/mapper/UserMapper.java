package cn.nicenan.meeting.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.nicenan.meeting.model.User;
import cn.nicenan.meeting.model.dto.UserListDto;
import cn.nicenan.meeting.model.vo.UserVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Nannan
 * @since 2019-08-18
 */
public interface UserMapper extends BaseMapper<User> {
    List<UserVo> pageListVo(Page page, @Param("userListDto") UserListDto userListDto);

}
