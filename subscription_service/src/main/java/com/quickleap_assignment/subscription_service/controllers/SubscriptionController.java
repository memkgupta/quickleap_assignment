package com.quickleap_assignment.subscription_service.controllers;

import com.quickleap_assignment.subscription_service.dtos.CreateSubscriptionRequestDTO;
import com.quickleap_assignment.subscription_service.dtos.SubscriptionDTO;
import com.quickleap_assignment.subscription_service.mappers.SubscriptionMapper;
import com.quickleap_assignment.subscription_service.services.SubscriptionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("/subscriptions")
public class SubscriptionController {
    private final SubscriptionService subscriptionService;

    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @PostMapping
    public ResponseEntity<SubscriptionDTO> createSubscription(@RequestBody CreateSubscriptionRequestDTO requestDTO) {
        return ResponseEntity.ok(SubscriptionMapper.toSubscriptionDTO(subscriptionService.createSubscription(requestDTO)));
    }
    @PutMapping("/{id}")
    public ResponseEntity<SubscriptionDTO> updateSubscription(@RequestBody CreateSubscriptionRequestDTO requestDTO, @PathVariable String id) {
        return ResponseEntity.ok(SubscriptionMapper.toSubscriptionDTO(subscriptionService.upgradeSubscription(requestDTO,id)));
    }
    @GetMapping("/{id}")
    public ResponseEntity<SubscriptionDTO> getSubscription(@PathVariable String id) {
        return ResponseEntity.ok(SubscriptionMapper.toSubscriptionDTO(subscriptionService.getSubscriptionById(id)));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSubscription(@PathVariable String id) {
        subscriptionService.cancelSubscription(id);
        return ResponseEntity.noContent().build();
    }
}
