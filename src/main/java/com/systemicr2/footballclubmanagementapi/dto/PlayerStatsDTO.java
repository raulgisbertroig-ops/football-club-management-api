package com.systemicr2.footballclubmanagementapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlayerStatsDTO {
    private Long playerId;
    private String playerName;
    private int totalMatchesCalledUp; // Partidos convocado
    private int totalMatchesStarted; // Partidos como titular
    private int totalMinutesPlayed; // Minutos totales jugados
}
