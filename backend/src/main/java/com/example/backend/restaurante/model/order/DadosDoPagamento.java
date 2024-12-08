package com.example.backend.restaurante.model.order;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class DadosDoPagamento  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(mappedBy = "dadosDoPagamento")
    private Pedido pedido;

    private String tipoPagamento;
    private Integer troco;
    private String statusPagamento;
    private LocalDateTime dataPagamento;

    public DadosDoPagamento(){

    }

    public Integer getTroco() {
        return troco;
    }

    public void setTroco(Integer troco) {
        this.troco = troco;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public String getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(String tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public String getStatusPagamento() {
        return statusPagamento;
    }

    public void setStatusPagamento(String statusPagamento) {
        this.statusPagamento = statusPagamento;
    }

    public LocalDateTime getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDateTime dataPagamento) {
        this.dataPagamento = dataPagamento;
    }
}
