package com.quickleap_assignment.subscription_service.dtos;

import lombok.Data;

import java.util.List;

@Data
public class PlanDTO {
    private String id;
    private String name;
    private String description;
    private List<String> features;
    private Integer price;

}
