package com.quickleap_assignment.subscription_service.services;

import com.quickleap_assignment.subscription_service.dtos.CreateSubscriptionRequestDTO;
import com.quickleap_assignment.subscription_service.dtos.SubscriptionDTO;
import com.quickleap_assignment.subscription_service.entities.Plan;
import com.quickleap_assignment.subscription_service.entities.RenewalInterval;
import com.quickleap_assignment.subscription_service.entities.Subscription;
import com.quickleap_assignment.subscription_service.entities.SubscriptionStatus;
import com.quickleap_assignment.subscription_service.exceptions.PlanNotFoundException;
import com.quickleap_assignment.subscription_service.exceptions.SubscriptionNotFoundException;
import com.quickleap_assignment.subscription_service.repositories.SubscriptionRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
public class SubscriptionService {

    private final PlanService planService;
    private final SubscriptionRepository subscriptionRepository;

    public SubscriptionService(PlanService planService, SubscriptionRepository subscriptionRepository) {
        this.planService = planService;
        this.subscriptionRepository = subscriptionRepository;
    }

    public Subscription createSubscription(CreateSubscriptionRequestDTO request) {

            // 1. Find the plan
           Plan plan = planService.findById(request.getPlanId());
            if (plan==null) {
                throw new PlanNotFoundException("Plan not found");
            }


            // 2. Create Subscription entity
            Subscription subscription = new Subscription();
            subscription.setPlan(plan);
            subscription.setRenewalInterval(request.getRenewalInterval());
            subscription.setStatus(SubscriptionStatus.ACTIVE);

            Timestamp now = Timestamp.from(Instant.now());
            subscription.setStartDate(now);

            Timestamp endDate;
            endDate = getEndDate(request.getRenewalInterval(), now);
            subscription.setEndDate(endDate);

            // 3. Save subscription
            Subscription savedSubscription = subscriptionRepository.save(subscription);


            return subscription;


    }

    private static Timestamp getEndDate(RenewalInterval interval, Timestamp now) {
        Timestamp endDate;
        switch (interval) {
            case ANNUALLY -> endDate = Timestamp.from(now.toInstant().plus(365, ChronoUnit.DAYS));
            default -> endDate = Timestamp.from(now.toInstant().plus(30, ChronoUnit.DAYS));
        }
        return endDate;
    }

    public Subscription getSubscriptionById(String id) {

            Subscription subscription = subscriptionRepository.findById(id).orElse(null);
            return subscription;


    }
    public Subscription upgradeSubscription(CreateSubscriptionRequestDTO subscriptionDTO,String id)
    {

            Subscription subscription =getSubscriptionById(id);
            if(subscription==null)
            {
                throw new SubscriptionNotFoundException("Subscription not found");
            }
            if(subscription.getStatus()==SubscriptionStatus.CANCELLED)
            {
                throw new SubscriptionNotFoundException("Subscription not found");
            }
            if(subscriptionDTO.getPlanId()!=null && !subscriptionDTO.getPlanId().equals(subscription.getPlan().getId()))
            {
                Plan plan = planService.findById(subscriptionDTO.getPlanId());
                if(plan==null)
                {
                    throw new PlanNotFoundException("Plan not found");
                }
                subscription.setPlan(plan);
            }
            if(subscriptionDTO.getRenewalInterval()!=null && !subscriptionDTO.getRenewalInterval().equals(subscription.getRenewalInterval()))
            {
                subscription.setRenewalInterval(subscriptionDTO.getRenewalInterval());
                subscription.setEndDate(getEndDate(subscriptionDTO.getRenewalInterval(), subscription.getEndDate()));
            }
            subscription.setStatus(SubscriptionStatus.ACTIVE);
            return subscriptionRepository.save(subscription);





    }
    public Subscription cancelSubscription(String id) {
        Subscription subscription = getSubscriptionById(id);
        if(subscription==null)
        {
            throw new SubscriptionNotFoundException("Subscription not found");
        }
        subscription.setStatus(SubscriptionStatus.CANCELLED);
        return subscriptionRepository.save(subscription);
    }
}
