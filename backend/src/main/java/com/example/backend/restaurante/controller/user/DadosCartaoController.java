package com.example.backend.restaurante.controller.user;

import com.example.backend.restaurante.config.SecurityUtils;
import com.example.backend.restaurante.model.user.DadosCartao;
import com.example.backend.restaurante.model.user.Usuario;
import com.example.backend.restaurante.service.user.DadosCartaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/cartoes")
public class DadosCartaoController {

    @Autowired
    private DadosCartaoService dadosCartaoService;

    @GetMapping
    public ResponseEntity<List<DadosCartao>> getCartoes(Authentication authentication) {
        Long usuarioId = SecurityUtils.getUsuarioId(authentication);
        var cartoes = dadosCartaoService.findCartoesByUsuario(usuarioId);
        return ResponseEntity.ok(cartoes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosCartao> getCartaoById(@PathVariable Long id, Authentication authentication) {
        Long usuarioId = SecurityUtils.getUsuarioId(authentication);
        var cartao = dadosCartaoService.findCartaoByIdAndUsuario(id, usuarioId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.FORBIDDEN, "Acesso negado ao cartão"));
        return ResponseEntity.ok(cartao);
    }

    @PostMapping
    public ResponseEntity<DadosCartao> addCartao(@RequestBody DadosCartao cartao, Authentication authentication) {
        Long usuarioId = SecurityUtils.getUsuarioId(authentication);
        var savedCartao = dadosCartaoService.saveCartaoForUsuario(cartao, usuarioId);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCartao);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DadosCartao> updateCartao(
            @PathVariable Long id, @RequestBody DadosCartao updatedCartao, Authentication authentication) {
        Long usuarioId = SecurityUtils.getUsuarioId(authentication);
        var savedCartao = dadosCartaoService.updateCartaoForUsuario(id, updatedCartao, usuarioId);
        return ResponseEntity.ok(savedCartao);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCartao(@PathVariable Long id, Authentication authentication) {
        Long usuarioId = SecurityUtils.getUsuarioId(authentication);
        dadosCartaoService.deleteCartaoForUsuario(id, usuarioId);
        return ResponseEntity.noContent().build();
    }

    private Long getUsuarioIdFromAuthentication(Authentication authentication) {
        return SecurityUtils.getUsuarioId(authentication);
    }
}

