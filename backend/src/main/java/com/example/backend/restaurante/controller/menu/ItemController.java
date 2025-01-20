package com.example.backend.restaurante.controller.menu;
import com.example.backend.restaurante.model.menu.Item;
import com.example.backend.restaurante.repository.menu.ItemRepository;
import com.example.backend.restaurante.service.S3Service;
import com.example.backend.restaurante.service.menu.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/item")
@CrossOrigin(origins = "http://localhost:3000")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private S3Service s3Service;

    @Autowired
    private ItemRepository itemRepository;

    @PostMapping("/create")
    public ResponseEntity<String> createItem(@RequestBody Map<String, Object> request) {
        try {
            // Extrair os dados do item do JSON
            String name = (String) request.get("name");
            double price = ((Number) request.get("price")).doubleValue();
            String description = (String) request.get("description");
            Boolean availability = (Boolean) request.get("availability");
            String imageBase64 = (String) request.get("imageBase64");

            // Realizar upload da imagem para o S3 e obter a URL
            String imageUrl = s3Service.uploadImage(imageBase64, name);

            // Criar o novo item e atualizar a URL da imagem
            Item newItem = new Item(name, price, description, availability, imageUrl);

            // Salvar o item no banco de dados
            itemRepository.save(newItem);

            return ResponseEntity.ok("Item criado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao criar o item: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Item>> getAllItems() {
        List<Item> items = itemService.getAllItem();
        return ResponseEntity.ok(items);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable Long id) {
        Item item = itemService.getItemById(id);
        if (item != null) {
            return ResponseEntity.ok(item);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Item> updateItem(@PathVariable Long id, @RequestBody Item updateItem) {
        Item updatedItem = itemService.updateItem(id, updateItem);
        if (updatedItem != null) {
            return ResponseEntity.ok(updatedItem);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }
}


