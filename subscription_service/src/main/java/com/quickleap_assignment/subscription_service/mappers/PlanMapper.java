package com.quickleap_assignment.subscription_service.mappers;

import com.quickleap_assignment.subscription_service.dtos.PlanDTO;
import com.quickleap_assignment.subscription_service.entities.Plan;

public class PlanMapper {
    public static PlanDTO mapPlanToPlanDTO(Plan plan) {
        PlanDTO planDTO = new PlanDTO();
        planDTO.setId(plan.getId());
        planDTO.setName(plan.getName());
        planDTO.setDescription(plan.getDescription());
        planDTO.setFeatures(plan.getFeatures());
        planDTO.setPrice(plan.getPrice());
        return planDTO;
    }
}
