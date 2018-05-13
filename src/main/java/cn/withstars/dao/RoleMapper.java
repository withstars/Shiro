package cn.withstars.dao;

import cn.withstars.domain.Role;
import cn.withstars.domain.User;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: withstars
 * Date: 2018-05-13
 * Time: 8:38
 * Mail: withstars@126.com
 */
public interface RoleMapper {

    Role getRoleByUser(User user);
}
