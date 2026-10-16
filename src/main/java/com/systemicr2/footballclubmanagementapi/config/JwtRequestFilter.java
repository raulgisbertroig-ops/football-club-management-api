package com.systemicr2.footballclubmanagementapi.config;

import com.systemicr2.footballclubmanagementapi.util.JwtUtil;
// Asegúrate de importar tu servicio de usuarios
import com.systemicr2.footballclubmanagementapi.service.CustomUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    // 1. Declaramos las herramientas
    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService userDetailsService;

    // 2. Las inyectamos por constructor
    public JwtRequestFilter(JwtUtil jwtUtil, CustomUserDetailsService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    // Este es el método obligatorio que el vigilante ejecuta cada vez que alguien llama a la puerta
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        // 3. Buscamos la cabecera "Authorizadción" en la petición que esta entrando
        final String authorizationHeader = request.getHeader("Authorization");
        System.out.println(">>> CABECERA EXACTA RECIBIDA: {" + authorizationHeader + "}");

        String username = null;
        String jwt = null;

        // 4. Comprobamos si la cabecera existe y si cumple la regla de empezar por "Bearer"
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer")) {
            jwt = authorizationHeader.substring(7); // <-- 2. Le asignamos valor aquí
            try {
                username = jwtUtil.extractUsername(jwt);
            } catch (Exception e) {
                logger.error("No se pudo extraer el username del token JWT", e);
            }
        }

        // 5. Si el Token tenía un usuario, pero Spring aún no lo ha registrado en esta petición...
        if (username != null && org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication() == null) {

            // 5.1 Buscamos al entrenador en tu base de datos usando la herramienta que estaba "sin uso"
            org.springframework.security.core.userdetails.UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

            // 5.2 Creamos el pase oficial de Spring Security (El sello en la mano)
            if (jwtUtil.validateToken(jwt, userDetails)) {

                org.springframework.security.authentication.UsernamePasswordAuthenticationToken authToken =
                        new org.springframework.security.authentication.UsernamePasswordAuthenticationToken(
                                userDetails, null, userDetails.getAuthorities());

                // Adjuntamos detalles de red de la petición HTTP (IP, sesión etc.)
                authToken.setDetails(new org.springframework.security.web.authentication.WebAuthenticationDetailsSource().buildDetails(request));

                org.springframework.security.core.context.SecurityContextHolder.getContext().setAuthentication(authToken);

                // 5.3 Le ponemos el pase al usuario en el contexto de seguridad actual
                org.springframework.security.core.context.SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        // --- EN EL PRÓXIMO PASO: VALIDAREMOS AL USUARIO AQUÍ ---

        // Aquí dentro programaremos la lógica de leer el token en el siguiente paso.

        // Esta línea es VITAL: Le dice a Spring "Pase lo que pase, deja que la petición siga su camino"
        filterChain.doFilter(request, response);
    }
}

