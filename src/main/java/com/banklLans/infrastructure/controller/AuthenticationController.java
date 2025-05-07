package com.banklLans.infrastructure.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

//  @Autowired
//  private AuthenticationService authenticationService;
//
//  @Autowired
//  private JwtTokenProvider jwtTokenProvider;
//
//  // 1. Iniciar sesión (autenticación)
//  @PostMapping("/login")
//  public ResponseEntity<String> login(@RequestBody User loginRequest) {
//    // Suponiendo que el servicio de autenticación maneja la lógica de login
//    String token = authenticationService.authenticate(loginRequest);
//
//    // Devuelve el JWT (token de autenticación)
//    return ResponseEntity.ok("Bearer " + token);
//  }

  @GetMapping("/usersAll")
  public String getAllUserTest() {
    return "test";
  }

}