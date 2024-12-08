package com.example.backend.restaurante.model.menu;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Cardapio {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private  String title;

    @ManyToMany
    @JoinTable(
            name = "cardapio_item",
            joinColumns = @JoinColumn(name = "cardapio_id"),
            inverseJoinColumns =  @JoinColumn(name = "item_id")
    )
    private Set<Item> item;

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

    public Set<Item> getItem() {
        return item;
    }

    public void setItem(Set<Item> item) {
        this.item = item;
    }
}
