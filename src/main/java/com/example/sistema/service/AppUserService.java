package com.example.sistema.service;

import com.example.sistema.model.AppUser;
import com.example.sistema.repository.AppUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppUserService {

  private final AppUserRepository repository;

  public AppUserService(AppUserRepository repository) {
    this.repository = repository;
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
  public AppUser save(AppUser user) {
    return repository.save(user);
  }

  // Deleta usuário
  public void delete(Long id) {
    repository.deleteById(id);
  }

}
