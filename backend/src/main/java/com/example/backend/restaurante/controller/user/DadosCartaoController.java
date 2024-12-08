package com.example.backend.restaurante.controller.user;

import com.example.backend.restaurante.model.menu.Cardapio;
import com.example.backend.restaurante.model.menu.Item;
import com.example.backend.restaurante.model.user.DadosCartao;
import com.example.backend.restaurante.repository.menu.ItemRepository;
import com.example.backend.restaurante.repository.user.DadosCartaoRepository;
import com.example.backend.restaurante.service.user.DadosCartaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dadosCartao")
public class DadosCartaoController {

    @Autowired
    private DadosCartaoService dadosCartaoService;

    @PostMapping
    public DadosCartao createDadosCartao(@RequestBody DadosCartao dadosCartao){
        return dadosCartaoService.saveDadosCartao(dadosCartao);
    }

    @GetMapping
    public List<DadosCartao> getAllDadosCartao(){
        return dadosCartaoService.getAllDadosCartao();
    }

    @GetMapping("/{id}")
    public DadosCartao getDadosCartaoById(@PathVariable Long id) {
        return dadosCartaoService.getDadosCartaoById(id);
    }

    @PutMapping("/{id}")
    public DadosCartao updateDadosCartao(@PathVariable Long id, @RequestBody DadosCartao dadosCartao){
        return  dadosCartaoService.updateDadosCartao(id, dadosCartao);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDadosCartao(@PathVariable Long id){
        dadosCartaoService.deleteDadosCartao(id);
        return ResponseEntity.ok().build();
    }
}
