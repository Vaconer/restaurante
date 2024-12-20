import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './PedidoPage.css';

const PedidoPage = () => {
  const [cardapio, setCardapio] = useState([]);
  const [pedido, setPedido] = useState([]);
  const [modalItem, setModalItem] = useState(null);

  // Buscar dados do cardápio no backend
  useEffect(() => {
    axios.get('http://localhost:8080/api/cardapio') // Substitua pela URL da sua API
      .then((response) => {
        // Mapeando os itens de todas as categorias
        const items = response.data.reduce((acc, categoria) => {
          return acc.concat(categoria.item);
        }, []);
        setCardapio(items); // Atualiza o estado com todos os itens do cardápio
      })
      .catch((error) => {
        console.error('Erro ao carregar o cardápio:', error);
        alert('Ocorreu um erro ao carregar o cardápio. Tente novamente mais tarde.');
      });
  }, []);

  // Adicionar item ao pedido (verificando duplicatas)
  const adicionarAoPedido = (item) => {
    if (!pedido.some((pedidoItem) => pedidoItem.id === item.id)) {
      setPedido([...pedido, item]);
    }
  };

  // Remover item do pedido
  const removerDoPedido = (id) => {
    setPedido(pedido.filter((item) => item.id !== id));
  };

  // Finalizar pedido
  const finalizarPedido = () => {
    axios.post('/api/pedidos', { items: pedido }) // Supondo que você tenha um endpoint para salvar o pedido
      .then((response) => {
        alert('Pedido finalizado com sucesso!');
        setPedido([]); // Limpar o pedido após finalização
      })
      .catch((error) => {
        alert('Erro ao finalizar o pedido. Tente novamente.');
      });
  };

  return (
    <div className="pedido-page">
      <h1>Realizar Pedido</h1>

      <div className="cardapio">
        <h2>Cardápio</h2>
        <div className="itens-cardapio">
          {cardapio.map((item) => (
            <div key={item.id} className="item-card" onClick={() => setModalItem(item)}>
              <img src={item.image} alt={item.nome} />
              <p>{item.nome}</p>
              <p>{item.price.toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' })}</p>
            </div>
          ))}
        </div>
      </div>

      <div className="pedido">
        <h2>Seu Pedido</h2>
        <div className="itens-pedido">
          {pedido.map((item) => (
            <div key={item.id} className="item-pedido">
              <p>{item.nome} - {item.price.toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' })}</p>
              <button onClick={() => removerDoPedido(item.id)}>Remover</button>
            </div>
          ))}
        </div>
        {pedido.length > 0 && (
          <button onClick={finalizarPedido}>Finalizar Pedido</button>
        )}
      </div>

      {modalItem && (
        <div className="modal-overlay" onClick={() => setModalItem(null)}>
          <div className="modal" onClick={(e) => e.stopPropagation()}>
            <button className="modal-close" onClick={() => setModalItem(null)}>×</button>
            <img src={modalItem.image} alt={modalItem.nome} />
            <h3>{modalItem.nome}</h3>
            <p>{modalItem.descricao}</p>
            <p>{modalItem.price.toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' })}</p>
            <button onClick={() => { adicionarAoPedido(modalItem); setModalItem(null); }}>Adicionar ao Pedido</button>
          </div>
        </div>
      )}
    </div>
  );
};

export default PedidoPage;
