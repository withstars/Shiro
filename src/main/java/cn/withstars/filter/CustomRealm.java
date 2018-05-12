package cn.withstars.filter;

import cn.withstars.domain.User;
import cn.withstars.service.impl.UserServiceImpl;
import org.apache.log4j.Logger;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
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
    /**
     * 授权
     * @param principalCollection
     * @return
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 获取身份信息
        String userName = (String) principalCollection.getPrimaryPrincipal();
        // 根据身份信息从数据库中查询权限数据
        // 这里使用静态数据模拟
        List<String> permissionList=new ArrayList<String>();
        permissionList.add("user:add");
        permissionList.add("user:delete");
        if (userName.equals("zhou")) {
            permissionList.add("user:query");
        }
        // 将权限信息封闭为AuthorizationInfo
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();

        info.addStringPermissions(permissionList);
        // 模拟数据，添加 manager 角色
        info.addRole("admin");
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
        //返回认证信息由父类 AuthenticatingRealm 进行认证
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(userName,realPassword,this.getName());
        return info;
    }
}
