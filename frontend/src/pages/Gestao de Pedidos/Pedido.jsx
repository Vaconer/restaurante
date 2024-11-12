// src/components/Pedido.js
import React, { useState, useEffect } from "react";
import "./Pedido.css";

const Pedido = ({ prato, tempo, quantidade, observacao, status }) => {
  const [segundos, setSegundos] = useState(tempo);

  useEffect(() => {
    const timer = setInterval(() => {
      setSegundos((prev) => (prev > 0 ? prev - 1 : 0));
    }, 1000);

    return () => clearInterval(timer);
  }, []);

  const formatarTempo = (tempo) => {
    const minutos = Math.floor(tempo / 60);
    const segundos = tempo % 60;
    return `${minutos.toString().padStart(2, "0")}:${segundos
      .toString()
      .padStart(2, "0")}`;
  };

  return (
    <div className="pedido-card">
      <img src={prato.imagem} alt={prato.nome} className="pedido-imagem" />
      <h3>{prato.nome}</h3>
      <p className="tempo">{formatarTempo(segundos)}</p>
      <div className="quantidade">
        <p>{quantidade} Pedidos</p>
      </div>
      <p className="observacao">OBS: {observacao}</p>
    </div>
  );
};

export default Pedido;
