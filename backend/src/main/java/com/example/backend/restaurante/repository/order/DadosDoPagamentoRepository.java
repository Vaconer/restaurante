package com.example.backend.restaurante.repository.order;

import com.example.backend.restaurante.model.order.DadosDoPagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DadosDoPagamentoRepository extends JpaRepository<DadosDoPagamento, Long> {
}
