import React, { useState } from 'react';
import './Cardapio.css';
import CardapioFetcher from '../../components/API/CardapioFetcher';

function Cardapio() {
  const [cardData, setCardData] = useState([]); // Estado para armazenar os dados do cardápio

  return (
    <div className='cardapio'>
      <CardapioFetcher setCardData={setCardData} /> {/* Chama o fetcher */}
      <div className="Topo">
        <h1>Cardápio</h1>
        <h4>Receitas Fit feitas especialmente para você</h4>
      </div>
      <div className="container">
        <div className="cards-container">
          {cardData.map((card, index) => (
            <div className="card" key={index}>
              <span className="title">{card.title}</span>
              <div className="card-content">
                {card.items.map((item, i) => (
                  <div key={i} className="item-container">
                    <div className='img-item'>
                      <img
                        src={item.image}
                        alt="Item icon"
                        className="item-icon"
                      />
                      <div className='name-price'>
                        <p>{i + 1}. {item.name}</p>
                        <h3>R$ {item.price}</h3>
                      </div>
                    </div>
                    <hr className="separator" />
                  </div>
                ))}
              </div>
            </div>
          ))}
        </div>
      </div>
    </div>
  );
}

export default Cardapio;
