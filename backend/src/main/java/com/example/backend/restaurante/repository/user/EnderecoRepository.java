package com.example.backend.restaurante.repository.user;

import com.example.backend.restaurante.model.user.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
