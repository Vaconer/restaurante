package com.example.backend.restaurante.service.order;

import com.example.backend.restaurante.model.order.Pedido;
import com.example.backend.restaurante.repository.order.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public Pedido savePedido(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    public List<Pedido> getAllPedidos() {
        return pedidoRepository.findAll();
    }

    public Pedido getPedidoById(Long id) {
        Optional<Pedido> pedido = pedidoRepository.findById(id);
        return pedido.orElse(null);
    }

    public Pedido updatePedido(Long id, Pedido updatePedido) {
        Optional<Pedido> pedido = pedidoRepository.findById(id);
        if (pedido.isPresent()) {
            Pedido existingPedido = pedido.get();
            existingPedido.setUsuario(updatePedido.getUsuario());
            existingPedido.setItensDoPedido(updatePedido.getItensDoPedido());
            existingPedido.setDadosDoPagamento(updatePedido.getDadosDoPagamento());
            existingPedido.setDadosDeEntregaDoPedido(updatePedido.getDadosDeEntregaDoPedido());
            existingPedido.setDataPedido(updatePedido.getDataPedido());
            existingPedido.setStatus(updatePedido.getStatus());

            return pedidoRepository.save(existingPedido);
        } else {
            return null;
        }
    }

    public void deletePedido(Long id) {
        pedidoRepository.deleteById(id);
    }
}
