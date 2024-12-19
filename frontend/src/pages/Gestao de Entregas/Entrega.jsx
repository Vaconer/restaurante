import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import './Entrega.css';
import { FaTrash, FaPlus } from 'react-icons/fa';

function Entregas() {
  const [deliveries, setDeliveries] = useState([]);
  const [orders, setOrders] = useState([]);
  const [newDelivery, setNewDelivery] = useState({ recipient: '', address: '', status: '' });
  const [newOrder, setNewOrder] = useState({ product: '', quantity: 1 });
  const [selectedOrders, setSelectedOrders] = useState([]);
  const [showAddDeliveryModal, setShowAddDeliveryModal] = useState(false);
  const [showAddOrderModal, setShowAddOrderModal] = useState(false);

  useEffect(() => {
    fetchDeliveries();
    fetchOrders();
  }, []);

  const fetchDeliveries = async () => {
    try {
      const response = await axios.get('http://localhost:8080/api/delivery');
      setDeliveries(response.data);
    } catch (error) {
      console.error("Erro ao buscar entregas", error);
      toast.error('Erro ao carregar entregas.');
    }
  };

  const fetchOrders = async () => {
    try {
      const response = await axios.get('http://localhost:8080/api/order');
      setOrders(response.data);
    } catch (error) {
      console.error("Erro ao buscar pedidos", error);
      toast.error('Erro ao carregar pedidos.');
    }
  };

  const handleAddNewOrder = async () => {
    if (!newOrder.product || newOrder.quantity <= 0) {
      toast.error('Por favor, preencha todos os campos corretamente.');
      return;
    }

    try {
      const response = await axios.post('http://localhost:8080/api/order', newOrder);
      setOrders([...orders, response.data]);
      setNewOrder({ product: '', quantity: 1 });
      setShowAddOrderModal(false);
      toast.success('Pedido adicionado com sucesso!');
    } catch (error) {
      console.error("Erro ao adicionar pedido", error);
      toast.error('Erro ao adicionar o pedido. Tente novamente.');
    }
  };

  const handleCreateNewDelivery = async () => {
    if (!newDelivery.recipient || !newDelivery.address || selectedOrders.length === 0) {
      toast.error('Preencha todos os campos e selecione pelo menos um pedido.');
      return;
    }

    const newEntrega = {
      recipient: newDelivery.recipient,
      address: newDelivery.address,
      orderIds: selectedOrders.map(order => order.id),
      status: 'Pendente'
    };

    try {
      const response = await axios.post('http://localhost:8080/api/delivery', newEntrega);
      setDeliveries([...deliveries, response.data]);
      setNewDelivery({ recipient: '', address: '', status: '' });
      setSelectedOrders([]);
      setShowAddDeliveryModal(false);
      toast.success('Entrega criada com sucesso!');
    } catch (error) {
      console.error("Erro ao criar entrega", error);
      toast.error('Erro ao criar entrega. Tente novamente.');
    }
  };

  const handleDeleteDelivery = async (deliveryId) => {
    try {
      await axios.delete(`http://localhost:8080/api/delivery/${deliveryId}`);
      setDeliveries(deliveries.filter(delivery => delivery.id !== deliveryId));
      toast.success('Entrega excluída com sucesso!');
    } catch (error) {
      console.error("Erro ao excluir entrega", error);
      toast.error('Erro ao excluir entrega. Tente novamente.');
    }
  };

  return (
    <div className="entregas">
      <h1>Entregas</h1>
      <div className="botao-container">
        <button className='botao_add' onClick={() => setShowAddDeliveryModal(true)}>
          <FaPlus size={20} /> Nova Entrega
        </button>
        <button className='botao_add' onClick={() => setShowAddOrderModal(true)}>
          <FaPlus size={20} /> Novo Pedido
        </button>
      </div>
      <div className="deliveries-list">
        {deliveries.map((delivery) => (
          <div key={delivery.id}>
            <h2>{delivery.recipient}</h2>
            <p>Endereço: {delivery.address}</p>
            <p>Status: {delivery.status}</p>
            <button className='deletar' onClick={() => handleDeleteDelivery(delivery.id)}>
              <FaTrash size={20} />
            </button>
          </div>
        ))}
      </div>
      <ToastContainer />
    </div>
  );
}

export default Entregas;
