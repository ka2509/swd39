package com.example.swd39.response;

import com.example.swd39.model.Role;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {

    private String jwt;
    private boolean status;
    private Role role;

    public AuthResponse(String jwt, boolean status) {
        this.jwt = jwt;
        this.status = status;
    }
}