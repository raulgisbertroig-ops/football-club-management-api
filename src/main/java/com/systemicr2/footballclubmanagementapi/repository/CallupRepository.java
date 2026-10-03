package com.systemicr2.footballclubmanagementapi.repository;

import com.systemicr2.footballclubmanagementapi.model.Callup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CallupRepository extends JpaRepository<Callup, Long>{
    int countByMatchIdAndIsStarter(Long matchId, boolean isStarter);
    boolean existsByMatchIdAndPlayerId(Long matchId, Long playerId);
    List<Callup> findByPlayerId(Long playerId);
}

