import React, { useState, useEffect } from 'react';
import './Cardapio.css';
import axios from 'axios';
import { ToastContainer, toast } from 'react-toastify';  // Importando o react-toastify
import 'react-toastify/dist/ReactToastify.css';  // Importando o estilo do react-toastify

function Cardapio() {
  const [cardData, setCardData] = useState([]); // Dados do cardápio
  const [newItem, setNewItem] = useState({ nome: '', descricao: '', price: '', image: '' });
  const [editItem, setEditItem] = useState(null); // Dados do item sendo editado
  const [isAddItemModalOpen, setIsAddItemModalOpen] = useState(false); // Estado para controlar o modal de adição de item

  // 1️⃣ Buscar itens do cardápio ao carregar a página
  useEffect(() => {
    axios.get('http://localhost:8080/api/item')
      .then(response => {
        console.log(response.data);  // Verifique a resposta da API
        setCardData(response.data);
      })
      .catch(error => {
        console.error("Erro ao buscar os itens", error);
      });
  }, []);

  // 2️⃣ Criar um novo item
  const handleAddItem = () => {
    axios.post('http://localhost:8080/api/item', newItem)
      .then(response => {
        setCardData([...cardData, response.data]);
        setNewItem({ nome: '', descricao: '', price: '', image: '' }); // Limpar o formulário
        setIsAddItemModalOpen(false); // Fechar o modal após adicionar
        toast.success('Item adicionado com sucesso!'); // Notificação de sucesso
      })
      .catch(error => {
        console.error("Erro ao adicionar item", error);
        toast.error('Erro ao adicionar o item. Tente novamente.'); // Notificação de erro
      });
  };

  // 3️⃣ Editar um item
  const handleEditItem = (item) => {
    setEditItem(item); // Definir o item a ser editado
  };

  const handleSaveEdit = () => {
    axios.put(`http://localhost:8080/api/item/${editItem.id}`, editItem)
      .then(response => {
        const updatedData = cardData.map(item =>
          item.id === editItem.id ? response.data : item
        );
        setCardData(updatedData);
        setEditItem(null); // Fechar o modal de edição
        toast.success('Item atualizado com sucesso!'); // Notificação de sucesso
      })
      .catch(error => {
        console.error("Erro ao editar item", error);
        toast.error('Erro ao atualizar o item. Tente novamente.'); // Notificação de erro
      });
  };

  // 4️⃣ Excluir um item
  const handleDeleteItem = (itemId) => {
    axios.delete(`http://localhost:8080/api/item/${itemId}`)
      .then(() => {
        setCardData(cardData.filter(item => item.id !== itemId));
        toast.success('Item excluído com sucesso!'); // Notificação de sucesso
      })
      .catch(error => {
        console.error("Erro ao excluir item", error);
        toast.error('Erro ao excluir o item. Tente novamente.'); // Notificação de erro
      });
  };

  return (
    <div className='cardapio'>
      <div className="Topo">
        <h1>Cardápio</h1>
        <h4>Receitas Fit feitas especialmente para você</h4>
        <button onClick={() => setIsAddItemModalOpen(true)}>Adicionar Novo Item</button>
      </div>

      {/* Formulário para adicionar novo item */}
      {isAddItemModalOpen && (
        <div className="modal">
          <div className="modal-content">
            <span className="close-modal" onClick={() => setIsAddItemModalOpen(false)}>&times;</span>
            <h3>Adicionar Novo Item</h3>
            <input
              type="text"
              placeholder="Nome"
              value={newItem.nome}
              onChange={(e) => setNewItem({ ...newItem, nome: e.target.value })}
            />
            <input
              type="text"
              placeholder="Descrição"
              value={newItem.descricao}
              onChange={(e) => setNewItem({ ...newItem, descricao: e.target.value })}
            />
            <input
              type="number"
              placeholder="Preço"
              value={newItem.price}
              onChange={(e) => setNewItem({ ...newItem, price: e.target.value })}
            />
            <input
              type="text"
              placeholder="URL da Imagem"
              value={newItem.image}
              onChange={(e) => setNewItem({ ...newItem, image: e.target.value })}
            />
            <button onClick={handleAddItem}>Adicionar Item</button>
            <button onClick={() => setIsAddItemModalOpen(false)}>Cancelar</button>
          </div>
        </div>
      )}

      {/* Listagem dos itens */}
      <div className="cards-container">
        {cardData.length > 0 ? (
          cardData.map((item) => (
            <div key={item.id} className="item-container">
              <img src={item.image} alt={item.nome} className="item-icon" />
              <div className="name-price">
                <p><strong>{item.nome}</strong></p>
                <p>{item.descricao}</p>
                <h3>R$ {item.price ? item.price.toFixed(2) : 'Preço não disponível'}</h3>
              </div>

              {/* Botões de Ação */}
              <div className="item-actions">
                <button onClick={() => handleEditItem(item)}>
                  <i className="fa fa-edit"></i> Editar
                </button>
                <button onClick={() => handleDeleteItem(item.id)}>
                  <i className="fa fa-trash"></i> Excluir
                </button>
              </div>
            </div>
          ))
        ) : (
          <p>Carregando itens...</p>
        )}

      </div>

      {/* Formulário para edição */}
      {editItem && (
        <div className="modal">
          <div className="modal-content">
            <span className="close-modal" onClick={() => setEditItem(null)}>&times;</span>
            <h3>Editar Item</h3>
            <input
              type="text"
              value={editItem.nome}
              onChange={(e) => setEditItem({ ...editItem, nome: e.target.value })}
            />
            <input
              type="text"
              value={editItem.descricao}
              onChange={(e) => setEditItem({ ...editItem, descricao: e.target.value })}
            />
            <input
              type="number"
              value={editItem.price}
              onChange={(e) => setEditItem({ ...editItem, price: e.target.value })}
            />
            <input
              type="text"
              value={editItem.image}
              onChange={(e) => setEditItem({ ...editItem, image: e.target.value })}
            />
            <button onClick={handleSaveEdit}>Salvar Alterações</button>
            <button onClick={() => setEditItem(null)}>Cancelar</button>
          </div>
        </div>
      )}

      {/* Container para as notificações */}
      <ToastContainer />
    </div>
  );
}

export default Cardapio;
