package com.example.backend.restaurante.model.order;

import com.example.backend.restaurante.model.user.Endereco;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class DadosDeEntregaDoPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id", referencedColumnName = "id")
    private Endereco enderecoEntrega;


    @OneToOne(mappedBy = "dadosDeEntregaDoPedido")
    private Pedido pedido;

    private LocalDateTime dataHoraEntregaEstimada;
    private String statusEntrega;
    private String nomeEntregador;

    public DadosDeEntregaDoPedido(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Endereco getEnderecoEntrega() {
        return enderecoEntrega;
    }

    public void setEnderecoEntrega(Endereco enderecoEntrega) {
        this.enderecoEntrega = enderecoEntrega;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public LocalDateTime getDataHoraEntregaEstimada() {
        return dataHoraEntregaEstimada;
    }

    public void setDataHoraEntregaEstimada(LocalDateTime dataHoraEntregaEstimada) {
        this.dataHoraEntregaEstimada = dataHoraEntregaEstimada;
    }

    public String getStatusEntrega() {
        return statusEntrega;
    }

    public void setStatusEntrega(String statusEntrega) {
        this.statusEntrega = statusEntrega;
    }

    public String getNomeEntregador() {
        return nomeEntregador;
    }

    public void setNomeEntregador(String nomeEntregador) {
        this.nomeEntregador = nomeEntregador;
    }
}
