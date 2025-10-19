package com.example.sistema.service;

import com.example.sistema.model.AppUser;
import com.example.sistema.repository.AppUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

  private final AppUserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public AuthService(AppUserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  public boolean login(String email, String password) {
    Optional<AppUser> userOpt = userRepository.findByEmail(email);
    if (userOpt.isEmpty()) return false;

    AppUser user = userOpt.get();
    return passwordEncoder.matches(password, user.getPassword());
  }
}
