package com.southwind.phone_store_demo.service.impl;

import com.southwind.phone_store_demo.entity.User;
import com.southwind.phone_store_demo.repository.UserRepository;
import com.southwind.phone_store_demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public User login(User user) {
        return userRepository.login(user);
    }

    @Override
    public User register(User user) {
        userRepository.register(user);
        return user;
    }
}
