package com.example.backend.restaurante.service.menu;

import com.example.backend.restaurante.model.menu.Cardapio;
import com.example.backend.restaurante.repository.menu.CardapioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CardapioService {

    @Autowired
    private CardapioRepository cardapioRepository;

    public Cardapio saveCardapio(Cardapio cardapio){

        return cardapioRepository.save(cardapio);
    }

    public List<Cardapio> getAllCardapio(){
        return cardapioRepository.findAll();
    }

    public Cardapio getCardapioById(Long id){
        Optional<Cardapio> cardapio = cardapioRepository.findById(id);
        return cardapio.orElse(null);
    }

    public Cardapio updateCardapio(Long id, Cardapio updateCardapio){
        Optional<Cardapio> cardapio = cardapioRepository.findById(id);
        if(cardapio.isPresent()){
            Cardapio existingCardapio = cardapio.get();
            existingCardapio.setTitle(updateCardapio.getTitle());
            existingCardapio.setItem(updateCardapio.getItem());

            return cardapioRepository.save(existingCardapio);
        }else{
            return null;
        }
    }

    public void deleteCardapio(Long id){
        cardapioRepository.deleteById(id);
    }
}
