package com.systemicr2.footballclubmanagementapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LeagueStandingDTO {
    private Long teamId;
    private String teamName;
    private int goalDifference;
    private int matchesPlayed; // Partidos jugados (PJ)
    private int won; // Victorias (PG)
    private int drawn; // Empates (PE)
    private int lost; // Derrotas (PP)
    private int goalsFor; // Goles a Favor (GF)
    private int goalsAgainst; // Goles en COntra (GC)
    private int goalsDifference; // Diferencia de Goles
    private int points; // Puntos totales (PTS)
}
