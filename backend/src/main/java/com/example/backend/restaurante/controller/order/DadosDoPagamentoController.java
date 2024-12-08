package com.example.backend.restaurante.controller.order;

import com.example.backend.restaurante.model.order.DadosDoPagamento;
import com.example.backend.restaurante.service.order.DadosDoPagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dadosDoPagamento")
public class DadosDoPagamentoController {

        @Autowired
        private DadosDoPagamentoService dadosDoPagamentoService;

        @PostMapping
        public ResponseEntity<DadosDoPagamento> createDadosDoPagamento(@RequestBody DadosDoPagamento dadosDoPagamento) {
            DadosDoPagamento newDadosDoPagamento = dadosDoPagamentoService.saveDadosDoPagamento(dadosDoPagamento);
            return ResponseEntity.ok(newDadosDoPagamento);
        }

        @GetMapping
        public ResponseEntity<List<DadosDoPagamento>> getAllDadosDoPagamento() {
            List<DadosDoPagamento> dadosDoPagamento = dadosDoPagamentoService.getAllDadosDoPagamento();
            return ResponseEntity.ok(dadosDoPagamento);
        }

        @GetMapping("/{id}")
        public ResponseEntity<DadosDoPagamento> getDadosDoPagamentoById(@PathVariable Long id) {
            DadosDoPagamento dadosDoPagamento = dadosDoPagamentoService.getDadosDoPagamentoById(id);
            if (dadosDoPagamento != null) {
                return ResponseEntity.ok(dadosDoPagamento);
            } else {
                return ResponseEntity.notFound().build();
            }
        }

        @PutMapping("/{id}")
        public ResponseEntity<DadosDoPagamento> updateDadosDoPagamento(@PathVariable Long id, @RequestBody DadosDoPagamento updateDadosDoPagamento) {
            DadosDoPagamento updatedDadosDoPagamento = dadosDoPagamentoService.updateDadosDoPagamento(id, updateDadosDoPagamento);
            if (updatedDadosDoPagamento != null) {
                return ResponseEntity.ok(updatedDadosDoPagamento);
            } else {
                return ResponseEntity.notFound().build();
            }
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteDadosDoPagamento(@PathVariable Long id) {
            dadosDoPagamentoService.deleteDadosDoPagamento(id);
            return ResponseEntity.noContent().build();
        }
}
