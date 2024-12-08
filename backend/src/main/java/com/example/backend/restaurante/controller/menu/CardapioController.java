package com.example.backend.restaurante.controller.menu;

import com.example.backend.restaurante.model.menu.Cardapio;
import com.example.backend.restaurante.service.menu.CardapioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cardapio")
public class CardapioController {

    @Autowired
    private CardapioService cardapioService;

    @PostMapping
    public Cardapio createCardapio(@RequestBody Cardapio cardapio){

        return cardapioService.saveCardapio(cardapio);
    }

    @GetMapping
    public List<Cardapio> getAllCardapio(){
        return cardapioService.getAllCardapio();
    }

    @GetMapping("/{id}")
    public Cardapio getCardapioById(@PathVariable Long id) {
        return cardapioService.getCardapioById(id);
    }

    @PutMapping("/{id}")
    public Cardapio updateCardapio(@PathVariable Long id, @RequestBody Cardapio cardapio){
        return  cardapioService.updateCardapio(id, cardapio);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCardapio(@PathVariable Long id){
        cardapioService.deleteCardapio(id);
        return ResponseEntity.ok().build();
    }
}


