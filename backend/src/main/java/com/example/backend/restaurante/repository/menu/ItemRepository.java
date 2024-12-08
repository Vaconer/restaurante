package com.example.backend.restaurante.repository.menu;

import com.example.backend.restaurante.model.menu.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
