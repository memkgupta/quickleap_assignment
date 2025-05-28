package com.quickleap_assignment.subscription_service.dtos;

import lombok.Data;

import java.sql.Timestamp;
@Data
public class SubscriptionDTO {
    private String id;
    private PlanDTO plan;
    private String userId;
    private String status;
    private String renewalInterval;
    private Timestamp startDate;
    private Timestamp endDate;
}
