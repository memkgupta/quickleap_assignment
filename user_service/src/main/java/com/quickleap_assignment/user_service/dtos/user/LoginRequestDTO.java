package com.quickleap_assignment.user_service.dtos.user;

import lombok.Data;

@Data
public class LoginRequestDTO {
    private String email;
    private String password;
}
