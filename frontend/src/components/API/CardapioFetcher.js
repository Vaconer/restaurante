import { useEffect } from 'react';

const CardapioFetcher = ({ setCardData }) => {
  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await fetch('http://localhost:8080/items'); 
        const data = await response.json();
        
        // Transformar a resposta em um formato compatível com o cardápio
        const transformedData = data.reduce((acc, item) => {
          const existingCategory = acc.find(card => card.title === item.title);
          // Adicionar a imagem ao objeto item
          const newItem = { name: item.nome, price: item.price, image: item.image }; 
          
          if (existingCategory) {
            existingCategory.items.push(newItem);
          } else {
            acc.push({ title: item.title, items: [newItem] });
          }
          return acc;
        }, []);
        
        setCardData(transformedData); // Armazena os dados transformados no estado do componente pai
      } catch (error) {
        console.error('Erro ao buscar dados da API:', error);
      }
    };

    fetchData(); // Chama a função para buscar os dados
  }, [setCardData]); // O efeito é chamado uma vez ao montar o componente

  return null; // Este componente não renderiza nada
};

export default CardapioFetcher;
