package com.example.backend.restaurante.repository.user;

import com.example.backend.restaurante.model.user.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
