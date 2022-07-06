package com.southwind.phone_store_demo.service;

import com.southwind.phone_store_demo.entity.User;

public interface UserService {
   public User login(User user);
   public User register(User user);
}
