package com.mehmetsolak.urlshortener.service;

import com.mehmetsolak.urlshortener.core.result.Result;
import com.mehmetsolak.urlshortener.entity.User;

import java.util.List;

public interface UserService {
    Result<User> save(User user);
    Result<User> findByEmailOrUsername(String email, String username);
    boolean existsByEmailOrUsername(String email, String username);
    Result<List<User>> findAll();
}
