import './Footer.css';

function Footer() {
    return (
        <footer className="footer">
            <div className="footer-container">
                <div className="footer-logo">
                    <img src="https://png.pngtree.com/png-vector/20190328/ourmid/pngtree-food-logo-designs-with-spoon-and-fork-png-image_879863.jpg" alt="Logo" />
                </div>
                <div className="footer-links">
                    <h3>Links Úteis</h3>
                    <ul>
                        <li>Como Funciona</li>
                        <li>Fale Conosco</li>
                        <li>Termos de Uso</li>
                        <li>Política de Privacidade</li>
                        <li>Trabalhe Conosco</li>
                    </ul>
                </div>
                <div className="footer-contact">
                    <h3>Contato</h3>
                    <p>Email: gabrielmartinssousa@gmail.com</p> <br/>
                    <p>Telefone: 4002-8922</p>
                </div>
                <div className="footer-social">
                    <h3>Siga-nos</h3>
                    <p>Facebook</p>
                    <p>Instagram</p>
                    <p>Twitter</p>
                </div>
            </div>
            <div className="footer-bottom">
                <p>&copy; 2024 Projeto BCC. Todos os direitos reservados.</p>
            </div>
        </footer>
    );
}

export default Footer;
