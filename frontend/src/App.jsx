import './App.css';
import Header from './components/Header/Header';
import Cardapio from './pages/Gestao de Cardapio/Cardapio';
import Entrega from './pages/Gestao de Entregas/Entrega';
import Pedidos from './pages/Gestao de Pedidos/Pedidos';
import PedidoPage from './pages/Realizar Pedidos/PedidoPage';
import Footer from './components/Footer/Footer';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom'; 

function App() {

  return (
    <Router>
      <div>
        <Header />
        <Routes>
          <Route path="/cardapio" element={<Cardapio />} /> 
          <Route path="/entrega" element={<Entrega />} /> 
          <Route path="/pedidos" element={<Pedidos />} /> 
          <Route path="/RealizarPedidos" element={<PedidoPage />} /> 
        </Routes>
        <Footer />
      </div>
    </Router>
  );
}

export default App;
