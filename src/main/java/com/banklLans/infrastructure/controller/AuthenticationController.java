package com.banklLans.infrastructure.controller;

import com.banklLans.application.dto.AuthCredentials;
import com.banklLans.application.dto.AuthResponse;
import com.banklLans.application.service.AuthenticationService;
import com.banklLans.domain.model.User;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@Log
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

  @Autowired
  private AuthenticationService authService;

  @Autowired
  private  PasswordEncoder passwordEncoder;

  @PostMapping("/login")
  public ResponseEntity<AuthResponse> login(@RequestBody AuthCredentials credentials) {
    log.info("passwordEncoder: " + passwordEncoder.encode(credentials.getPassword()));
    return ResponseEntity.ok(authService.Login(credentials));
  }

  @PostMapping("/register")
  public ResponseEntity<User> register(@RequestBody AuthCredentials credentials) {
    return ResponseEntity.ok(authService.registerUser(credentials.getUsername(), credentials.getPassword()));
  }

  @GetMapping("/usersAll")
  public String getAllUserTest() {
    return "test";
  }

}