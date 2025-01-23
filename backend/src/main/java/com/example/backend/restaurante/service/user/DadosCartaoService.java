package com.example.backend.restaurante.service.user;

import com.example.backend.restaurante.model.user.DadosCartao;
import com.example.backend.restaurante.repository.user.DadosCartaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DadosCartaoService {

    @Autowired
    private DadosCartaoRepository dadosCartaoRepository;

    public List<DadosCartao> findCartoesByUsuario(Long usuarioId) {
        return dadosCartaoRepository.findByUsuarioId(usuarioId);
    }

    public Optional<DadosCartao> findCartaoByIdAndUsuario(Long id, Long usuarioId) {
        return dadosCartaoRepository.findById(id)
                .filter(cartao -> cartao.getUsuario().getId().equals(usuarioId));
    }

    public DadosCartao saveCartaoForUsuario(DadosCartao cartao, Long usuarioId) {
        cartao.getUsuario().setId(usuarioId);
        return dadosCartaoRepository.save(cartao);
    }

    public DadosCartao updateCartaoForUsuario(Long id, DadosCartao updatedCartao, Long usuarioId) {
        var cartao = findCartaoByIdAndUsuario(id, usuarioId)
                .orElseThrow(() -> new RuntimeException("Acesso negado ao cartão"));

        cartao.setNumeroCartao(updatedCartao.getNumeroCartao());
        cartao.setNomeTitular(updatedCartao.getNomeTitular());
        cartao.setValidadeCartao(updatedCartao.getValidadeCartao());
        cartao.setCvv(updatedCartao.getCvv());
        return dadosCartaoRepository.save(cartao);
    }

    public void deleteCartaoForUsuario(Long id, Long usuarioId) {
        var cartao = findCartaoByIdAndUsuario(id, usuarioId)
                .orElseThrow(() -> new RuntimeException("Acesso negado ao cartão"));
        dadosCartaoRepository.delete(cartao);
    }
}

