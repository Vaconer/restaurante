package com.example.backend.restaurante.service.order;

import com.example.backend.restaurante.model.order.DadosDeEntregaDoPedido;
import com.example.backend.restaurante.repository.order.DadosDeEntregaDoPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DadosDeEntregaDoPedidoService {


    @Autowired
    private DadosDeEntregaDoPedidoRepository dadosDeEntregaDoPedidoRepository;

    public DadosDeEntregaDoPedido saveDadosDeEntregaDoPedido(DadosDeEntregaDoPedido dadosDeEntregaDoPedido) {
        return dadosDeEntregaDoPedidoRepository.save(dadosDeEntregaDoPedido);
    }

    public List<DadosDeEntregaDoPedido> getAllDadosDeEntregaDoPedido() {
        return dadosDeEntregaDoPedidoRepository.findAll();
    }

    public DadosDeEntregaDoPedido getDadosDeEntregaDoPedidoById(Long id) {
        Optional<DadosDeEntregaDoPedido> dadosDeEntregaDoPedido = dadosDeEntregaDoPedidoRepository.findById(id);
        return dadosDeEntregaDoPedido.orElse(null);
    }

    public DadosDeEntregaDoPedido updateDadosDeEntregaDoPedido(Long id, DadosDeEntregaDoPedido updateDadosDeEntregaDoPedido) {
        Optional<DadosDeEntregaDoPedido> dadosDeEntregaDoPedido = dadosDeEntregaDoPedidoRepository.findById(id);
        if (dadosDeEntregaDoPedido.isPresent()) {
            DadosDeEntregaDoPedido existingDadosDeEntregaDoPedido = dadosDeEntregaDoPedido.get();
            existingDadosDeEntregaDoPedido.setEnderecoEntrega(updateDadosDeEntregaDoPedido.getEnderecoEntrega());
            existingDadosDeEntregaDoPedido.setDataHoraEntregaEstimada(updateDadosDeEntregaDoPedido.getDataHoraEntregaEstimada());
            existingDadosDeEntregaDoPedido.setStatusEntrega(updateDadosDeEntregaDoPedido.getStatusEntrega());
            existingDadosDeEntregaDoPedido.setNomeEntregador(updateDadosDeEntregaDoPedido.getNomeEntregador());

            return dadosDeEntregaDoPedidoRepository.save(existingDadosDeEntregaDoPedido);
        } else {
            return null;
        }
    }

    public void deleteDadosDeEntregaDoPedido(Long id) {
        dadosDeEntregaDoPedidoRepository.deleteById(id);
    }
}
