package com.systemicr2.footballclubmanagementapi.service;

import com.systemicr2.footballclubmanagementapi.model.Callup;
import com.systemicr2.footballclubmanagementapi.model.CategoryRule;
import com.systemicr2.footballclubmanagementapi.model.Match;
import com.systemicr2.footballclubmanagementapi.model.Player;
import com.systemicr2.footballclubmanagementapi.model.enums.Modality;
import com.systemicr2.footballclubmanagementapi.repository.CallupRepository;
import com.systemicr2.footballclubmanagementapi.repository.MatchRepository;
import com.systemicr2.footballclubmanagementapi.repository.PlayerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CallupServiceTest {

    @Mock
    private CallupRepository callupRepository;
    @Mock
    private MatchRepository matchRepository;
    @Mock
    private PlayerRepository playerRepository;

    @InjectMocks
    private CallupService callupService;

    @Test
    void shouldThrowExceptionWhenTeamExceedsStartersLimit() {
        // 1. ARRANGE
        Long matchId = 1L;
        Long playerId = 2L; // Ahora es un Long

        CategoryRule rule = new CategoryRule();
        rule.setModality(Modality.F7);

        Match match = new Match();
        match.setId(matchId);
        match.setCategoryRule(rule);

        Player player = new Player();
        player.setId(playerId);
        player.setDni("12345678Z");

        when(matchRepository.findById(matchId)).thenReturn(Optional.of(match));
        when(playerRepository.findById(playerId)).thenReturn(Optional.of(player));
        when(callupRepository.countByMatchIdAndIsStarter(matchId, true)).thenReturn(7);

        // 2. ACT & 3. ASSERT
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            callupService.addPlayerToMatch(matchId, playerId, true, 90);
        });

        assertTrue(exception.getMessage().contains("No se pueden exceder los 7 titulares"));
    }
}