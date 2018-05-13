package cn.withstars.service;

import cn.withstars.domain.Role;
import cn.withstars.domain.User;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: withstars
 * Date: 2018-05-13
 * Time: 9:10
 * Mail: withstars@126.com
 */
public interface RoleService {

    Role getRoleByUser(User user);
}
