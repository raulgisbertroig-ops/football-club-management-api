package com.systemicr2.footballclubmanagementapi.service;

import com.systemicr2.footballclubmanagementapi.dto.PlayerStatsDTO;
import com.systemicr2.footballclubmanagementapi.model.Callup;
import com.systemicr2.footballclubmanagementapi.model.Player;
import com.systemicr2.footballclubmanagementapi.model.enums.EventType;
import com.systemicr2.footballclubmanagementapi.repository.MatchEventRepository;
import com.systemicr2.footballclubmanagementapi.repository.CallupRepository;
import com.systemicr2.footballclubmanagementapi.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2 // Aprovechamos para meter telemetría aquí también
public class PlayerStatsService {

    // Necesitamos acceso a los jugadores y las convocatorias
    private final MatchEventRepository matchEventRepository;
    private final PlayerRepository playerRepository;
    private final CallupRepository callupRepository;
    
    public long getPlayerTotalGoals(Long playerId) {
        return matchEventRepository.countByPlayerIdAndEventType(playerId, EventType.GOAL);
    }

    public long getPlayerTotalYellowCards(Long playerId) {
        return matchEventRepository.countByPlayerIdAndEventType(playerId, EventType.YELLOW_CARD);
    }


    public PlayerStatsDTO getPlayerStats(Long playerId) {
        log.info("Calculando estadísticas para el jugador ID: {}", playerId);

        // PASO 1: Buscar el jugador (Si no existe, lanzamos error)
        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new RuntimeException("Jugador no encontrado con ID: " + playerId));

        // PASO 2: Obtener todo su historial de convocatorias
        List<Callup> callups = callupRepository.findByPlayerId(playerId);

        // PASO 3: Inicializar la calculadora (Variables acumuladoras)
        int totalMatches = callups.size();
        int totalStarted = 0;
        int totalMinutes = 0;

        // PASO 4: Iterar (bucle) sobre cada acta para sumar los datos
        for (Callup callup : callups) {
            if (callup.isStarter()) {
                totalStarted++; // Sumamos 1 a titularidades
            }
            totalMinutes += callup.getMinutesPlayed(); // Acumulamos los minutos jugados
        }

        log.info("Estadisticas calculadas -> Partidos: {}, Titular: {}, Minutos: {}", totalMatches, totalStarted, totalMinutes);

        // PASO 5: Construir y devolver el Informe DTO
        return PlayerStatsDTO.builder()
                .playerId(player.getId())
                .playerName(player.getName())
                .totalMatchesCalledUp(totalMatches)
                .totalMatchesStarted(totalStarted)
                .totalMinutesPlayed(totalMinutes)
                .build();
    }
}