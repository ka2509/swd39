package com.example.swd39.config;

import com.example.swd39.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.security.Keys;
import org.springframework.util.StringUtils;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtProvider {

    public String generateToken(Authentication auth) {
        String jwt = Jwts.builder()
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + 86400000))
                .claim("email", auth.getName())
                .signWith(SignatureAlgorithm.HS512, JwtConstant.SECRET_KEY)
                .compact();
        jwt.getBytes(StandardCharsets.UTF_8);
        return jwt;
    }
    public String getEmailFromToken(String jwt) {
        Claims claims = Jwts.parserBuilder().setSigningKey(JwtConstant.SECRET_KEY).build().parseClaimsJws(jwt).getBody();

        return String.valueOf(claims.get("email"));
    }
    public String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
