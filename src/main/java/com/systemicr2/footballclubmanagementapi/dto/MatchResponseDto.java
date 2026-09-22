package com.systemicr2.footballclubmanagementapi.dto;

import lombok.Data;

@Data
public class MatchResponseDto {
    private Long id;
    private Integer homeGoals;
    private Integer awayGoals;
}
