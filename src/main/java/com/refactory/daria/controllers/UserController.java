package com.refactory.daria.controllers;

import com.refactory.daria.exception.ResourceNotFoundException;
import com.refactory.daria.models.User;
import com.refactory.daria.repositories.UserRepository;
import com.refactory.daria.security.CurrentUser;
import com.refactory.daria.security.UserPrincipal;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/oauth2/user")
    @PreAuthorize("hasRole('USER')")
    public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return userRepository.findById(new ObjectId(userPrincipal.getId()))
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
    }
}
