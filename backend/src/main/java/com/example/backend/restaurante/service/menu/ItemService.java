package com.example.backend.restaurante.service.menu;

import com.example.backend.restaurante.model.menu.Item;
import com.example.backend.restaurante.repository.menu.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

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

    public void deleteItem(Long id){
        itemRepository.deleteById(id);
    }
}
