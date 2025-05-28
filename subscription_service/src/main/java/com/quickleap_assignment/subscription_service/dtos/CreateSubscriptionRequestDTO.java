package com.quickleap_assignment.subscription_service.dtos;

import com.quickleap_assignment.subscription_service.entities.RenewalInterval;
import lombok.Data;

@Data
public class CreateSubscriptionRequestDTO {
    private String planId;
    private RenewalInterval renewalInterval;

}
