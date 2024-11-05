import React, { useState } from 'react';
import './RealizarPedidos.css';
import Cart from './Carrinho/Cart';
import CardItem from './Card/CardItem';
import CardapioFetcher from '../../components/API/CardapioFetcher'; 

function RealizarPedidos() {
  const [cards, setCards] = useState([]); // Estado para armazenar os cards organizados
  const [cart, setCart] = useState({});
  const [isModalOpen, setIsModalOpen] = useState(false);

  // Adiciona ao carrinho, ou aumenta a quantidade se já existir
  const addToCart = (item) => {
    setCart((prevCart) => ({
      ...prevCart,
      [item.name]: (prevCart[item.name] || 0) + 1, // Usa o nome como chave no carrinho
    }));
  };

  // Remove do carrinho, sem permitir quantidade negativa
  const removeFromCart = (item) => {
    setCart((prevCart) => ({
      ...prevCart,
      [item.name]: Math.max((prevCart[item.name] || 0) - 1, 0),
    }));
  };

  // Calcula o número total de itens no carrinho
  const getTotalItems = () => {
    return Object.values(cart).reduce((total, quantity) => total + quantity, 0);
  };

  // Limpa o carrinho ao finalizar a compra
  const finalizarCompra = () => {
    setCart({});
    setIsModalOpen(false); // Fecha o modal após finalizar a compra
  };

  return (
    <div className='RealizarPedidos'>
      <CardapioFetcher setCardData={setCards} /> {/* Chama o fetcher e passa os dados para o estado */}

      <div className="Topo">
        <div className="cart-icon" onClick={() => setIsModalOpen(true)}>
          <span role="img" aria-label="cart">🛒</span>
          <span className="cart-count">{getTotalItems()}</span>
        </div>
        <h1>Realizar Pedidos</h1>
        <h4>Escolha os itens que deseja pedir</h4>
      </div>

      <div className="container">
        <div className="cards-container">
          {cards.map((card, index) => (
            <CardItem
              key={index}
              card={card} // Passa o card organizado
              addToCart={addToCart}
              removeFromCart={removeFromCart}
              cart={cart}
            />
          ))}
        </div>
      </div>

      {/* Modal do carrinho */}
      {isModalOpen && (
        <Cart
          cart={cart}
          setIsModalOpen={setIsModalOpen}
          getTotalItems={getTotalItems}
          removeFromCart={removeFromCart}
          addToCart={addToCart}
          finalizarCompra={finalizarCompra}
        />
      )}
    </div>
  );
}

export default RealizarPedidos;
