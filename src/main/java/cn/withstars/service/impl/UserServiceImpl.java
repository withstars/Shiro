package cn.withstars.service.impl;

import cn.withstars.domain.User;
import cn.withstars.service.UserService;
import cn.withstars.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: withstars
 * Date: 2018-05-10
 * Time: 21:38
 * Mail: withstars@126.com
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    public UserMapper userMapper;

    public boolean addUser(User user) {
        int addSucc=userMapper.addUser(user);
        boolean ifAddSucc;
        if (addSucc == 1){
            ifAddSucc = true;
        }else {
            ifAddSucc = false;
        }
        return ifAddSucc;
    }

    public boolean existUser(User user) {
        User existUser = userMapper.existUser(user);
        boolean ifExist;
        if (existUser != null){
            ifExist = true;
        }else {
            ifExist = false;
        }
        return ifExist;
    }

    public String getSalt(User user) {
        User user1=userMapper.getSalt(user);
        String salt2;
        if (user1 != null){
            salt2 = user1.getSalt2();
        }else {
            salt2 = "";
        }
        return salt2;
    }

    public String getPasswd(User user) {
        User userRes = userMapper.getPassword(user);
        String password;
        if (userRes != null){
            password = userRes.getPassword();
        }else {
            password = "";
        }
        return password;
    }

    /**
     * 账号是否被锁定
     * @param user
     * @return true 锁定 fasle 未锁定
     */
    public boolean ifLocked(User user) {
        User userInfo=userMapper.getLockedInfo(user);
        Boolean locked = false;
        if (userInfo != null){
            Byte status=userInfo.getStatus();
            if (status == 1 ){
                locked = true;
            }
        }
        return locked;
    }
}
