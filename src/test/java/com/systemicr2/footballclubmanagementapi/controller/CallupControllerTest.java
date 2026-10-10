package com.systemicr2.footballclubmanagementapi.controller; // Ajusta a tu paquete


import com.systemicr2.footballclubmanagementapi.config.JwtRequestFilter;
import com.systemicr2.footballclubmanagementapi.service.CallupService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CallupController.class)
@AutoConfigureMockMvc(addFilters = false) // 1. NUEVO: Apaga la seguridad de Spring Boot para este test
class CallupControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CallupService callupService;

    @MockitoBean
    private JwtRequestFilter jwtRequestFilter; // 2. NUEVO: Engañamos a Spring para que no intente crear el filtro real

    @Test
    void shouldReturn201WhenCallingAddPlayerEndpoint() throws Exception {
        // ACT & ASSERT
        mockMvc.perform(post("/api/callups")
                        .param("matchId", "1")
                        .param("playerId", "2")
                        .param("isStarter", "true")
                        .param("minutesPlayed", "90"))
                .andExpect(status().isCreated());
    }
}
