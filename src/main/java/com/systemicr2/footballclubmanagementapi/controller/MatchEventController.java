package com.systemicr2.footballclubmanagementapi.controller;

import com.systemicr2.footballclubmanagementapi.model.MatchEvent;
import com.systemicr2.footballclubmanagementapi.model.enums.EventType;
import com.systemicr2.footballclubmanagementapi.service.MatchEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/matchevents")
@RequiredArgsConstructor
public class MatchEventController {

    private final MatchEventService matchEventService;

    // POST: /api/matchevents?matchId=1&playerId=2&type=GOAL&minute=45
    @PostMapping
    public ResponseEntity<String> registerEvent(
            @RequestParam Long matchId,
            @RequestParam Long playerId,
            @RequestParam EventType type,
            @RequestParam int minute) {

        MatchEvent savedEvent = matchEventService.registerEvent(matchId, playerId, type, minute);
        return new ResponseEntity<>("Succes: Evento registrado con ID " + savedEvent.getId(), HttpStatus.CREATED);
    }
}