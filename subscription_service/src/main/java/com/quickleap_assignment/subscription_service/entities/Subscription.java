package com.quickleap_assignment.subscription_service.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Data
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String userId;
  @ManyToOne(fetch = FetchType.EAGER)
    private Plan plan;
  @Enumerated(EnumType.STRING)
    private SubscriptionStatus status;

    @Enumerated(EnumType.STRING)
   private RenewalInterval renewalInterval;
    private Timestamp startDate;
    private Timestamp endDate;

}
