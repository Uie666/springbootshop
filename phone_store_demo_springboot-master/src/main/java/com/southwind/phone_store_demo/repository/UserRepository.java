package com.southwind.phone_store_demo.repository;

import com.southwind.phone_store_demo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserRepository {
    public User login(User user);
    public void register(User user);
}
