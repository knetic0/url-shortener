package com.mehmetsolak.urlshortener.service.impl;

import com.mehmetsolak.urlshortener.constants.Messages;
import com.mehmetsolak.urlshortener.core.result.FailureResult;
import com.mehmetsolak.urlshortener.core.result.Result;
import com.mehmetsolak.urlshortener.core.result.SuccessResult;
import com.mehmetsolak.urlshortener.entity.User;
import com.mehmetsolak.urlshortener.repository.UserRepository;
import com.mehmetsolak.urlshortener.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public final class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Result<User> save(User user) {
        return new SuccessResult<>(userRepository.save(user));
    }

    @Override
    public Result<User> findByEmailOrUsername(String email, String username) {
        Optional<User> user = userRepository.findByEmailOrUsername(email, username);
        if(user.isEmpty()) {
            return new FailureResult<>(Messages.USER_NOT_FOUND);
        }
        return new SuccessResult<>(user.get());
    }

    @Override
    public boolean existsByEmailOrUsername(String email, String username) {
        return userRepository.existsUserByEmailOrUsername(email, username);
    }

    @Override
    public Result<List<User>> findAll() {
        return new SuccessResult<>(userRepository.findAll());
    }
}
