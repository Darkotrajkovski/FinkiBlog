package com.darkotrajkovski.finkiblog.service;

import com.darkotrajkovski.finkiblog.exceptions.InvalidArgumentsException;
import com.darkotrajkovski.finkiblog.exceptions.InvalidUserCredentialsException;
import com.darkotrajkovski.finkiblog.model.User;

public interface AuthService {
    User login(String username, String password) throws InvalidUserCredentialsException, InvalidArgumentsException;
}
