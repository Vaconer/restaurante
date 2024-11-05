import React from 'react';
import './Card.css';

const CardItem = ({ card, addToCart, removeFromCart, cart }) => {
  return (
    <div className="card">
      <span className="title">{card.title}</span>
      <br />
      <div className="card-content">
        {card.items.map((item, i) => {
          console.log('Imagem:', item.image); // Verifica se o caminho da imagem está correto
          return (
            <div key={i}>
              <div className="item-row">
                <div className="img-item">
                  <img
                    src={item.image} // Acesse a imagem corretamente aqui
                    alt="Item icon"
                    className="item-icon"
                  />
                </div>
                <div className='name-price'>
                  <p>{i + 1}. {item.name}</p>
                  <h3>R$ {item.price}</h3>
                </div>
                <div className="quantity-control">
                  <button onClick={() => removeFromCart(item)}>-</button>
                  <span>{cart[item.name] || 0}</span>
                  <button onClick={() => addToCart(item)}>+</button>
                </div>
              </div>
              <div className="separator">
                {i < card.items.length - 1 && <hr />}
              </div>  
            </div>
          );
        })}
      </div>
    </div>
  );
};

export default CardItem;
