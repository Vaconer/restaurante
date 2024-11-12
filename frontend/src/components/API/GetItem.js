import React, { useEffect } from 'react';
import axios from 'axios';

const CardapioFetcher = ({ setCardData }) => {
  useEffect(() => {
    axios.get('http://localhost:8080/api/items')
      .then(response => {
        // Certifique-se de que a resposta tem o formato esperado
        const formattedData = response.data.map(card => ({
          ...card,
          items: Array.isArray(card.items) ? card.items : []  // Garantir que `items` é um array
        }));
        setCardData(formattedData);
      })
      .catch(error => {
        console.error("Erro ao buscar itens:", error);
      });
  }, [setCardData]);

  return null;
};

export default CardapioFetcher;
