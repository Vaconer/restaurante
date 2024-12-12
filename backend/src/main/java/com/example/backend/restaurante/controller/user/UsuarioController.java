package com.example.backend.restaurante.controller.user;

import com.example.backend.restaurante.model.user.Usuario;
import com.example.backend.restaurante.service.user.UsuarioService;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

        @Autowired
        private UsuarioService usuarioService;

        @PostMapping
        public ResponseEntity<Usuario> createUsuario(@RequestBody Usuario usuario) {
            Usuario Usuario = usuarioService.saveUsuario(usuario);
            return ResponseEntity.ok(Usuario);
        }

        @GetMapping
        public ResponseEntity<List<Usuario>> getAllUsuarios() {
            List<Usuario> usuarios = usuarioService.getAllUsuarios();
            return ResponseEntity.ok(usuarios);
        }

        @GetMapping("/{id}")
        public ResponseEntity<Usuario> getUsuarioById(@PathVariable Long id) {
            Usuario usuario = usuarioService.getUsuarioById(id);
            if (usuario != null) {
                return ResponseEntity.ok(usuario);
            } else {
                return ResponseEntity.notFound().build();
            }
        }

        @PutMapping("/{id}")
        public ResponseEntity<Usuario> updateUsuario(@PathVariable Long id, @RequestBody Usuario updateUsuario) {
            Usuario updatedUsuario = usuarioService.updateUsuario(id, updateUsuario);
            if (updatedUsuario != null) {
                return ResponseEntity.ok(updatedUsuario);
            } else {
                return ResponseEntity.notFound().build();
            }
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
            usuarioService.deleteUsuario(id);
            return ResponseEntity.noContent().build();
        }
}
