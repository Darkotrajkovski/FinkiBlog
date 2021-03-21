package com.darkotrajkovski.finkiblog.service.impl;

import com.darkotrajkovski.finkiblog.exceptions.InvalidArgumentsException;
import com.darkotrajkovski.finkiblog.exceptions.PasswordsDoNotMatchException;
import com.darkotrajkovski.finkiblog.exceptions.UsernameAlreadyExistsException;
import com.darkotrajkovski.finkiblog.model.Role;
import com.darkotrajkovski.finkiblog.model.User;
import com.darkotrajkovski.finkiblog.repository.UserRepository;
import com.darkotrajkovski.finkiblog.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(String username, String password, String repeatPassword, Role role) throws InvalidArgumentsException {
        if(username==null || username.isEmpty() || password==null || password.isEmpty())
            throw new InvalidArgumentsException();
        if(!password.equals(repeatPassword))
            throw new PasswordsDoNotMatchException();
        if(userRepository.findByUsername(username).isPresent() || !userRepository.findByUsername(username).isEmpty())
            throw new UsernameAlreadyExistsException(username);

        User user = new User(username, passwordEncoder.encode(password), role);
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByUsername(s).orElseThrow(() -> new UsernameNotFoundException(s));
    }
}
