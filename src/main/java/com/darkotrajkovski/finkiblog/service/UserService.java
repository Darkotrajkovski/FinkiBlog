package com.darkotrajkovski.finkiblog.service;

import com.darkotrajkovski.finkiblog.exceptions.InvalidArgumentsException;
import com.darkotrajkovski.finkiblog.model.Role;
import com.darkotrajkovski.finkiblog.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User register(String username, String password, String repeatPassword, Role role) throws InvalidArgumentsException;
}
