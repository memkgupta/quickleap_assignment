package com.quickleap_assignment.subscription_service.repositories;

import com.quickleap_assignment.subscription_service.entities.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanRepository extends JpaRepository<Plan,String> {
}
