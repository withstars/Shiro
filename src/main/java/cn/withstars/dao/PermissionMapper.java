package cn.withstars.dao;

import cn.withstars.domain.Permission;
import cn.withstars.domain.User;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: withstars
 * Date: 2018-05-13
 * Time: 8:38
 * Mail: withstars@126.com
 */
public interface PermissionMapper {

    List<Permission> getPermissions(User user);
}
