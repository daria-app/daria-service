package com.refactory.daria.controllers;

import com.refactory.daria.exception.BadRequestException;
import com.refactory.daria.models.AuthProvider;
import com.refactory.daria.models.User;
import com.refactory.daria.payload.ApiResponse;
import com.refactory.daria.payload.AuthResponse;
import com.refactory.daria.payload.LoginRequest;
import com.refactory.daria.payload.SignupRequest;
import com.refactory.daria.repositories.UserRepository;
import com.refactory.daria.security.TokenProvider;
import com.refactory.daria.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private TokenProvider tokenProvider;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {

        User user = userService.findOneByEmail(loginRequest.getEmail());
        if (user == null) {
            userService.createUser(loginRequest);
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = tokenProvider.createToken(authentication);
        return ResponseEntity.ok(new AuthResponse(token));

    }


}
