package com.example.backend.restaurante.service.menu;

import com.example.backend.restaurante.model.menu.Cardapio;
import com.example.backend.restaurante.model.menu.Item;
import com.example.backend.restaurante.repository.menu.CardapioRepository;
import com.example.backend.restaurante.repository.menu.ItemRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CardapioService {

    @Autowired
    private CardapioRepository cardapioRepository;

    @Autowired
    private ItemRepository itemRepository;

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

    public Cardapio createCardapioWithItems(String title, List<Long> itemIds) {
        Cardapio cardapio = new Cardapio();
        cardapio.setTitle(title);

        List<Item> itens = itemRepository.findAllById(itemIds);

        cardapio.setItem(itens);

        return cardapioRepository.save(cardapio);
    }

    public Cardapio updateCardapioItems(Long cardapioId, List<Long> itemIds) {
        // Buscar o cardápio pelo ID
        Optional<Cardapio> optionalCardapio = cardapioRepository.findById(cardapioId);
        if (optionalCardapio.isPresent()) {
            Cardapio cardapio = optionalCardapio.get();

            // Buscar os itens pelos IDs fornecidos
            List<Item> items = itemRepository.findAllById(itemIds);

            // Atualizar a lista de itens do cardápio
            cardapio.setItem(items);

            // Salvar o cardápio atualizado no banco de dados
            return cardapioRepository.save(cardapio);
        }
        return null; // Retornar null se o cardápio não for encontrado
    }

}
