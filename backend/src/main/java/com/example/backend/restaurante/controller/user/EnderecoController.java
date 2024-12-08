package com.example.backend.restaurante.controller.user;

import com.example.backend.restaurante.model.user.Endereco;
import com.example.backend.restaurante.service.user.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enderecos")
public class EnderecoController {

        @Autowired
        private EnderecoService enderecoService;

        @PostMapping
        public ResponseEntity<Endereco> createEndereco(@RequestBody Endereco endereco) {
            Endereco newEndereco = enderecoService.saveEndereco(endereco);
            return ResponseEntity.ok(newEndereco);
        }

        @GetMapping
        public ResponseEntity<List<Endereco>> getAllEnderecos() {
            List<Endereco> enderecos = enderecoService.getAllEnderecos();
            return ResponseEntity.ok(enderecos);
        }

        @GetMapping("/{id}")
        public ResponseEntity<Endereco> getEnderecoById(@PathVariable Long id) {
            Endereco endereco = enderecoService.getEnderecoById(id);
            if (endereco != null) {
                return ResponseEntity.ok(endereco);
            } else {
                return ResponseEntity.notFound().build();
            }
        }

        @PutMapping("/{id}")
        public ResponseEntity<Endereco> updateEndereco(@PathVariable Long id, @RequestBody Endereco updateEndereco) {
            Endereco updatedEndereco = enderecoService.updateEndereco(id, updateEndereco);
            if (updatedEndereco != null) {
                return ResponseEntity.ok(updatedEndereco);
            } else {
                return ResponseEntity.notFound().build();
            }
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteEndereco(@PathVariable Long id) {
            enderecoService.deleteEndereco(id);
            return ResponseEntity.noContent().build();
        }
}
