package cn.withstars.service.impl;

import cn.withstars.dao.RoleMapper;
import cn.withstars.domain.Role;
import cn.withstars.domain.User;
import cn.withstars.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: withstars
 * Date: 2018-05-13
 * Time: 9:10
 * Mail: withstars@126.com
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    public RoleMapper roleMapper;

    public Role getRoleByUser(User user) {
        return roleMapper.getRoleByUser(user);
    }
}
