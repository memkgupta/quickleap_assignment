package com.quickleap_assignment.subscription_service.mappers;

import com.quickleap_assignment.subscription_service.dtos.SubscriptionDTO;
import com.quickleap_assignment.subscription_service.entities.Subscription;

public class SubscriptionMapper {
    public static SubscriptionDTO toSubscriptionDTO(Subscription subscription) {
        SubscriptionDTO subscriptionDTO = new SubscriptionDTO();
        subscriptionDTO.setId(subscription.getId());
        subscriptionDTO.setPlan(PlanMapper.mapPlanToPlanDTO(subscription.getPlan()));
        subscriptionDTO.setStatus(subscription.getStatus().toString());
        subscriptionDTO.setStartDate(subscription.getStartDate());
        subscriptionDTO.setEndDate(subscription.getEndDate());
        return subscriptionDTO;
    }
}
