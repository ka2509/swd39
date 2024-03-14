package com.example.swd39.service;

import com.example.swd39.config.JwtProvider;
import com.example.swd39.exception.UserException;
import com.example.swd39.model.Role;
import com.example.swd39.model.User;
import com.example.swd39.repository.UserRepository;
import com.example.swd39.response.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private UserService customUserDetails;
    public AuthResponse signUpUser(User user) throws UserException {
        String email = user.getEmail();
        String password = user.getPassword();
        String fullName = user.getFullName();

        User isEmailExist = userRepository.findByEmail(email);

        if(isEmailExist != null) {
            throw new UserException("Email is already used with another account!");
        }

        User newUser = new User();
        newUser.setEmail(email);
        newUser.setPassword(passwordEncoder.encode(password));
        newUser.setFullName(fullName);
        newUser.setRole(Role.patient);
        newUser.setCreatedAt(LocalDateTime.now());
        User savedUser = userRepository.save(newUser);

        Authentication authentication = new UsernamePasswordAuthenticationToken(email, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.generateToken(authentication);

        return new AuthResponse(token, true, savedUser.getRole());
    }

    public AuthResponse signIn(User user) throws UserException {
        String email = user.getEmail();
        String password = user.getPassword();

        try {
            UserDetails userDetails = customUserDetails.loadUserByUsername(email);
            User user1 = customUserDetails.findUserByEmail(email);
            if(passwordEncoder.matches(password, userDetails.getPassword())) {
                Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                String token = jwtProvider.generateToken(authentication);
                return new AuthResponse(token, true, user1.getRole());

            } else {
                throw new UserException("Wrong Password!");
            }
        } catch (UsernameNotFoundException e) {
            throw new UserException("User with the specified email does not exist!");
        }
    }
}
