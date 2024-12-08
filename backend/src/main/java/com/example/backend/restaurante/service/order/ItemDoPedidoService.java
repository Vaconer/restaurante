package com.example.backend.restaurante.service.order;

import com.example.backend.restaurante.model.order.ItemDoPedido;
import com.example.backend.restaurante.repository.order.ItemDoPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemDoPedidoService {

    @Autowired
    private ItemDoPedidoRepository itemDoPedidoRepository;

    public ItemDoPedido saveItemDoPedido(ItemDoPedido itemDoPedido) {
        return itemDoPedidoRepository.save(itemDoPedido);
    }

    public List<ItemDoPedido> getAllItemDoPedido() {
        return itemDoPedidoRepository.findAll();
    }

    public ItemDoPedido getItemDoPedidoById(Long id) {
        Optional<ItemDoPedido> itemDoPedido = itemDoPedidoRepository.findById(id);
        return itemDoPedido.orElse(null);
    }

    public ItemDoPedido updateItemDoPedido(Long id, ItemDoPedido updateItemDoPedido) {
        Optional<ItemDoPedido> itemDoPedido = itemDoPedidoRepository.findById(id);
        if (itemDoPedido.isPresent()) {
            ItemDoPedido existingItemDoPedido = itemDoPedido.get();
            existingItemDoPedido.setItem(updateItemDoPedido.getItem());
            existingItemDoPedido.setPedido(updateItemDoPedido.getPedido());
            existingItemDoPedido.setQuantidade(updateItemDoPedido.getQuantidade());
            existingItemDoPedido.setPrecoUnitario(updateItemDoPedido.getPrecoUnitario());
            existingItemDoPedido.setPrecoTotal(updateItemDoPedido.getPrecoTotal());

            return itemDoPedidoRepository.save(existingItemDoPedido);
        } else {
            return null;
        }
    }

    public void deleteItemDoPedido(Long id) {
        itemDoPedidoRepository.deleteById(id);
    }
}
