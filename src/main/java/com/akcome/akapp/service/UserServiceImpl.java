package com.akcome.akapp.service;

import com.akcome.akapp.domain.User;
import com.akcome.akapp.domain.enums.YesOrNoEnum;
import com.akcome.akapp.repository.UserRepository;
import org.epbcommons.transformation.date.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 *
 * Created by dongdongshi on 16/1/12.
 */
@Service
public class UserServiceImpl implements UserService {

    // 登陆最多错误次数，默认为5
    @Value("${login.error.max.nums}")
    private int loginErrorMaxNums = 5;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User create(User user) {
        user.setLoginErrorCount(0);
        return userRepository.save(user);
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public User login(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }

    @Override
    public User update(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.delete(id);
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    /**
     * 得到登陆剩余次数
     * @param user
     * @return
     */
    @Override
    public int getLoginSurplusNums(User user){
        String lastLoginTime = DateUtil.getDate(user.getLastLoginTime());
        String currentDate = DateUtil.getDate(new Date());
        if(currentDate.equals(currentDate)) {
            return loginErrorMaxNums - user.getLoginErrorCount();
        }
        return 0;
    }

    /**
     * 重置登陆错误的次数为0
     * @param username
     */
    @Override
    public void resetFailNums(String username){
        User user = this.findUserByUsername(username);
        if(user != null && user.getId() != null) {
            user.setLoginErrorCount(0);
            user.setLastLoginTime(new Date());
            update(user);
        }
    }

    /**
     * 更新登陆错误的次数+1
     * @param username
     */
    @Override
    public User updateFailNums(String username) {
        User user = this.findUserByUsername(username);
        if(user != null && user.getId() != null) {
            int errorCount = user.getLoginErrorCount() == null ? 0 : user.getLoginErrorCount() ;
            user.setLoginErrorCount(errorCount + 1);
            user.setLastLoginTime(new Date());

            if(user.getLoginErrorCount() >= loginErrorMaxNums){
                user.setLocked(YesOrNoEnum.Y.getCode());
            }
            update(user);
        }
        return user;
    }
}
