package com.example.backend.controller;

import com.example.backend.model.Item;
import com.example.backend.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;

    // Método para listar todos os itens (GET)
    @GetMapping("/items")
    public List<Item> getItems() {
        return itemRepository.findAll();
    }

    // Método para adicionar um novo item (CREATE)
    @PostMapping("/items")
    public Item createItem(@RequestBody Item item) {
        System.out.println("Item recebido: " + item);
        return itemRepository.save(item);
    }


    // Método para atualizar um item existente (UPDATE)
    @PutMapping("/items/{id}")
    public Item updateItem(@PathVariable int id, @RequestBody Item updatedItem) {
        return itemRepository.findById(id)
                .map(item -> {
                    item.setNome(updatedItem.getNome());
                    item.setPrice(updatedItem.getPrice());
                    item.setTitle(updatedItem.getTitle());
                    item.setImage(updatedItem.getImage());
                    return itemRepository.save(item);
                })
                .orElseThrow(() -> new RuntimeException("Item não encontrado com id: " + id));
    }

    // Método para deletar um item (DELETE)
    @DeleteMapping("/items/{id}")
    public void deleteItem(@PathVariable int id) {
        itemRepository.deleteById(id);
    }
}
