package com.banklLans.infrastructure.config.jwt;



import com.banklLans.domain.model.User;
import com.banklLans.domain.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class JwtService {

    private static final String SECRET = "UWEROUTIORUEWTUREWOUTUIREOUWTREUIITOUERIOPUTPIOREUTOIPREUTOIPREUTOIURE";

    private final UserRepository userRepository;

    public String getToken(UserDetails user) {
        return getToken(new HashMap<>(), user);
    }

    private String getToken(HashMap<String, Object> extraClaims, UserDetails userDetails) {
        User UserDb = userRepository.findByEmail(userDetails.getUsername()).orElseThrow();
        extraClaims.put("role", UserDb.getRole().toString());
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String getUserNameFromToken(String token) {
        try {
            return getClaimFromToken(token, Claims::getSubject);
        } catch (ExpiredJwtException e) {
            throw new CustomJwtException("El token JWT ha expirado. Por favor, inicie sesión nuevamente.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new CustomJwtException("Error al procesar el token JWT.");
        }
    }

    public boolean isValid(String token, UserDetails userDetails) {
    final String username = getUserNameFromToken(token);
    return (username.equals(userDetails.getUsername())
            && !isTokenExpired(token));
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts
                .parser()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Date getExpiration(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    private boolean isTokenExpired(String token) {
        return getExpiration(token).before(new Date());
    }

    public class CustomJwtException extends RuntimeException {
        public CustomJwtException(String message) {
            super(message);
        }
    }
}
