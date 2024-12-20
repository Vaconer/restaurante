package com.example.backend.menuCardapio;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.example.backend.restaurante.controller.menu.ItemController;
import com.example.backend.restaurante.model.menu.Item;
import com.example.backend.restaurante.service.menu.ItemService;

class ItemControllerTest {

    @InjectMocks
    private ItemController itemController;

    @Mock
    private ItemService itemService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateItem() {
        Item mockItem = new Item();
        mockItem.setId(1L);
        mockItem.setNome("Pizza");
        mockItem.setDescricao("Pizza deliciosa de queijo");
        mockItem.setPrice(BigDecimal.valueOf(20.50));
        mockItem.setImage("pizza.jpg");
        mockItem.setAvailability(true);

        when(itemService.saveItem(any(Item.class))).thenReturn(mockItem);

        ResponseEntity<Item> response = itemController.createItem(mockItem);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockItem, response.getBody());
        assertEquals("Pizza", response.getBody().getNome());
        assertEquals("Pizza deliciosa de queijo", response.getBody().getDescricao());
        assertEquals(BigDecimal.valueOf(20.50), response.getBody().getPrice());
        assertEquals("pizza.jpg", response.getBody().getImage());
        assertEquals(true, response.getBody().getAvailability());
        verify(itemService, times(1)).saveItem(mockItem);
    }
}
