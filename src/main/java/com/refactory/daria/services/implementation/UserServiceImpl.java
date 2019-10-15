package com.refactory.daria.services.implementation;

import com.refactory.daria.models.AuthProvider;
import com.refactory.daria.models.User;
import com.refactory.daria.payload.LoginRequest;
import com.refactory.daria.repositories.UserRepository;
import com.refactory.daria.services.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User findOneById(String id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User findOneByEmail(String email) {
        return userRepository.findByEmail(email).stream().findAny().orElse(null);
    }

    @Override
    public User createUser(LoginRequest loginRequest) {

        // Creating user's account
        User user = new User();
        user.setEmail(loginRequest.getEmail());
        user.setPassword(loginRequest.getPassword());
        user.setProvider(AuthProvider.local);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User savedUser = userRepository.save(user);
        return savedUser;

    }
}
