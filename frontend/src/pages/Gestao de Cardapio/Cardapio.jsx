import React, { useState } from 'react';
import './Cardapio.css';
import CardapioFetcher from '../../components/API/GetItem';
import axios from 'axios';
import { toast, ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import { FaEdit, FaTrashAlt } from 'react-icons/fa'; // Importando os ícones de lápis e lixeira

function Cardapio() {
  const [cardData, setCardData] = useState([]); // Estado para armazenar os dados do cardápio
  const [newItem, setNewItem] = useState({
    name: '',
    price: '',
    title: '',
    image: ''
  });
  const [isAdding, setIsAdding] = useState(false); // Controla a exibição do formulário de adição
  const [editingItem, setEditingItem] = useState(null); // Estado para o item em edição

  // Função para agrupar os itens pelo título
  const groupItemsByTitle = (items) => {
    return items.reduce((groups, item) => {
      if (!groups[item.title]) {
        groups[item.title] = [];
      }
      groups[item.title].push(item);
      return groups;
    }, {});
  };

  // Função para adicionar um novo item ao backend
  const handleAddItem = (e) => {
    e.preventDefault();

    const itemToAdd = {
      nome: newItem.name,
      price: parseFloat(newItem.price),
      title: newItem.title,
      image: newItem.image
    };

    axios.post('http://localhost:8080/api/items', itemToAdd)
      .then(response => {
        setCardData(prevData => {
          const updatedData = [...prevData];
          updatedData[0].items.push(response.data);
          return updatedData;
        });
        setIsAdding(false);
        setNewItem({ name: '', price: '', title: '', image: '' });
        toast.success('Item adicionado com sucesso!');
      })
      .catch(error => {
        console.error("Erro ao adicionar o item", error);
      });
  };

  // Função para editar um item (alterar)
  const handleEditItem = (item) => {
    setEditingItem(item);
  };

  const handleUpdateItem = (e) => {
    e.preventDefault();

    const updatedItem = {
      nome: editingItem.name,
      price: parseFloat(editingItem.price),
      title: editingItem.title,
      image: editingItem.image
    };

    axios.put(`http://localhost:8080/api/items/${editingItem.id}`, updatedItem)
      .then(response => {
        const updatedData = cardData.map(card => {
          return {
            ...card,
            items: card.items.map(item =>
              item.id === editingItem.id ? response.data : item
            )
          };
        });
        setCardData(updatedData);
        setEditingItem(null);
        toast.success('Item atualizado com sucesso!');
      })
      .catch(error => {
        console.error("Erro ao atualizar o item", error);
      });
  };

  // Função para excluir um item
  const handleDeleteItem = (itemId) => {
    axios.delete(`http://localhost:8080/api/items/${itemId}`)
      .then(() => {
        const updatedData = cardData.map(card => ({
          ...card,
          items: card.items.filter(item => item.id !== itemId)
        }));
        setCardData(updatedData);
        toast.success('Item excluído com sucesso!');
      })
      .catch(error => {
        console.error("Erro ao excluir o item", error);
      });
  };

  // Função para alternar a visibilidade do formulário de adição
  const toggleAddItem = () => {
    setIsAdding(!isAdding);
  };

  // Função para alternar a visibilidade do formulário de edição
  const toggleEditItem = () => {
    setEditingItem(null);
  };

  return (
    <div className='cardapio'>
      <CardapioFetcher setCardData={setCardData} />
      <div className="Topo">
        <h1>Cardápio</h1>
        <h4>Receitas Fit feitas especialmente para você</h4>
      </div>

      <button onClick={toggleAddItem} className="add-item-button">
        {isAdding ? 'Cancelar' : 'Adicionar Item'}
      </button>

      {isAdding && (
        <div className="modal-overlay">
          <div className="modal-content">
            <h3>Adicionar Novo Item</h3>
            <form onSubmit={handleAddItem}>
              <input
                type="text"
                placeholder="Nome do Item"
                value={newItem.name}
                onChange={(e) => setNewItem({ ...newItem, name: e.target.value })}
                required
              />
              <input
                type="text"
                placeholder="Título"
                value={newItem.title}
                onChange={(e) => setNewItem({ ...newItem, title: e.target.value })}
                required
              />
              <input
                type="number"
                placeholder="Preço"
                value={newItem.price}
                onChange={(e) => setNewItem({ ...newItem, price: e.target.value })}
                required
              />
              <input
                type="text"
                placeholder="URL da Imagem"
                value={newItem.image}
                onChange={(e) => setNewItem({ ...newItem, image: e.target.value })}
                required
              />
              <button type="submit">Adicionar Item</button>
              <button type="button" onClick={toggleAddItem}>Cancelar</button>
            </form>
          </div>
        </div>
      )}

      {editingItem && (
        <div className="modal-overlay">
          <div className="modal-content">
            <h3>Editar Item</h3>
            <form onSubmit={handleUpdateItem}>
              <input
                type="text"
                placeholder="Nome do Item"
                value={editingItem.name}
                onChange={(e) => setEditingItem({ ...editingItem, name: e.target.value })}
                required
              />
              <input
                type="text"
                placeholder="Título"
                value={editingItem.title}
                onChange={(e) => setEditingItem({ ...editingItem, title: e.target.value })}
                required
              />
              <input
                type="number"
                placeholder="Preço"
                value={editingItem.price}
                onChange={(e) => setEditingItem({ ...editingItem, price: e.target.value })}
                required
              />
              <input
                type="text"
                placeholder="URL da Imagem"
                value={editingItem.image}
                onChange={(e) => setEditingItem({ ...editingItem, image: e.target.value })}
                required
              />
              <button type="submit">Atualizar Item</button>
              <button type="button" onClick={toggleEditItem}>Cancelar</button>
            </form>
          </div>
        </div>
      )}

      <div>
        <div className="container">
          <div className="cards-container">
            {cardData.length > 0 ? (
              Object.keys(groupItemsByTitle(cardData)).map((title, index) => (
                <div key={index} className="card">
                  <span className="title">{title}</span>
                  <div className="card-content">
                    {groupItemsByTitle(cardData)[title].map((item, i) => (
                      <div key={i} className="item-container">
                        <div className="img-item">
                          <img src={item.image} alt={item.nome} className="item-icon" />
                          <div className="name-price">
                            <p>{i + 1}. {item.nome}</p>
                            <h3>R$ {item.price}</h3>
                          </div>
                        </div>
                        
                        <div className="item-actions">
                          {/* Alterar: Ícone de lápis */}
                          <button onClick={() => handleEditItem(item)} className="edit-button">
                            <FaEdit />
                          </button>

                          {/* Excluir: Ícone de lixeira */}
                          <button onClick={() => handleDeleteItem(item.id)} className="delete-button">
                            <FaTrashAlt />
                          </button>
                        </div>

                        <hr className="separator" />
                      </div>
                    ))}
                  </div>
                </div>
              ))
            ) : (
              <p>Carregando itens...</p>
            )}
          </div>
        </div>
      </div>

      <ToastContainer />
    </div>
  );
}

export default Cardapio;
