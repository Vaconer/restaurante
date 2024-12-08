package com.example.backend.restaurante.service.user;

import com.example.backend.restaurante.model.menu.Cardapio;
import com.example.backend.restaurante.model.user.DadosCartao;
import com.example.backend.restaurante.repository.menu.CardapioRepository;
import com.example.backend.restaurante.repository.user.DadosCartaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DadosCartaoService {

    @Autowired
    private DadosCartaoRepository dadosCartaoRepository;

    public DadosCartao saveDadosCartao(DadosCartao dadosCartao){
        return dadosCartaoRepository.save(dadosCartao);
    }

    public List<DadosCartao> getAllDadosCartao(){
        return dadosCartaoRepository.findAll();
    }

    public DadosCartao getDadosCartaoById(Long id){
        Optional<DadosCartao> dadosCartao = dadosCartaoRepository.findById(id);
        return dadosCartao.orElse(null);
    }

    public DadosCartao updateDadosCartao(Long id, DadosCartao updateDadosCartao){
        Optional<DadosCartao> dadosCartao = dadosCartaoRepository.findById(id);
        if(dadosCartao.isPresent()){
            DadosCartao existingdadosCartao = dadosCartao.get();
            existingdadosCartao.setNumeroCartao(updateDadosCartao.getNumeroCartao());
            existingdadosCartao.setNomeTitular(updateDadosCartao.getNomeTitular());
            existingdadosCartao.setValidadeCartao(updateDadosCartao.getValidadeCartao());
            existingdadosCartao.setCvv(updateDadosCartao.getCvv());


            return dadosCartaoRepository.save(existingdadosCartao);
        }else{
            return null;
        }
    }

    public void deleteDadosCartao(Long id){
        dadosCartaoRepository.deleteById(id);
    }

}
