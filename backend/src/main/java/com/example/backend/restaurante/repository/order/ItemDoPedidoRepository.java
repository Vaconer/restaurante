package com.example.backend.restaurante.repository.order;

import com.example.backend.restaurante.model.order.ItemDoPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemDoPedidoRepository extends JpaRepository<ItemDoPedido, Long> {
}
