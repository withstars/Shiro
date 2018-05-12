package cn.withstars.dao;

import cn.withstars.domain.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

public interface UserMapper {


    User selectByPrimaryKey(Integer id);


    User selectByUsername(String username);

    int addUser(User user);

    User existUser(User user);

    User getSalt(User user);

    User getPassword(User user);

    User getLockedInfo(User user);
}