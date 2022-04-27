package com.example.demo.service;

import com.example.demo.data.CustomUserDetails;
import com.example.demo.data.entity.Roles;
import com.example.demo.data.entity.User;
import com.example.demo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User create(User user) {
        user.setRole(Roles.STUDENT);
        user.setIsEnabled(true);
        Optional<User> byLogin = userRepository.findByLogin(user.getLogin());
        if (byLogin.isEmpty()) {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            return userRepository.save(user);
        } else {
            throw new IllegalArgumentException("user already registered");
        }
    }

    @Override
    public User update(User user) {
        return userRepository.saveAndFlush(user);
    }

    @Override
    public void delete(User user) {
         userRepository.delete(user);
    }

    @Override
    public User getId(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("user not found"));
    }

    @Override
    public User getByLogin(String login) {
        return userRepository.findByLogin(login).orElseThrow(() -> new IllegalArgumentException("user not found"));
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(s).orElseThrow(() -> new IllegalArgumentException("user not found"));
        return new CustomUserDetails(user);
    }
}
