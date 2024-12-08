package com.example.backend.restaurante.service.order;

import com.example.backend.restaurante.model.order.DadosDoPagamento;
import com.example.backend.restaurante.repository.order.DadosDoPagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DadosDoPagamentoService {

        @Autowired
        private DadosDoPagamentoRepository dadosDoPagamentoRepository;

        public DadosDoPagamento saveDadosDoPagamento(DadosDoPagamento dadosDoPagamento) {
            return dadosDoPagamentoRepository.save(dadosDoPagamento);
        }

        public List<DadosDoPagamento> getAllDadosDoPagamento() {
            return dadosDoPagamentoRepository.findAll();
        }

        public DadosDoPagamento getDadosDoPagamentoById(Long id) {
            Optional<DadosDoPagamento> dadosDoPagamento = dadosDoPagamentoRepository.findById(id);
            return dadosDoPagamento.orElse(null);
        }

        public DadosDoPagamento updateDadosDoPagamento(Long id, DadosDoPagamento updateDadosDoPagamento) {
            Optional<DadosDoPagamento> dadosDoPagamento = dadosDoPagamentoRepository.findById(id);
            if (dadosDoPagamento.isPresent()) {
                DadosDoPagamento existingDadosDoPagamento = dadosDoPagamento.get();
                existingDadosDoPagamento.setTipoPagamento(updateDadosDoPagamento.getTipoPagamento());
                existingDadosDoPagamento.setTroco(updateDadosDoPagamento.getTroco());
                existingDadosDoPagamento.setStatusPagamento(updateDadosDoPagamento.getStatusPagamento());
                existingDadosDoPagamento.setDataPagamento(updateDadosDoPagamento.getDataPagamento());
                existingDadosDoPagamento.setPedido(updateDadosDoPagamento.getPedido());

                return dadosDoPagamentoRepository.save(existingDadosDoPagamento);
            } else {
                return null;
            }
        }

        public void deleteDadosDoPagamento(Long id) {
            dadosDoPagamentoRepository.deleteById(id);
        }
}
