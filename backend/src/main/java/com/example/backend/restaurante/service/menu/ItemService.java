package com.example.backend.restaurante.service.menu;

import com.example.backend.restaurante.model.menu.Cardapio;
import com.example.backend.restaurante.model.menu.Item;
import com.example.backend.restaurante.repository.menu.CardapioRepository;
import com.example.backend.restaurante.repository.menu.ItemRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CardapioRepository cardapioRepository;

    public Item saveItem(Item item){
        return itemRepository.save(item);
    }

    public List<Item> getAllItem(){
        return itemRepository.findAll();
    }

    public Item getItemById(Long id){
        Optional<Item> item = itemRepository.findById(id);
        return  item.orElse(null);
    }

    public Item updateItem(Long id, Item updateItem){
        Optional<Item> item = itemRepository.findById(id);
        if(item.isPresent()){
            Item existingItem = item.get();
            existingItem.setNome(updateItem.getNome());
            existingItem.setDescricao(updateItem.getDescricao());
            existingItem.setPrice(updateItem.getPrice());
            existingItem.setImage(updateItem.getImage());
            existingItem.setAvailability(updateItem.getAvailability());
            return itemRepository.save(existingItem);
        }else{
            return null;
        }
    }

    @Transactional public void deleteItem(Long itemId) {
        Optional<Item> itemOpt = itemRepository.findById(itemId);
        if (itemOpt.isPresent()) { Item item = itemOpt.get();
            // Remover referências em todos os cardápios
            Iterator<Cardapio> iterator = item.getCardapio().iterator();
            while (iterator.hasNext()) { Cardapio cardapio = iterator.next();
                iterator.remove();
                // Remover o item da lista de cardápios do item
                cardapio.getItem().remove(item); // Remover o item do cardápio
                cardapioRepository.save(cardapio);
                // Salvar o cardápio atualizado
            }
            //deletar o item
            itemRepository.deleteById(itemId);
        }
    }
}
