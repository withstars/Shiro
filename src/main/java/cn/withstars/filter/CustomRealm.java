package cn.withstars.filter;

import cn.withstars.domain.Role;
import cn.withstars.domain.User;
import cn.withstars.service.impl.PermissionServiceImpl;
import cn.withstars.service.impl.RoleServiceImpl;
import cn.withstars.service.impl.UserServiceImpl;
import org.apache.log4j.Logger;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: withstars
 * Date: 2018-05-05
 * Time: 19:11
 * Mail: withstars@126.com
 */
public class CustomRealm extends AuthorizingRealm {

    private static final Logger logger = Logger.getLogger(CustomRealm.class);

    @Autowired
    public UserServiceImpl userService;
    @Autowired
    public RoleServiceImpl roleService;
    @Autowired
    public PermissionServiceImpl permissionService;
    /**
     * 授权
     * @param principalCollection
     * @return
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 获取身份信息
        String userName = (String) principalCollection.getPrimaryPrincipal();
        User user = new User();
        user.setUsername(userName);
        // 根据身份信息从数据库中查询权限数据
        List<String> permissionList = permissionService.getPermissions(user);
        Role role = roleService.getRoleByUser(user);
        String roleName = role.getName();
        // 将权限信息封闭为AuthorizationInfo
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        info.addStringPermissions(permissionList);
        info.addRole(roleName);
        logger.warn("user:"+userName+",role:"+roleName+", permissions:"+permissionList);
        return info;
    }

    /**
     * 认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    //根据Token获取认证信息
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        // 从 token 中获取用户身份信息
        String userName = (String) token.getPrincipal();
        String password = new String((char[])token.getCredentials());
        // 如果查询不到则返回 null
        User user = new User();
        user.setUsername(userName);
        boolean ifExist = userService.existUser(user);
        boolean ifLocked = userService.ifLocked(user);
        if (!ifExist) {
            throw new UnknownAccountException();
        }
        if (ifLocked){
            throw new LockedAccountException();
        }
        //获取从数据库查询出来的用户密码
        String realPassword = userService.getPasswd(user);
        // 获取salt2
        String salt2 = userService.getSalt(user);
        //返回认证信息由父类 AuthenticatingRealm 进行认证
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(userName,realPassword,ByteSource.Util.bytes(userName+salt2),this.getName());
        return info;
    }

}
