package com.banklLans.application.service;

import com.banklLans.application.service.interfaces.UserInterface;
import com.banklLans.domain.model.User;
import com.banklLans.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService  implements UserInterface {

  @Autowired
  UserRepository userRepository;

  @Override
  public List<User> getAll() {
    Sort sort = Sort.by(Sort.Direction.ASC, "firstName");
    return userRepository.findAll(sort);
  }

  @Override
  public User getUserByID(Long userId) {
    return userRepository.findById(userId).orElseThrow();
  }

  @Override
  public Boolean delete(User user) {
    try {
      userRepository.delete(user);
      return true;
    } catch (DataAccessException e) {
      return false;
    }
  }

  @Override
  public User update(User user) {
    return userRepository.saveAndFlush(user);
  }

  public ResponseEntity<?> save(User user) {
    return ResponseEntity.ok(userRepository.save(user));
  }
}