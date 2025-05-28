package com.quickleap_assignment.user_service.dtos.user;

import lombok.Data;

@Data
public class RegisterRequestDTO {
    private String username;
    private String email;
    private String password;
}
