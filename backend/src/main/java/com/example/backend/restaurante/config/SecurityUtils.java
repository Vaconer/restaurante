package com.example.backend.restaurante.config;

import com.example.backend.restaurante.model.user.Usuario;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;

public class SecurityUtils {

    public static Long getUsuarioId(Authentication authentication) {
        if (authentication == null || authentication.getPrincipal() == null) {
            throw new IllegalArgumentException("Authentication ou principal não pode ser nulo.");
        }

        Object principal = authentication.getPrincipal();

        // Verifique se o principal é do tipo User (implementação padrão do Spring Security)
        if (principal instanceof Usuario) {
            Usuario usuario = (Usuario) principal;
            String sub = usuario.getEmail(); // Se você estiver usando o nome de usuário como 'sub'
            return Long.valueOf(sub);  // Converte o 'sub' em Long
        }

        throw new IllegalStateException("Principal não é do tipo esperado.");
    }

    public static boolean hasRole(Authentication authentication, String role) {
        return authentication.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(role));
    }
}
