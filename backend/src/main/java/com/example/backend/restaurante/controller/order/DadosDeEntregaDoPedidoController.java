package com.example.backend.restaurante.controller.order;

import com.example.backend.restaurante.model.order.DadosDeEntregaDoPedido;
import com.example.backend.restaurante.service.order.DadosDeEntregaDoPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dadosDeEntregaDoPedido")
public class DadosDeEntregaDoPedidoController {


        @Autowired
        private DadosDeEntregaDoPedidoService dadosDeEntregaDoPedidoService;

        @PostMapping
        public ResponseEntity<DadosDeEntregaDoPedido> createDadosDeEntregaDoPedido(@RequestBody DadosDeEntregaDoPedido dadosDeEntregaDoPedido) {
            DadosDeEntregaDoPedido newDadosDeEntregaDoPedido = dadosDeEntregaDoPedidoService.saveDadosDeEntregaDoPedido(dadosDeEntregaDoPedido);
            return ResponseEntity.ok(newDadosDeEntregaDoPedido);
        }

        @GetMapping
        public ResponseEntity<List<DadosDeEntregaDoPedido>> getAllDadosDeEntregaDoPedido() {
            List<DadosDeEntregaDoPedido> dadosDeEntregaDoPedido = dadosDeEntregaDoPedidoService.getAllDadosDeEntregaDoPedido();
            return ResponseEntity.ok(dadosDeEntregaDoPedido);
        }

        @GetMapping("/{id}")
        public ResponseEntity<DadosDeEntregaDoPedido> getDadosDeEntregaDoPedidoById(@PathVariable Long id) {
            DadosDeEntregaDoPedido dadosDeEntregaDoPedido = dadosDeEntregaDoPedidoService.getDadosDeEntregaDoPedidoById(id);
            if (dadosDeEntregaDoPedido != null) {
                return ResponseEntity.ok(dadosDeEntregaDoPedido);
            } else {
                return ResponseEntity.notFound().build();
            }
        }

        @PutMapping("/{id}")
        public ResponseEntity<DadosDeEntregaDoPedido> updateDadosDeEntregaDoPedido(@PathVariable Long id, @RequestBody DadosDeEntregaDoPedido updateDadosDeEntregaDoPedido) {
            DadosDeEntregaDoPedido updatedDadosDeEntregaDoPedido = dadosDeEntregaDoPedidoService.updateDadosDeEntregaDoPedido(id, updateDadosDeEntregaDoPedido);
            if (updatedDadosDeEntregaDoPedido != null) {
                return ResponseEntity.ok(updatedDadosDeEntregaDoPedido);
            } else {
                return ResponseEntity.notFound().build();
            }
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteDadosDeEntregaDoPedido(@PathVariable Long id) {
            dadosDeEntregaDoPedidoService.deleteDadosDeEntregaDoPedido(id);
            return ResponseEntity.noContent().build();
        }
}
