// src/components/Pedidos.js
import React, { useState } from 'react';
import styles from './Pedidos.module.css';

const Pedidos = () => {
  const [pedido, setPedido] = useState([]);
  const [novoItem, setNovoItem] = useState('');

  const adicionarItem = () => {
    if (novoItem.trim() !== '') {
      setPedido([...pedido, novoItem]);
      setNovoItem('');
    }
  };

  const limparPedido = () => {
    setPedido([]);
  };

  return (
    <div className={styles.pedidosContainer}>
      <h1>Gestão de Pedidos</h1>

      <div className={styles.formulario}>
        <input
          type="text"
          placeholder="Adicione um item"
          value={novoItem}
          onChange={(e) => setNovoItem(e.target.value)}
        />
        <button onClick={adicionarItem}>Adicionar</button>
      </div>

      <div className={styles.pedidoResumo}>
        <h2>Resumo do Pedido</h2>
        {pedido.length > 0 ? (
          <ul className={styles.pedidoLista}>
            {pedido.map((item, index) => (
              <li key={index} className={styles.pedidoItem}>
                {item}
              </li>
            ))}
          </ul>
        ) : (
          <p>O pedido está vazio.</p>
        )}
      </div>


      <button className={styles.limparPedido} onClick={limparPedido}>
        Limpar Pedido
      </button>
    </div>
  );
};

export default Pedidos;
