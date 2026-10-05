package com.systemicr2.footballclubmanagementapi.service;

import com.systemicr2.footballclubmanagementapi.dto.LeagueStandingDTO;
import com.systemicr2.footballclubmanagementapi.model.Match;
import com.systemicr2.footballclubmanagementapi.model.Team;
import com.systemicr2.footballclubmanagementapi.repository.MatchRepository;
import com.systemicr2.footballclubmanagementapi.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class StandingsService {

    private final TeamRepository teamRepository;
    private final MatchRepository matchRepository;

    public List<LeagueStandingDTO> calculateLeagueStandings() {
        log.info("Iniciando el cálculo de la clasificación de la liga...");

        List<LeagueStandingDTO> standings = new ArrayList<>();
        List<Team> allTeams = teamRepository.findAll();
        List<Match> allMatches = matchRepository.findAll();

        // 1. ITERAR EQUIPOS: Inicializar el Estado Cero
        for (Team team : allTeams) {
            LeagueStandingDTO dto = LeagueStandingDTO.builder()
                    .teamId(team.getId())
                    .teamName(team.getName())
                    .matchesPlayed(0)
                    .won(0)
                    .drawn(0)
                    .lost(0)
                    .goalsFor(0)
                    .goalsAgainst(0)
                    .goalDifference(0)
                    .points(0)
                    .build();

            // 2. ITERAR PARTIDOS: Buscar resultados de este equipo
            for (Match match : allMatches) {
                // Solo procesar si el partido tiene un resultado final
                if (match.getHomeGoals() != null && match.getAwayGoals() != null) {
                    if (match.getHomeTeam().getId().equals(team.getId())) {
                        processMatchResult(dto, match.getHomeGoals(), match.getAwayGoals());
                    } else if (match.getAwayTeam().getId().equals(team.getId())) {
                        processMatchResult(dto, match.getAwayGoals(), match.getHomeGoals());
                    }
                }
            }

            // 3. CÁLCULO FINAL: Diferencia de goles
            dto.setGoalDifference(dto.getGoalsFor() - dto.getGoalsAgainst());

            standings.add(dto);
        }

        // 4. ORDENAR LA TABLA (Sorting): Primero por puntos, en caso de empate, por diferencia de goles
        standings.sort((t1, t2) -> {
            if (t2.getPoints() != t1.getPoints()) {
                return Integer.compare(t2.getPoints(), t1.getPoints());
            }
            return Integer.compare(t2.getGoalDifference(), t1.getGoalDifference());
        });

        log.info("Clasificación calculada con éxito para {} equipos.", standings.size());
        return standings;
    }

    // Motor Matemático (Adaptado de tu lógica original)
    private void processMatchResult(LeagueStandingDTO dto, int goalsFor, int goalsAgainst) {
        dto.setMatchesPlayed(dto.getMatchesPlayed() + 1);
        dto.setGoalsFor(dto.getGoalsFor() + goalsFor);
        dto.setGoalsAgainst(dto.getGoalsAgainst() + goalsAgainst);

        if (goalsFor > goalsAgainst) {
            dto.setWon(dto.getWon() + 1);
            dto.setPoints(dto.getPoints() + 3);
        } else if (goalsFor == goalsAgainst) {
            dto.setDrawn(dto.getDrawn() + 1);
            dto.setPoints(dto.getPoints() + 1);
        } else {
            dto.setLost(dto.getLost() + 1);
        }
    }
}