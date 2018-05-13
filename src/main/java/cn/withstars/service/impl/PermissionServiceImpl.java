package cn.withstars.service.impl;

import cn.withstars.dao.PermissionMapper;
import cn.withstars.domain.Permission;
import cn.withstars.domain.User;
import cn.withstars.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: withstars
 * Date: 2018-05-13
 * Time: 10:01
 * Mail: withstars@126.com
 */
@Service
public class PermissionServiceImpl implements PermissionService{

    @Autowired
    public PermissionMapper permissionMapper;

    public List<String> getPermissions(User user) {
        List<Permission> permissions=permissionMapper.getPermissions(user);
        if (permissions != null){
            List<String> permissionsRes = new ArrayList<String>();
            Iterator iterator = permissions.iterator();
            while (iterator.hasNext()){
                Permission permission = (Permission) iterator.next();
                String permissionInfo = permission.getName();
                permissionsRes.add(permissionInfo);
            }
            return permissionsRes;
        }
        return null;
    }
}
