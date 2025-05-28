package com.quickleap_assignment.subscription_service.controllers;

import com.quickleap_assignment.subscription_service.dtos.PlanDTO;
import com.quickleap_assignment.subscription_service.entities.Plan;
import com.quickleap_assignment.subscription_service.mappers.PlanMapper;
import com.quickleap_assignment.subscription_service.services.PlanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/plans")
public class PlanController {
    private final PlanService planService;

    public PlanController(PlanService planService) {
        this.planService = planService;
    }

    @GetMapping("")
    public ResponseEntity<List<PlanDTO>> getPlans() {
        List<Plan> plans = planService.getPlans();
       List<PlanDTO> planDTOS =  plans.stream().map(PlanMapper::mapPlanToPlanDTO).toList();
       return ResponseEntity.ok(planDTOS);
    }
    @PostMapping
    public ResponseEntity<PlanDTO> createPlan(@RequestBody PlanDTO planDTO) {
        Plan plan = planService.createPlan(planDTO);
        PlanDTO createdPlanDTO = PlanMapper.mapPlanToPlanDTO(plan);
        return ResponseEntity.ok(createdPlanDTO);
    }

}
