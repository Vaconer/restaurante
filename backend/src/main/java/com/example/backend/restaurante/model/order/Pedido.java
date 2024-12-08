package com.example.backend.restaurante.model.order;

import com.example.backend.restaurante.model.user.Usuario;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Usuario usuario;

    @OneToMany(mappedBy = "pedido")
    private Set<ItemDoPedido> itensDoPedido;

    @OneToOne
    @JoinColumn(name = "dados_do_pagamento_id", referencedColumnName = "id")
    private DadosDoPagamento dadosDoPagamento;

    @OneToOne
    @JoinColumn(name = "dados_da_entrega_id", referencedColumnName = "id")
    private DadosDeEntregaDoPedido dadosDeEntregaDoPedido;

    private LocalDateTime dataPedido;
    private String status;

    public Pedido(){

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

    public Set<ItemDoPedido> getItensDoPedido() {
        return itensDoPedido;
    }

    public void setItensDoPedido(Set<ItemDoPedido> itensDoPedido) {
        this.itensDoPedido = itensDoPedido;
    }

    public DadosDoPagamento getDadosDoPagamento() {
        return dadosDoPagamento;
    }

    public void setDadosDoPagamento(DadosDoPagamento dadosDoPagamento) {
        this.dadosDoPagamento = dadosDoPagamento;
    }

    public DadosDeEntregaDoPedido getDadosDeEntregaDoPedido() {
        return dadosDeEntregaDoPedido;
    }

    public void setDadosDeEntregaDoPedido(DadosDeEntregaDoPedido dadosDeEntregaDoPedido) {
        this.dadosDeEntregaDoPedido = dadosDeEntregaDoPedido;
    }

    public LocalDateTime getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(LocalDateTime dataPedido) {
        this.dataPedido = dataPedido;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
