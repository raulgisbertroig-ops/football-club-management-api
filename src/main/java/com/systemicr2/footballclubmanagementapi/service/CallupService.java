package com.systemicr2.footballclubmanagementapi.service;

import com.systemicr2.footballclubmanagementapi.model.Callup;
import com.systemicr2.footballclubmanagementapi.model.Match;
import com.systemicr2.footballclubmanagementapi.model.Player;
import com.systemicr2.footballclubmanagementapi.repository.CallupRepository;
import com.systemicr2.footballclubmanagementapi.repository.MatchRepository;
import com.systemicr2.footballclubmanagementapi.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j // MOTOR DE LOGS
public class CallupService {

    private final CallupRepository callupRepository;
    private final MatchRepository matchRepository;
    private final PlayerRepository playerRepository;

    @Transactional
    public Callup addPlayerToMatch(Long matchId, Long playerId, boolean isStarter, int minutesPlayed) {
        log.info("Iniciando proceso de convocatoria para el jugador ID: {} en el partido ID: {}", playerId, matchId);

        // REQUERIMIENTO 1: Busca el partido (Match) usando matchRepository.
        // Si no existe, lanza un RuntimeException("Partido no encontrado")
        Match match = matchRepository.findById(matchId)
                .orElseThrow(() -> new RuntimeException("Partido no encontrado con ID:" + matchId));

        // REQUERIMIENTO 2: Busca el jugador (Player) usando playerRepository.
        // Si no existe, lanza un RuntimeException("Jugador no encontrado")
        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new RuntimeException("Jugador no encontrado con ID:" + playerId));

        // --- NUEVA REGLA: Validación de limite de titulares ---
        if (isStarter) {
            // Escudo anti-NPE
            if (match.getCategoryRule() == null || match.getCategoryRule().getModality() == null) {
                throw new RuntimeException("Partido no tiene una modalidad válida configurada.");
            }
            int titularesActuales = callupRepository.countByMatchIdAndIsStarter(matchId, true);
            String tipoPartido = match.getCategoryRule().getModality().name();
            int limiteTitulares = obtenerLimiteTitulares(tipoPartido);
            if (titularesActuales >= limiteTitulares) {
                throw new RuntimeException("No se pueden exceder los " + limiteTitulares + " titulares para este partido.");
            }
        }

        if (minutesPlayed < 0) {
            log.warn("El jugador ID: {} tiene minutos negativos ({}). Revisa los datos de entrada.", playerId, minutesPlayed);
        }
        // REQUERIMIENTO 3: Instancia en una nueva Callup (new Callup())
        // y asignale el partido, el jugador, si es titular y los minutos jugados.
        Callup callup = new Callup();
        callup.setMatch(match);
        callup.setPlayer(player);
        callup.setStarter(isStarter);
        callup.setMinutesPlayed(minutesPlayed);

        // REQUERIMIENTO 4: Guarda la convocatoria en la base de datos usando callupRepository
        // y retorna el objeto guardado.
        try {
            Callup savedCallup = callupRepository.save(callup);
            log.info("Jugador ID: {} convocado exitosamente. Titular: {}", playerId, isStarter);
            return savedCallup;
        } catch (Exception e) {
            log.error("Error crítico al guardar la convocatoria del jugador ID: {}. Causa: {}", playerId, e.getMessage());
            throw new RuntimeException("Error en base de datos al guardar la convocatoria");
        }
    }

    // Método auxiliar para gestionar las modalidades
    private int obtenerLimiteTitulares(String matchType) {
        if (matchType == null) return 11; // Por defecto
        return switch (matchType.toUpperCase()) {
            case "F7" -> 7;
            case "F8" -> 8;
            case "F11" -> 11;
            default -> 11;
        };
    }
}

