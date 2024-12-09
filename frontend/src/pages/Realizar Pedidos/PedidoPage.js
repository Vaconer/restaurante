import React, { useState } from 'react';
import axios from 'axios';

const PedidoPage = () => {
  const [pedido, setPedido] = useState({
    usuario: {
      id: 1, // ID fictício do usuário
    },
    itensDoPedido: [
      {
        item: {
          id: 1, // ID fictício do item
        },
        quantidade: 2,
        precoUnitario: 10.0,
        precoTotal: 20.0,
      },
      {
        item: {
          id: 2, // ID fictício do item
        },
        quantidade: 1,
        precoUnitario: 15.0,
        precoTotal: 15.0,
      },
    ],
    dadosDoPagamento: {
      tipoPagamento: 'Cartão de Crédito',
      troco: 0,
      statusPagamento: 'Pendente',
      dataPagamento: new Date(),
    },
    dadosDeEntregaDoPedido: {
      enderecoEntrega: {
        rua: 'Rua Exemplo',
        numero: 123,
        bairro: 'Bairro Exemplo',
        cidade: 'Cidade Exemplo',
        estado: 'Estado Exemplo',
        cep: '12345-678',
      },
      dataHoraEntregaEstimada: new Date(),
      statusEntrega: 'Pendente',
      nomeEntregador: 'Entregador Exemplo',
    },
  });

  const handleSubmit = async () => {
    try {
      // 1. Criando o Pedido
      const pedidoResponse = await axios.post('http://localhost:8080/api/pedidos', pedido);
      const pedidoId = pedidoResponse.data.id;

      // 2. Criando os Dados de Pagamento
      const pagamentoResponse = await axios.post('http://localhost:8080/api/dadosDoPagamento', {
        ...pedido.dadosDoPagamento,
        pedido: { id: pedidoId },
      });

      // 3. Criando os Dados de Entrega
      const entregaResponse = await axios.post('http://localhost:8080/api/dadosDeEntregaDoPedido', {
        ...pedido.dadosDeEntregaDoPedido,
        pedido: { id: pedidoId },
      });

      // 4. Criando os Itens do Pedido
      for (let item of pedido.itensDoPedido) {
        await axios.post('http://localhost:8080/api/itemDoPedido', {
          ...item,
          pedido: { id: pedidoId },
        });
      }

      alert('Pedido criado com sucesso!');
    } catch (error) {
      console.error('Erro ao criar pedido:', error);
      alert('Erro ao criar pedido');
    }
  };

  return (
    <div>
      <h1>Criar Pedido</h1>
      <button onClick={handleSubmit}>Criar Pedido</button>
    </div>
  );
};

export default PedidoPage;
