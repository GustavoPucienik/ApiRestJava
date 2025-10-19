package com.example.sistema.service;

import com.example.sistema.model.AppUser;
import com.example.sistema.repository.AppUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class AppUserService {

  private final AppUserRepository repository;
  private final BCryptPasswordEncoder passwordEncoder;

  public AppUserService(AppUserRepository repository) {
    this.repository = repository;
    this.passwordEncoder = new BCryptPasswordEncoder();
  }

  // Retorna todos os usuários
  public List<AppUser> findAll() {
    return repository.findAll();
  }

  // Busca usuário por ID
  public Optional<AppUser> findById(Long id) {
    return repository.findById(id);
  }

  // Cria ou atualiza usuário
  public AppUser save(AppUser user){
    if (repository.existsByEmail(user.getEmail())) {
      throw new IllegalArgumentException("E-mail já cadastrado");
    }
    if (user.getPassword() != null && !user.getPassword().isEmpty()) {
      user.setPassword(passwordEncoder.encode(user.getPassword()));
    }
    return repository.save(user);
  }

  // Deleta usuário
  public void delete(Long id) {
    repository.deleteById(id);
  }

}
