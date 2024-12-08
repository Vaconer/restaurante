package com.example.backend.restaurante.model.user;

import jakarta.persistence.*;

@Entity
public class DadosCartao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    private String numeroCartao;
    private String nomeTitular;
    private String validadeCartao;
    private String cvv;

    public DadosCartao(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getNumeroCartao(){
        return numeroCartao;
    }

    public void setNumeroCartao(String numeroCartao){
        this.numeroCartao = numeroCartao;
    }

    public String getNomeTitular(){
        return nomeTitular;
    }

    public void setNomeTitular(String nomeTitular){
        this.nomeTitular = nomeTitular;
    }

    public String getValidadeCartao(){
        return validadeCartao;
    }

    public void setValidadeCartao(String validadeCartao){
        this.validadeCartao = validadeCartao;
    }

    public String getCvv(){
        return cvv;
    }

    public void setCvv(String cvv){
        this.cvv = cvv;
    }
}
