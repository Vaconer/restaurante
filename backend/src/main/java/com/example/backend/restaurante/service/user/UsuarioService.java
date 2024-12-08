package com.example.backend.restaurante.service.user;

import com.example.backend.restaurante.model.user.Usuario;
import com.example.backend.restaurante.repository.user.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

        @Autowired
        private UsuarioRepository usuarioRepository;

        public Usuario saveUsuario(Usuario usuario) {
            return usuarioRepository.save(usuario);
        }

        public List<Usuario> getAllUsuarios() {
            return usuarioRepository.findAll();
        }

        public Usuario getUsuarioById(Long id) {
            Optional<Usuario> usuario = usuarioRepository.findById(id);
            return usuario.orElse(null);
        }

        public Usuario updateUsuario(Long id, Usuario updateUsuario) {
            Optional<Usuario> usuario = usuarioRepository.findById(id);
            if (usuario.isPresent()) {
                Usuario existingUsuario = usuario.get();
                existingUsuario.setNome(updateUsuario.getNome());
                existingUsuario.setEmail(updateUsuario.getEmail());
                existingUsuario.setPassword(updateUsuario.getPassword());
                existingUsuario.setRole(updateUsuario.getRole());
                existingUsuario.setEndereco(updateUsuario.getEndereco());
                existingUsuario.setCartoes(updateUsuario.getCartoes());

                return usuarioRepository.save(existingUsuario);
            } else {
                return null;
            }
        }

        public void deleteUsuario(Long id) {
            usuarioRepository.deleteById(id);
        }
}
