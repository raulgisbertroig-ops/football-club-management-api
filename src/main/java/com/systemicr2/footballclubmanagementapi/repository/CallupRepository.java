package com.systemicr2.footballclubmanagementapi.repository;

import com.systemicr2.footballclubmanagementapi.model.Callup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CallupRepository extends JpaRepository<Callup, Long>{
}
