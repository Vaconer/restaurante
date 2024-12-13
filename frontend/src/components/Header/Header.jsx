import './Header.css';
import logo from './img/logo.png';
import { Link, useLocation } from 'react-router-dom';
import { useState } from 'react';

function Header() {
  const location = useLocation();
  const [isMenuOpen, setIsMenuOpen] = useState(false);

  return (
    <div className="Header">
      <div className="hamburger-menu" onClick={() => setIsMenuOpen(!isMenuOpen)}>
        <span className={`bar ${isMenuOpen ? 'open' : ''}`}></span>
        <span className={`bar ${isMenuOpen ? 'open' : ''}`}></span>
        <span className={`bar ${isMenuOpen ? 'open' : ''}`}></span>
      </div>
      <div className="logo-container">
        <img src={logo} alt="Logo" />
      </div>
      <ul className={`nav-list ${isMenuOpen ? 'open' : ''}`}>
        <li className={`nav-itens ${location.pathname === '/cardapio' ? 'active' : ''}`}>
          <Link to="/cardapio">Cardápio</Link>
        </li>
        <li className={`nav-itens ${location.pathname === '/pedidos' ? 'active' : ''}`}>
          <Link to="/pedidos">Pedidos</Link>
        </li>
        <li className={`nav-itens ${location.pathname === '/RealizarPedidos' ? 'active' : ''}`}>
          <Link to="/RealizarPedidos">Realizar Pedido</Link>
        </li>
        <li className={`nav-itens ${location.pathname === '/entrega' ? 'active' : ''}`}>
          <Link to="/entrega">Entrega</Link>
        </li>
      </ul>
    </div>
  );
}

export default Header;
