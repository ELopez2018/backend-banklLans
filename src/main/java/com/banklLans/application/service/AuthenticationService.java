package com.banklLans.application.service;

import com.banklLans.application.dto.AuthCredentials;
import com.banklLans.application.dto.AuthResponse;
import com.banklLans.domain.repository.UserRepository;
import com.banklLans.infrastructure.config.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

  private final AuthenticationManager authenticationManager;
  private final UserRepository userRepository;
  private final JwtService jwtService;

  public AuthResponse Login (AuthCredentials credentials){
    System.out.println("llego =>" + credentials.getPassword());
    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(credentials.getUsername(), credentials.getPassword()));
    System.out.println("paso");
    UserDetails user = userRepository.findByEmail(credentials.getUsername()).orElseThrow();
    String token =  jwtService.getToken(user);
    return  AuthResponse.builder()
            .token(token)
            .build();
  }
}