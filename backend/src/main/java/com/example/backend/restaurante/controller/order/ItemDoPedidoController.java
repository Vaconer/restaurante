package com.example.backend.restaurante.controller.order;

import com.example.backend.restaurante.model.order.ItemDoPedido;
import com.example.backend.restaurante.service.order.ItemDoPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/itemDoPedido")
public class ItemDoPedidoController {

    @Autowired
    private ItemDoPedidoService itemDoPedidoService;

    @PostMapping
    public ResponseEntity<ItemDoPedido> createItemDoPedido(@RequestBody ItemDoPedido itemDoPedido) {
        ItemDoPedido newItemDoPedido = itemDoPedidoService.saveItemDoPedido(itemDoPedido);
        return ResponseEntity.ok(newItemDoPedido);
    }

    @GetMapping
    public ResponseEntity<List<ItemDoPedido>> getAllItemDoPedido() {
        List<ItemDoPedido> itensDoPedido = itemDoPedidoService.getAllItemDoPedido();
        return ResponseEntity.ok(itensDoPedido);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemDoPedido> getItemDoPedidoById(@PathVariable Long id) {
        ItemDoPedido itemDoPedido = itemDoPedidoService.getItemDoPedidoById(id);
        if (itemDoPedido != null) {
            return ResponseEntity.ok(itemDoPedido);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemDoPedido> updateItemDoPedido(@PathVariable Long id, @RequestBody ItemDoPedido updateItemDoPedido) {
        ItemDoPedido updatedItemDoPedido = itemDoPedidoService.updateItemDoPedido(id, updateItemDoPedido);
        if (updatedItemDoPedido != null) {
            return ResponseEntity.ok(updatedItemDoPedido);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItemDoPedido(@PathVariable Long id) {
        itemDoPedidoService.deleteItemDoPedido(id);
        return ResponseEntity.noContent().build();
    }
}
