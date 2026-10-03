package com.systemicr2.footballclubmanagementapi.service;

import com.systemicr2.footballclubmanagementapi.model.Match;
import com.systemicr2.footballclubmanagementapi.model.MatchEvent;
import com.systemicr2.footballclubmanagementapi.model.Player;
import com.systemicr2.footballclubmanagementapi.model.enums.EventType;
import com.systemicr2.footballclubmanagementapi.repository.CallupRepository;
import com.systemicr2.footballclubmanagementapi.repository.MatchEventRepository;
import com.systemicr2.footballclubmanagementapi.repository.PlayerRepository;
import com.systemicr2.footballclubmanagementapi.repository.MatchRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MatchEventService {

    private final MatchEventRepository matchEventRepository;
    private final MatchRepository matchRepository;
    private final PlayerRepository playerRepository;
    private final CallupRepository callupRepository;


    public MatchEvent registerEvent(Long matchId, Long playerId, EventType type, int minute) {
        log.info("Intentando registrar evento {} para el jugador {} en el partido {} (Minuto {}", type, playerId, matchId, minute);

        // 1. PRIMERA LÍNEA DEFENSIVA: ¿Existen las entidadesbásicas?
        Match match = matchRepository.findById(matchId)
                .orElseThrow(() -> new RuntimeException("Partido no encontrado con ID: " + matchId));

        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new RuntimeException("Jugador no encontrado con ID: " + playerId));

        // 2. SEGUNDA LÍNEA DEFENSIVA (Validación Cruzada): ¿Estaba convocado?
        boolean isCalledUp = callupRepository.existsByMatchIdAndPlayerId(matchId, playerId);

        if (!isCalledUp) {
            log.error("Violación de integridad: El jugador {} no esta convocado para el partido {}", playerId, matchId);
            throw new RuntimeException("Operación rechazada: El jugador no pertenece a la convocatoria de este partido.");
        }

        // 3. TERCERA LÍNEA DEFENSIVA: Valida el minuto
        if (minute < 0 || minute > 120) {
            throw new RuntimeException("El minuto invalido: " + minute);
        }

        // 4. SI SOBREVIVE A LAS DEFENSAS -> Construimosy guardamos la jugada
        MatchEvent event = new MatchEvent();
        event.setMatch(match);
        event.setPlayer(player);
        event.setEventType(type);
        event.setMatchMinute(minute);

        MatchEvent savedEvent = matchEventRepository.save(event);
        log.info("Evento registrado con éxito en la base de datos conID: {}", savedEvent.getId());

        return savedEvent;
    }
}




