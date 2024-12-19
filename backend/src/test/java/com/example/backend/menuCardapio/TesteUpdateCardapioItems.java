package com.example.backend.menuCardapio;

import com.example.backend.restaurante.model.menu.Cardapio;
import com.example.backend.restaurante.model.menu.Item;
import com.example.backend.restaurante.repository.menu.CardapioRepository;
import com.example.backend.restaurante.repository.menu.ItemRepository;
import com.example.backend.restaurante.service.menu.CardapioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class TesteUpdateCardapioItems {

    @Mock
    private CardapioRepository cardapioRepository;

    @Mock
    private ItemRepository itemRepository;

    @InjectMocks
    private CardapioService cardapioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testUpdateCardapioItems_ValidCardapioIdAndItemIds() {
        Long cardapioId = 1L;
        List<Long> itemIds = Arrays.asList(2L, 3L);

        Cardapio cardapio = new Cardapio();
        cardapio.setId(cardapioId);

        Item item1 = new Item();
        item1.setId(2L);
        Item item2 = new Item();
        item2.setId(3L);

        Mockito.when(cardapioRepository.findById(cardapioId)).thenReturn(Optional.of(cardapio));
        Mockito.when(itemRepository.findAllById(itemIds)).thenReturn(Arrays.asList(item1, item2));
        Mockito.when(cardapioRepository.save(cardapio)).thenReturn(cardapio);

        Cardapio updatedCardapio = cardapioService.updateCardapioItems(cardapioId, itemIds);

        assertNotNull(updatedCardapio);
        assertEquals(itemIds.size(), updatedCardapio.getItem().size());
        assertTrue(updatedCardapio.getItem().containsAll(Arrays.asList(item1, item2)));
    }

    //Test verifica validacao id com cardapio
    @Test
    void testUpdateCardapioItems_InvalidCardapioId() {
        Long cardapioId = 999L;
        List<Long> itemIds = Arrays.asList(2L, 3L);

        Mockito.when(cardapioRepository.findById(cardapioId)).thenReturn(Optional.empty());

        Cardapio updatedCardapio = cardapioService.updateCardapioItems(cardapioId, itemIds);

        assertNull(updatedCardapio);
    }
}
