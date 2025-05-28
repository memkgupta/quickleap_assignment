package com.quickleap_assignment.subscription_service.repositories;

import com.quickleap_assignment.subscription_service.entities.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<Subscription, String> {
}
