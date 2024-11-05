import React, { useEffect, useState } from 'react';
import './Cart.css';

const Cart = ({ cart, setIsModalOpen, getTotalItems, removeFromCart, addToCart, finalizarCompra }) => {
  const [itemsData, setItemsData] = useState([]); // Estado para armazenar os dados dos itens

  useEffect(() => {
    // Função para buscar os dados da API
    const fetchItemsData = async () => {
      try {
        const response = await fetch('http://localhost:8080/items'); // Altere a URL conforme necessário
        const data = await response.json();
        setItemsData(data); // Armazena os dados recebidos no estado
      } catch (error) {
        console.error('Erro ao buscar os dados dos itens:', error);
      }
    };

    fetchItemsData(); // Chama a função para buscar os dados ao montar o componente
  }, []);

  // Função para obter o preço de um item baseado no nome
  const getPriceByName = (name) => {
    const item = itemsData.find(item => item.nome === name); // Procura o item pelo nome
    return item ? item.price : 0; // Retorna o preço ou 0 se não encontrado
  };

  // Função para calcular o preço total
  const getTotalPrice = () => {
    return Object.entries(cart).reduce((total, [name, quantity]) => {
      const price = getPriceByName(name); // Obtenha o preço do item
      return total + (quantity * price); // Multiplica a quantidade pelo preço e acumula
    }, 0);
  };

  const totalPrice = getTotalPrice(); // Total do carrinho

  return (
    <div className="modal">
      <div className="modal-content">
        <h2>Carrinho</h2>
        {getTotalItems() === 0 ? (
          <p>Seu carrinho está vazio.</p>
        ) : (
          <ul>
            {Object.entries(cart).map(([name, quantity], index) => (
              quantity > 0 && (
                <li key={index}>
                  {name} - <strong>R$ {getPriceByName(name).toFixed(2)}</strong>
                  <div className="quantity-control">
                    <button className="decrease-button" onClick={() => removeFromCart({ name })}>-</button>
                    <span>{quantity}</span>
                    <button className="increase-button" onClick={() => addToCart({ name })}>+</button>
                  </div>
                </li>
              )
            ))}
          </ul>
        )}

        {/* Exibir o valor total do carrinho */}
        <div className="total-price">
          <strong>Valor Total: R$ {totalPrice.toFixed(2)}</strong>
        </div>

        <div className="modal-actions">
          <button className="finalizar-compra" onClick={finalizarCompra}>
            Finalizar Compra
          </button>
          <button className="fechar-modal" onClick={() => setIsModalOpen(false)}>
            Fechar
          </button>
        </div>
      </div>
    </div>
  );
};

export default Cart;
