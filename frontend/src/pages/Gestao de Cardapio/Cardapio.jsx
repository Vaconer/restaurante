import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import './Cardapio.css';
import { FaTrash, FaEdit, FaPlus } from 'react-icons/fa';

function Cardapio() {
  const [menus, setMenus] = useState([]);
  const [items, setItems] = useState([]);
  const [newMenu, setNewMenu] = useState({ title: '' });
  const [newItem, setNewItem] = useState({
    nome: '',
    descricao: '',
    price: '',
    image: '',
    availability: true
  });
  const [selectedItems, setSelectedItems] = useState([]); // Itens selecionados para adicionar ao cardápio
  const [showAddMenuModal, setShowAddMenuModal] = useState(false); // Estado para controlar a exibição do modal de adicionar cardápio
  const [showAddItemModal, setShowAddItemModal] = useState(false); // Estado para controlar a exibição do modal de adicionar item
  const [menuToUpdate, setMenuToUpdate] = useState(null); // Armazena o cardápio que será atualizado
  const [itemToEdit, setItemToEdit] = useState(null); // Armazena o item a ser editado
  const [showEditItemModal, setShowEditItemModal] = useState(false);


  useEffect(() => {
<<<<<<< Updated upstream
    fetchMenus();
    fetchItems();
  }, []);

  // Função para buscar os menus
  const fetchMenus = async () => {
    try {
      const response = await axios.get('http://localhost:8080/api/cardapio');
      setMenus(response.data);
    } catch (error) {
      console.error("Erro ao buscar os cardápios", error);
      toast.error('Erro ao carregar cardápios.');
    }
  };

  // Função para buscar todos os itens
  const fetchItems = async () => {
    try {
      const response = await axios.get('http://localhost:8080/api/item');
      setItems(response.data);
    } catch (error) {
      console.error("Erro ao buscar os itens", error);
      toast.error('Erro ao carregar itens.');
    }
  };

  // Função para adicionar um novo item
  const handleAddNewItem = async () => {
    if (!newItem.nome || !newItem.price || !newItem.image) {
      toast.error('Por favor, preencha todos os campos.');
      return;
    }

    try {
      const response = await axios.post('http://localhost:8080/api/item', newItem);
      setItems([...items, response.data]);
      setNewItem({ nome: '', descricao: '', price: '', image: '', availability: true });
      setShowAddItemModal(false); // Fecha o modal de adicionar item
      toast.success('Item adicionado com sucesso!');
    } catch (error) {
      console.error("Erro ao adicionar item", error);
      toast.error('Erro ao adicionar o item. Tente novamente.');
    }
  };

  // Função para criar um novo cardápio
  const handleCreateNewMenu = async () => {
    if (!newMenu.title || selectedItems.length === 0) {
      toast.error('Digite o título do cardápio e adicione pelo menos um item.');
      return;
    }

    // Verificar se já existe um cardápio com o mesmo título
    const existingMenu = menus.find(menu => menu.title.toLowerCase() === newMenu.title.toLowerCase());

    if (existingMenu) {
      toast.error('Já existe um cardápio com este título.');
      return;
    }

    const newCardapio = {
      title: newMenu.title,
      itemIds: selectedItems.map(item => item.id)
    };

    try {
      const response = await axios.post('http://localhost:8080/api/cardapio/criar', newCardapio);
      setMenus([...menus, response.data]);
      setNewMenu({ title: '' });
      setSelectedItems([]); // Limpa a seleção dos itens
      setShowAddMenuModal(false); // Fecha o modal
      toast.success('Cardápio criado com sucesso!');
    } catch (error) {
      console.error("Erro ao criar o cardápio", error);
      toast.error('Erro ao criar o cardápio. Tente novamente.');
    }
  };

  // Função para excluir um cardápio
  const handleDeleteMenu = async (menuId) => {
    try {
      await axios.delete(`http://localhost:8080/api/cardapio/${menuId}`);
      setMenus(menus.filter(menu => menu.id !== menuId));
      toast.success('Cardápio excluído com sucesso!');
    } catch (error) {
      console.error("Erro ao excluir o cardápio", error);
      toast.error('Erro ao excluir o cardápio. Tente novamente.');
    }
  };

  // Função para excluir um item
  const handleDeleteItem = async (itemId) => {
    try {
      await axios.delete(`http://localhost:8080/api/item/${itemId}`);
      setItems(items.filter(item => item.id !== itemId));
      toast.success('Item excluído com sucesso!');
    } catch (error) {
      console.error("Erro ao excluir o item", error);
      toast.error('Erro ao excluir o item. Tente novamente.');
    }
=======
    getItem()
  }, []);

  const getItem = async () => {
    try {
      const response = await axios.get('http://localhost:8080/api/item')
      console.log(response.data);  // Verifique a resposta da API
      setCardData(response.data);

    } catch (e) {
      console.error("Erro ao buscar os itens", e);


    }

  }
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
>>>>>>> Stashed changes
  };

  const handleEditItem = (item) => {
    setItemToEdit(item);  // Define o item a ser editado
    setShowEditItemModal(true);  // Exibe o modal de edição
  };

  const handleSaveEditedItem = async () => {
    if (!itemToEdit.nome || !itemToEdit.price || !itemToEdit.image) {
      toast.error('Por favor, preencha todos os campos.');
      return;
    }

    try {
      const response = await axios.put(`http://localhost:8080/api/item/${itemToEdit.id}`, itemToEdit);
      const updatedItems = items.map((item) =>
        item.id === itemToEdit.id ? { ...item, ...itemToEdit } : item
      );
      setItems(updatedItems);  // Atualiza a lista de itens no estado
      setShowEditItemModal(false);  // Fecha o modal de edição
      toast.success('Item atualizado com sucesso!');
    } catch (error) {
      console.error("Erro ao editar o item", error);
      toast.error('Erro ao atualizar o item. Tente novamente.');
    }
  };



  // Função para selecionar/deselecionar itens para adicionar ao cardápio
  const toggleSelectItem = (item) => {
    if (selectedItems.some(selectedItem => selectedItem.id === item.id)) {
      setSelectedItems(selectedItems.filter(selectedItem => selectedItem.id !== item.id));
    } else {
      setSelectedItems([...selectedItems, item]);
    }
  };

  // Função para adicionar novos itens ao cardápio
  const handleAddItemsToMenu = async (menuId) => {
    if (selectedItems.length === 0) {
      toast.error('Por favor, selecione pelo menos um item.');
      return;
    }

    // Obter os itens já presentes no cardápio
    const menu = menus.find(menu => menu.id === menuId);
    const existingItems = menu ? menu.item : []; // Itens existentes no cardápio

    // Adicionar os novos itens selecionados ao array de itens existentes
    const updatedItems = [...existingItems, ...selectedItems]; // Mantém os itens anteriores e adiciona os novos

    try {
      // Enviar a requisição PUT com o array atualizado de itens
      const response = await axios.put(`http://localhost:8080/api/cardapio/${menuId}/itens`, updatedItems);

      // Verificar se a resposta foi bem-sucedida
      if (response.status === 200) {
        // Atualizar os itens do cardápio localmente
        const updatedMenus = menus.map(menu =>
          menu.id === menuId
            ? { ...menu, item: updatedItems }  // Atualiza o array de itens com o array atualizado
            : menu
        );

        setMenus(updatedMenus);  // Atualiza os menus no estado
        setSelectedItems([]);  // Limpar a seleção de itens
        toast.success('Itens adicionados com sucesso!');
      } else {
        toast.error('Erro ao adicionar itens. Tente novamente.');
      }
    } catch (error) {
      console.error("Erro ao adicionar itens ao cardápio", error);
      toast.error('Erro ao adicionar itens ao cardápio. Tente novamente.');
    }
  };

  // Função para atualizar os itens de um cardápio
  const handleUpdateMenuItems = async (menuId) => {
    if (selectedItems.length === 0) {
      toast.error('Por favor, selecione pelo menos um item.');
      return;
    }

    const menu = menus.find(menu => menu.id === menuId);
    const existingItems = menu ? menu.item : []; // Itens já presentes no cardápio

    // Atualiza o array de itens, adicionando os novos itens
    const updatedItems = [...existingItems, ...selectedItems]; // Mantém os itens anteriores e adiciona os novos

    try {
      // Enviar a requisição PUT com o array de IDs dos itens
      const itemIds = updatedItems.map(item => item.id);  // Extrai os IDs dos itens
      const response = await axios.put(`http://localhost:8080/api/cardapio/${menuId}/itens`, itemIds);

      // Verificar se a resposta foi bem-sucedida
      if (response.status === 200) {
        // Atualizar a lista de menus localmente
        const updatedMenus = menus.map(menu =>
          menu.id === menuId
            ? { ...menu, item: updatedItems }  // Atualiza o array de itens com o array atualizado
            : menu
        );

        setMenus(updatedMenus);
        setSelectedItems([]);  // Limpar a seleção de itens
        setMenuToUpdate(null);  // Limpar o cardápio a ser atualizado
        toast.success('Itens atualizados com sucesso!');
      } else {
        toast.error('Erro ao atualizar os itens. Tente novamente.');
      }
    } catch (error) {
      console.error("Erro ao atualizar os itens do cardápio", error);
      toast.error('Erro ao adicionar itens ao cardápio. Tente novamente.');
    }
  };




  return (



    <div className="cardapio">

      <h1>Cardápios</h1>

      <div className="botao-container">
        {/* Botão para abrir o modal de adicionar cardápio */}
        <div className="botao-com-texto">
          <button className='botao_add' onClick={() => setShowAddMenuModal(true)}>
            <FaPlus size={20} /> {/* Ícone de adição para Cardápio */}
          </button>
          <span>Cardápio</span> {/* Texto explicativo fora do botão */}
        </div>

        {/* Botão para abrir o modal de adicionar item */}
        <div className="botao-com-texto">
          <button className='botao_add' onClick={() => setShowAddItemModal(true)}>
            <FaPlus size={20} /> {/* Ícone de adição para Item */}
          </button>
          <span>Item</span> {/* Texto explicativo fora do botão */}
        </div>
      </div>


      {/* Lista de Cardápios */}
      <div className="menus-list">
        {menus.map((menu) => (
          <div className='menu-separator' key={menu.id} >

            <div className='exc_alt'>
              <h2>{menu.title}</h2>
              <div className='botao'>
                {/* Ícone de excluir cardápio */}
                <button className='deletar' onClick={() => handleDeleteMenu(menu.id)}>
                  <FaTrash size={20} /> {/* Ícone de lixeira */}
                </button>

                {/* Ícone de adicionar/alterar cardápio */}
                <button className='adicionar' onClick={() => setMenuToUpdate(menu)}>
                  <FaPlus size={20} /> {/* Ícone de lápis de edição */}
                </button>
              </div>
            </div>


            <div >
              {menu.item.map((item) => (
                <div key={item.id} >

                  <div className='menu-item'>
                    <img className='fotos' src={item.image} alt={item.nome} />

                    <div className='infos'>

                      <h2>
                        {item.nome}
                      </h2>

                      <p>
                        {item.descricao}
                      </p>

                      <p>
                        R$ {item.price}
                      </p>

                      <p>
                        {' '}
                        {item.availability ? 'Disponível' : 'Indisponível'}
                      </p>

                      <div className='botoes'>
                        <button className='deletar' onClick={() => handleDeleteItem(item.id)}>
                          <FaTrash /> {/* Ícone de lixeira */}
                        </button>
                        <button className='editar' onClick={() => handleEditItem(item)}>
                          <FaEdit /> {/* Ícone de edição */}
                        </button>
                      </div>

                    </div>

                  </div>

                </div>
              ))}
            </div>

          </div>
        ))}
      </div>










      {/*----------------------------------MODAL------------------------------}

      {/* Modal de Adicionar Novo Cardápio */}
      <div className={`modal ${showAddMenuModal ? 'active' : ''}`}>
        <div className="modal-content">
          <div className="modal-header">
            <h3>Adicionar Novo Cardápio</h3>
            <button className="modal-close" onClick={() => setShowAddMenuModal(false)}>&times;</button>
          </div>
          <div className="modal-body">
            <input
              type="text"
              placeholder="Digite o título do Cardápio"
              value={newMenu.title}
              onChange={(e) => setNewMenu({ title: e.target.value })}
            />

            <div className="item-selection">
              <h3>Selecione os Itens</h3>
              {items.map((item) => (
                <div key={item.id} className="item">
                  <input
                    type="checkbox"
                    checked={selectedItems.some((selectedItem) => selectedItem.id === item.id)}
                    onChange={() => toggleSelectItem(item)}
                  />
                  <span>
                    {item.nome} - R$ {item.price}
                  </span>
                  <img className='img-modal' src={item.image} alt={item.nome} />
                </div>
              ))}
            </div>

            <button onClick={handleCreateNewMenu}>Criar Cardápio com Itens Selecionados</button>
          </div>
        </div>
      </div>

      {/* Modal de Adicionar Novo Item */}
      <div className={`modal ${showAddItemModal ? 'active' : ''}`}>
        <div className="modal-content">
          <div className="modal-header">
            <h3>Adicionar Novo Item</h3>
            <button className="modal-close" onClick={() => setShowAddItemModal(false)}>&times;</button>
          </div>
          <div className="modal-body">
            <input
              type="text"
              placeholder="Nome do Item"
              value={newItem.nome}
              onChange={(e) => setNewItem({ ...newItem, nome: e.target.value })}
            />
            <input
              type="text"
              placeholder="Descrição do Item"
              value={newItem.descricao}
              onChange={(e) => setNewItem({ ...newItem, descricao: e.target.value })}
            />
            <input
              type="number"
              placeholder="Preço do Item"
              value={newItem.price}
              onChange={(e) => setNewItem({ ...newItem, price: e.target.value })}
            />
            <input
              type="text"
              placeholder="URL da Imagem"
              value={newItem.image}
              onChange={(e) => setNewItem({ ...newItem, image: e.target.value })}
            />
            <label>
              Disponibilidade:
              <input
                type="checkbox"
                checked={newItem.availability}
                onChange={() => setNewItem({ ...newItem, availability: !newItem.availability })}
              />
            </label>

            <button className='botao' onClick={handleAddNewItem}>Adicionar Item</button>
          </div>
        </div>
      </div>

      {/* Modal de Editar Item */}
      {itemToEdit && (
        <div className={`modal ${showEditItemModal ? 'active' : ''}`}>
          <div className="modal-content">
            <div className="modal-header">
              <h3>Editar Item</h3>
              <button className="modal-close" onClick={() => setShowEditItemModal(false)}>&times;</button>
            </div>
            <div className="modal-body">
              <input
                type="text"
                placeholder="Nome do Item"
                value={itemToEdit.nome}
                onChange={(e) => setItemToEdit({ ...itemToEdit, nome: e.target.value })}
              />
              <input
                type="text"
                placeholder="Descrição do Item"
                value={itemToEdit.descricao}
                onChange={(e) => setItemToEdit({ ...itemToEdit, descricao: e.target.value })}
              />
              <input
                type="number"
                placeholder="Preço do Item"
                value={itemToEdit.price}
                onChange={(e) => setItemToEdit({ ...itemToEdit, price: e.target.value })}
              />
              <input
                type="text"
                placeholder="URL da Imagem"
                value={itemToEdit.image}
                onChange={(e) => setItemToEdit({ ...itemToEdit, image: e.target.value })}
              />
              <label>
                Disponibilidade:
                <input
                  type="checkbox"
                  checked={itemToEdit.availability}
                  onChange={() => setItemToEdit({ ...itemToEdit, availability: !itemToEdit.availability })}
                />
              </label>

              <button onClick={handleSaveEditedItem}>Salvar Alterações</button>
            </div>
          </div>
        </div>
      )}




      {/* Modal para Atualizar Itens do Cardápio */}
      {menuToUpdate && (
        <div className="modal active">
          <div className="modal-content">
            <div className="modal-header">
              <h3 className="modal-title">Adicionar Itens ao Cardápio: {menuToUpdate.title}</h3>
              <button className="modal-close" onClick={() => setMenuToUpdate(null)}>&times;</button>
            </div>
            <div className="modal-body">
              <div className="item-selection">
                <h3>Selecione os Itens</h3>
                {items.map((item) => (
                  <div key={item.id} className="item">
                    <input
                      type="checkbox"
                      checked={selectedItems.some((selectedItem) => selectedItem.id === item.id)}
                      onChange={() => toggleSelectItem(item)}
                    />
                    <img src={item.image} alt={item.nome} className="item-image" />
                    <span>{item.nome} - R$ {item.price}</span>
                  </div>
                ))}
              </div>
            </div>
            <div className="modal-footer">
              <button
                className="confirm"
                onClick={() => handleUpdateMenuItems(menuToUpdate.id)}
              >
                Atualizar Itens
              </button>
              <button
                className="cancel"
                onClick={() => setMenuToUpdate(null)}
              >
                Cancelar
              </button>
            </div>
          </div>
        </div>
      )}






      <ToastContainer />
    </div>
  );
}

export default Cardapio;
