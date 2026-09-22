package com.systemicr2.footballclubmanagementapi.controller;

import com.systemicr2.footballclubmanagementapi.dto.TeamStandingDto;
import com.systemicr2.footballclubmanagementapi.service.StandingsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/standings")
@RequiredArgsConstructor
public class StandingsController {

    private final StandingsService standingsService;

    // T-59: Endpoint GET para consultar la clasificación
    @GetMapping
    public ResponseEntity<List<TeamStandingDto>> getLeagueStandings() {
        List<TeamStandingDto> standings = standingsService.calculateLeagueStandings();

        // Retornamos un estado 200 OK con la lista de clasificación en el cuerpo
        return ResponseEntity.ok(standings);
    }
}