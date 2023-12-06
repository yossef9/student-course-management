package com.scm.studentcoursemanagement.service.impl;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class JwtService {




    @Value("${jwt.secret}")
    private String secret;

/*    @Value("${jwt.expiration}")
    private Long expiration;*/

    // Generate a JWT access token with user's roles and permissions as claims
    public String generateToken(UserDetails userDetails) {

        Map<String, Object> claims = new HashMap<>();
        //builder.claim("authorities", authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));
        // Add user's roles and permissions as claims
        claims.put("authorities", userDetails.getAuthorities().stream()
                .filter(grantedAuthority -> grantedAuthority.getAuthority().startsWith("ROLE_"))
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList()));

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 60*60 * 1000))
                .signWith(getSignKey(),SignatureAlgorithm.HS512).compact();
    }


    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // Validate a JWT access token
    public Boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static String getTokenFromAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication instanceof UsernamePasswordAuthenticationToken token) {
            String jwtToken = (String) token.getCredentials();

            return jwtToken;
        }

        return null;
    }
}
