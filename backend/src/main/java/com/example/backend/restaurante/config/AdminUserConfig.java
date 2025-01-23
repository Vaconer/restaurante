package com.example.backend.restaurante.config;

import com.example.backend.restaurante.model.user.Roles;
import com.example.backend.restaurante.model.user.Usuario;
import com.example.backend.restaurante.repository.user.RolesRepository;
import com.example.backend.restaurante.repository.user.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Set;

@Configuration
public class AdminUserConfig implements CommandLineRunner {

    @Autowired
    private RolesRepository rolesRepository;

    private UsuarioRepository usuarioRepository;
    private BCryptPasswordEncoder passwordEncoder;

    public AdminUserConfig(RolesRepository rolesRepository,
                           UsuarioRepository usuarioRepository,
                           BCryptPasswordEncoder passwordEncoder) {
        this.rolesRepository = rolesRepository;
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        var rolesAdmin = rolesRepository.findByName(Roles.Values.ADMIN.name())
                .orElseThrow(() -> new RuntimeException("Role ADMIN não encontrado!"));

        var userAdmin = usuarioRepository.findByEmail("admin");

        userAdmin.ifPresentOrElse(
                user -> System.out.println("Admin já existe"),
                () -> {
                    var user = new Usuario();
                    user.setEmail("admin");
                    user.setPassword(passwordEncoder.encode("123"));
                    user.setRoles(Set.of(rolesAdmin));
                    usuarioRepository.save(user);
                    System.out.println("Admin criado com sucesso");
                }
        );
    }

}
