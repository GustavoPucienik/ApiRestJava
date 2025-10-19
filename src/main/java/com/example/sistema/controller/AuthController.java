package com.example.sistema.controller;

import com.example.sistema.dto.LoginDTO;
import com.example.sistema.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

  private final AuthService authService;

  public AuthController(AuthService authService) {
    this.authService = authService;
  }

  @PostMapping("/login")
  public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) {
    boolean success = authService.login(loginDTO.getEmail(), loginDTO.getPassword());
    if (success) {
      return ResponseEntity.ok("Login realizado com sucesso!");
    } else {
      return ResponseEntity.status(401).body("Email ou senha inválidos");
    }
  }
}
