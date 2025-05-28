package com.quickleap_assignment.subscription_service.services;

import com.quickleap_assignment.subscription_service.dtos.PlanDTO;
import com.quickleap_assignment.subscription_service.entities.Plan;
import com.quickleap_assignment.subscription_service.repositories.PlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlanService {
    private final PlanRepository planRepository;
    public Plan createPlan(PlanDTO planDTO) {
        try
        {
            Plan plan = new Plan();
            plan.setDescription(planDTO.getDescription());
            plan.setName(plan.getName());
            plan.setPrice(planDTO.getPrice());
            plan.setFeatures(planDTO.getFeatures());
            return planRepository.save(plan);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    public List<Plan> getPlans()
    {
        return planRepository.findAll();
    }
    public Plan findById(String id) {
        return planRepository.findById(id).orElse(null);
    }
}
