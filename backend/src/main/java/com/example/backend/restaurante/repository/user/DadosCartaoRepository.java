package com.example.backend.restaurante.repository.user;

import com.example.backend.restaurante.model.user.DadosCartao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DadosCartaoRepository extends JpaRepository<DadosCartao, Long> {
}
