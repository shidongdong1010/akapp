package com.akcome.akapp.repository;

import com.akcome.akapp.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by dongdongshi on 16/1/12.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from User u where u.username=?1 and u.password=?2")
    User login(String username, String password);

    User findByUsernameAndPassword(String username, String password);

    User findUserByUsername(String username);
}
