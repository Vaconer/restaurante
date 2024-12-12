package com.example.backend.restaurante.model.menu;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
public class Cardapio {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private  String title;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "cardapio_item",
            joinColumns = @JoinColumn(name = "cardapio_id"),
            inverseJoinColumns =  @JoinColumn(name = "item_id")
    )
    @JsonIgnoreProperties("cardapio")
    private List<Item> item;

    public Cardapio(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Item> getItem() {
        return item;
    }

    public void setItem(List<Item> item) {
        this.item = item;
    }

    public void removeItem(Item item) {
        this.item.remove(item); item.getCardapio().remove(this);
    }
}
