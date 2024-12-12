package com.example.backend.restaurante.controller.menu;

import com.example.backend.restaurante.model.menu.Cardapio;
import com.example.backend.restaurante.model.menu.CardapioRequest;
import com.example.backend.restaurante.model.menu.Item;
import com.example.backend.restaurante.service.menu.CardapioService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cardapio")
public class CardapioController {

    @Autowired
    private CardapioService cardapioService;

    @PostMapping
    public ResponseEntity<Cardapio> createCardapio(@RequestBody Cardapio cardapio){
        Cardapio newCardapio = cardapioService.saveCardapio(cardapio);
        return ResponseEntity.ok(newCardapio);
    }

    @GetMapping
    public ResponseEntity<List<Cardapio>> getAllCardapio(){
        List<Cardapio> cardapio = cardapioService.getAllCardapio();
        return ResponseEntity.ok(cardapio);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cardapio> getCardapioById(@PathVariable Long id) {
        Cardapio cardapio = cardapioService.getCardapioById(id);
        if (cardapio != null) {
            return ResponseEntity.ok(cardapio);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cardapio> updateCardapio(@PathVariable Long id, @RequestBody Cardapio updateCardapio){
        Cardapio updatedCardapio = cardapioService.updateCardapio(id, updateCardapio);
        if (updatedCardapio != null) {
            return ResponseEntity.ok(updatedCardapio);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCardapio(@PathVariable Long id){
        cardapioService.deleteCardapio(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/criar")
    public ResponseEntity<Cardapio> createCardapio(@RequestBody CardapioRequest cardapioRequest) {
        // Criação do cardápio com os itens
        Cardapio cardapio = cardapioService.createCardapioWithItems(cardapioRequest.getTitle(), cardapioRequest.getItemIds());
        return ResponseEntity.ok(cardapio);
    }

    @PutMapping("/{id}/itens")
    public ResponseEntity<Cardapio> updateCardapioItems(@PathVariable Long id, @RequestBody List<Long> itemIds) {
        Cardapio updatedCardapio = cardapioService.updateCardapioItems(id, itemIds);
        if (updatedCardapio != null) {
            return ResponseEntity.ok(updatedCardapio);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}


