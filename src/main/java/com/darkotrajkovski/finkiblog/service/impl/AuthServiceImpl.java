package com.darkotrajkovski.finkiblog.service.impl;

import com.darkotrajkovski.finkiblog.exceptions.InvalidArgumentsException;
import com.darkotrajkovski.finkiblog.exceptions.InvalidUserCredentialsException;
import com.darkotrajkovski.finkiblog.model.User;
import com.darkotrajkovski.finkiblog.repository.UserRepository;
import com.darkotrajkovski.finkiblog.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User login(String username, String password) {
        if(username==null || username.isEmpty() || password==null || password.isEmpty())
            throw new InvalidArgumentsException();
        return userRepository.findByUsernameAndPassword(username, password).orElseThrow(InvalidUserCredentialsException::new);
    }
}
