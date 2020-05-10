package cn.nicenan.meeting.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.nicenan.meeting.bean.PageBean;
import cn.nicenan.meeting.mapper.UserMapper;
import cn.nicenan.meeting.model.Role;
import cn.nicenan.meeting.model.User;
import cn.nicenan.meeting.model.UserRole;
import cn.nicenan.meeting.model.dto.UserListDto;
import cn.nicenan.meeting.model.vo.UserVo;
import cn.nicenan.meeting.service.AuthService;
import cn.nicenan.meeting.service.RoleService;
import cn.nicenan.meeting.service.UserRoleService;
import cn.nicenan.meeting.service.UserService;
import cn.nicenan.meeting.util.PageUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    private final static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private AuthService authService;


    @Override
    public User getUserByUsername(String username) {
        User user = baseMapper.selectOne(new QueryWrapper<User>().eq("username", username));
        return user;
    }

    @Override
    public List<String> getRoleNameListById(Long userId) {
        List<UserRole> userRoles = userRoleService.list(new QueryWrapper<UserRole>().select("roleId").eq("userId", userId));
        List<Integer> ids = new ArrayList<>();
        for (UserRole userRole : userRoles) {
            ids.add(userRole.getRoleId());
        }
        Collection<Role> roles = roleService.listByIds(ids);
        List<String> roleNames = new ArrayList<>();
        for (Role r : roles) {
            roleNames.add(r.getName());
        }
        return roleNames;
    }

    @Override
    public PageBean<List<UserVo>> getListByDto(UserListDto userListDto) {
        Page<UserVo> userVoPage = PageUtil.generatePage(UserVo.class, userListDto.getLimit());
        List<UserVo> userVos = baseMapper.pageListVo(userVoPage, userListDto);
        for (UserVo userVo : userVos) {
            userVo.setRoleTitle("客服");
        }
        return new PageBean<>(userVoPage.getTotal(), userVos);
    }

    @Override
    public int deleteByIds(List<Long> ids) {
        int num = baseMapper.deleteBatchIds(ids);
        for (long id : ids) {
            userRoleService.remove(new QueryWrapper<UserRole>().lambda().eq(UserRole::getUserId, id));
        }
        return num;
    }

    @Override
    public boolean add(User user) {
        authService.register(user);
        userRoleService.save(new UserRole(user.getId(), 3));
        return true;
    }

    @Override
    public boolean updateOne(User user) {
        User byId = this.getById(user.getId());
        if (StringUtils.isNotEmpty(user.getPassword())) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            final String rawPassword = user.getPassword();
            user.setPassword(encoder.encode(rawPassword));
        } else {
            user.setPassword(byId.getPassword());
        }
        baseMapper.update(user, new UpdateWrapper<User>().lambda().eq(User::getId, user.getId()));
        return true;
    }
}
