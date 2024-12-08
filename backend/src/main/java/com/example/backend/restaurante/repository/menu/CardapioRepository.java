package com.example.backend.restaurante.repository.menu;

import com.example.backend.restaurante.model.menu.Cardapio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardapioRepository extends JpaRepository<Cardapio, Long> {
}