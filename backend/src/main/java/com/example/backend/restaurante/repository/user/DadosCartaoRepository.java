package com.example.backend.restaurante.repository.user;

import com.example.backend.restaurante.model.user.DadosCartao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DadosCartaoRepository extends JpaRepository<DadosCartao, Long> {
    List<DadosCartao> findByUsuarioId(Long usuarioId);
}
