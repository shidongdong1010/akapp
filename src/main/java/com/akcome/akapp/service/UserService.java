package com.akcome.akapp.service;

import com.akcome.akapp.domain.User;

import java.util.List;

/**
 * 用户Service
 * Created by dongdongshi on 16/1/15.
 */
public interface UserService {

    List<User> findAll();

    User create(User user);

    User findUserById(Long id);

    User login(String username, String password);

    User update(User user);

    void deleteUser(Long id);

    User findUserByUsername(String username);

    /**
     * 得到登陆剩余次数
     * @param user
     * @return
     */
    int getLoginSurplusNums(User user);

    /**
     * 重置登陆错误的次数为0
     * @param username
     */
    void resetFailNums(String username);

    /**
     * 更新登陆错误的次数+1
     * @param username
     */
    User updateFailNums(String username);
}
