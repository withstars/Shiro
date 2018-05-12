package cn.withstars.service;

import cn.withstars.domain.User;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: withstars
 * Date: 2018-05-10
 * Time: 21:35
 * Mail: withstars@126.com
 */
public interface UserService {



    boolean addUser(User user);

    boolean existUser(User user);

    String getSalt(User user);

    String getPasswd(User user);

    boolean ifLocked(User user);

}
