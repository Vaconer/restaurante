package com.example.backend.restaurante.repository.order;

import com.example.backend.restaurante.model.order.DadosDeEntregaDoPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DadosDeEntregaDoPedidoRepository extends JpaRepository<DadosDeEntregaDoPedido, Long> {
}
