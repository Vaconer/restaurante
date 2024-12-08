package com.example.backend.restaurante.repository.order;

import com.example.backend.restaurante.model.order.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
