package cn.withstars.service;

import cn.withstars.domain.Permission;
import cn.withstars.domain.User;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: withstars
 * Date: 2018-05-13
 * Time: 10:01
 * Mail: withstars@126.com
 */
public interface PermissionService {

    List<String> getPermissions(User user);
}
