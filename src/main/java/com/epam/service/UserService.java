package com.epam.service;

import com.epam.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserService {
    List<User> findAll();
    User findById(long id);
    void save(User user);
}
