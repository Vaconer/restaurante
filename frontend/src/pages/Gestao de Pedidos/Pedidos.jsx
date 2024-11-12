// src/components/Pedidos.js
import React from 'react';
import Pedido from './Pedido';
import './Pedidos.css';

const Pedidos = () => {
  const pedidos = [
    { prato: { nome: 'Prato Vegetariano', imagem: 'link_da_imagem' }, tempo: 32, quantidade: 2, observacao: 'Carne Vegana de Porco' },
    { prato: { nome: 'Prato Vegetariano', imagem: 'link_da_imagem' }, tempo: 923, quantidade: 2, observacao: 'Carne Vegana de Porco' },
    { prato: { nome: 'Prato Vegetariano', imagem: 'link_da_imagem' }, tempo: 1243, quantidade: 2, observacao: 'Carne Vegana de Porco' },
  ];

  function filterPedidos() {
    
  }

  function filterConcluidos() {
    
  }

  function filterEntregues() {
    
  }

  function filterCancelados() {
    
  }
  return (
    
    <div className="pedidos-container">
      <div className= "buttons-container">
        <button type="button" onClick={filterPedidos} className='btnPedidos'> Pedidos </button>
        <button type="button" onClick={filterConcluidos} className='btnConcluidos'> concluidos </button>
        <button type="button" onClick={filterEntregues} className='btnEntregues'> Entregues </button>
        <button type="button" onClick={filterCancelados} className='btnCancelados'> Cancelados </button>
      </div>
      <div className="pedidos-grid">
        {pedidos.map((pedido, index) => (
          <Pedido
            key={index}
            prato={pedido.prato}
            tempo={pedido.tempo}
            quantidade={pedido.quantidade}
            observacao={pedido.observacao}
          />
        ))}
      </div>
    </div>
  );
};

export default Pedidos;