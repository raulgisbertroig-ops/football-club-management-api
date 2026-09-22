package com.systemicr2.footballclubmanagementapi.repository;

import com.systemicr2.footballclubmanagementapi.model.CategoryRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRuleRepository extends JpaRepository<CategoryRule, Long> {
}
