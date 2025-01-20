package com.example.backend.restaurante.service.menu;

import com.example.backend.restaurante.model.menu.Item;
import com.example.backend.restaurante.service.menu.ItemService;
import com.example.backend.restaurante.repository.menu.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;

    public String uploadImage(MultipartFile file) throws IOException {
        // Substitua pela lógica de upload para o serviço de armazenamento desejado
        // Exemplo fictício:
        String imageUrl = "https://flickrexample.com/uploads/" + file.getOriginalFilename();
        return imageUrl;
    }

    public Item saveItem(Item item) {
        return itemRepository.save(item);
    }

    public List<Item> getAllItem() {
        return itemRepository.findAll();
    }

    public Item getItemById(Long id) {
        return itemRepository.findById(id).orElse(null);
    }

    public Item updateItem(Long id, Item updateItem) {
        if (itemRepository.existsById(id)) {
            updateItem.setId(id);
            return itemRepository.save(updateItem);
        }
        return null;
    }

    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
    }
}
