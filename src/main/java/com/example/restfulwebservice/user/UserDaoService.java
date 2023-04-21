package com.example.restfulwebservice.user;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * UserDaoService.java
 * Class 설명을 작성하세요.
 *
 * @author kjm
 * @since 2023.04.20
 */
@Service
public class UserDaoService {

    private static List<User> users = new ArrayList<>();
    private static int usersCount = 3;
    static {
        users.add(new User(1L, "kim", new Date(), "pass1", "701010-1111111"));
        users.add(new User(2L, "John", new Date(),"pass2", "701010-1111111"));
        users.add(new User(3L, "Mei", new Date(),"pass3", "701010-1111111"));
    }

    public List<User> findAll() {
        return users;
    }

    public User save(User user) {
        if (user.getId() == null) {
            user.setId((long) ++usersCount);
        }
        users.add(user);
        return user;
    }

    public User update(int id, User updateUser) {
        for (User user : users) {
            if (user.getId() == id) {
                user.setName(updateUser.getName());
                user.setJoinDate(updateUser.getJoinDate());
                return user;
            }
        }
        return null;
    }

    public User findOne(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    public boolean deleteUser(int id) {
        return users.removeIf(user -> user.getId() == id);
    }
}
