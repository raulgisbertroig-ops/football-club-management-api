package com.systemicr2.footballclubmanagementapi.repository;

import com.systemicr2.footballclubmanagementapi.model.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClubRepository extends JpaRepository<Club, Long> {
}