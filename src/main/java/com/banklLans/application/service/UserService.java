package com.banklLans.application.service;

import com.banklLans.application.dto.AuthenticationResponse;
import com.banklLans.domain.model.User;
import com.banklLans.domain.repository.UserRepository;
import com.banklLans.infrastructure.config.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository repository;

  private  PasswordEncoder passwordEncoder;

  @Autowired
  private  JwtService jwtService;
  private  AuthenticationManager authenticationManager;
  public AuthenticationResponse register(User request) {
  return null;
  }

  private void saveUserToken(User user, String jwtToken) {}
}