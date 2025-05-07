package com.banklLans.infrastructure.config.init;

import com.banklLans.domain.model.User;
import com.banklLans.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


import java.util.Arrays;

@Component
public class DataInitializer implements CommandLineRunner {

  private  PasswordEncoder passwordEncoder;
  @Autowired
  private UserRepository userRepository;

  @Override
  public void run(String... args) throws Exception {
    if (userRepository.count() == 0) {
      userRepository.saveAll(Arrays.asList(
              new User(1L, "estarlin.elv@gmail.com", passwordEncoder.encode("123456"), "estarlin.elv@gmail.com")
      ));
    }


  }
}