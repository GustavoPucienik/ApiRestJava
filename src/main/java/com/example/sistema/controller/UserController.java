package com.example.sistema.controller;

import com.example.sistema.dto.AppUserDTO;
import com.example.sistema.model.AppUser;
import com.example.sistema.service.AppUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

  private final AppUserService UserService;

  public UserController(AppUserService UserService) {
    this.UserService = UserService;
  }

  // POST /users - cria usuário
  @PostMapping
  public ResponseEntity<AppUser> create(@RequestBody AppUserDTO dto) {
    AppUser user = new AppUser();
    user.setName(dto.getName());
    user.setEmail(dto.getEmail());
    user.setPassword(dto.getPassword());
    AppUser savedUser = UserService.save(user);
    return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
  }

  // GET /users - lista todos os usuários
  @GetMapping
  public ResponseEntity<List<AppUser>> getAll() {
    List<AppUser> users = UserService.findAll();
    return ResponseEntity.ok(users);
  }

  // GET /users/{id} - busca usuário por ID
  @GetMapping("/{id}")
  public ResponseEntity<AppUser> getById(@PathVariable Long id) {
    Optional<AppUser> user = UserService.findById(id);
    return user.map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  // PUT /users/{id} - atualiza usuário
  @PutMapping("/{id}")
  public ResponseEntity<AppUser> update(@PathVariable Long id, @RequestBody AppUser userDetails) {
    Optional<AppUser> optionalUser = UserService.findById(id);
    if (optionalUser.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    AppUser existingUser = optionalUser.get();
    existingUser.setName(userDetails.getName());
    existingUser.setEmail(userDetails.getEmail());
    AppUser updatedUser = UserService.save(existingUser);
    return ResponseEntity.ok(updatedUser);
  }

  // DELETE /users/{id} - deleta usuário
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    Optional<AppUser> optionalUser = UserService.findById(id);
    if (optionalUser.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    UserService.delete(id);
    return ResponseEntity.noContent().build();
  }

}
