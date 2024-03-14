package com.example.swd39.service;

import com.example.swd39.config.JwtProvider;
import com.example.swd39.exception.UserException;
import com.example.swd39.model.Role;
import com.example.swd39.model.User;
import com.example.swd39.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtProvider jwtProvider;

    public User findUserById(Long userId) throws UserException {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserException("this user is not exist with this id : " + userId));
    }



    public Role findUserRoleByJwt(String jwt) {
        String email = jwtProvider.getEmailFromToken(jwt);
        User user = userRepository.findByEmail(email);
        return user.getRole();
    }


    //custom UserDetailsService of Spring Security
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if(user == null) {
            throw new UsernameNotFoundException("Email not found!");
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}