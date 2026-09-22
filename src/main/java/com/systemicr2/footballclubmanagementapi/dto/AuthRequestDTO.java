package com.systemicr2.footballclubmanagementapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data // Recordatorio: Esto genera automaticamente los Getters y Setters
@AllArgsConstructor
public class AuthRequestDTO {
    private String username;
    private String password;
    private String token;
}
