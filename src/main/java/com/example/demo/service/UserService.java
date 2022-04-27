package com.example.demo.service;

import com.example.demo.data.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User create(User user);
    User update(User user);
    void delete(User user);
    User getId(Long id);
    User getByLogin(String login);

}
