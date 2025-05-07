package com.banklLans.application.service;

import com.banklLans.application.dto.AuthCredentials;
import com.banklLans.application.dto.AuthResponse;
import com.banklLans.domain.model.User;
import com.banklLans.domain.repository.UserRepository;
import com.banklLans.infrastructure.config.jwt.JwtService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

  private final AuthenticationManager authenticationManager;
  private final UserRepository userRepository;
  private final JwtService jwtService;
  @Autowired
  private PasswordEncoder passwordEncoder;

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

  @Transactional
  public User registerUser(String email, String password) {
    // Comprobamos si el usuario ya existe
    if (userRepository.findByEmail(email).isPresent()) {
      throw new IllegalArgumentException("El correo electrónico ya está registrado.");
    }

    // Creamos un nuevo usuario
    User user = new User();
    user.setEmail(email);
    user.setPassword(passwordEncoder.encode(password));  // Encriptamos la contraseña

    // Guardamos el usuario
    return userRepository.save(user);
  }
}