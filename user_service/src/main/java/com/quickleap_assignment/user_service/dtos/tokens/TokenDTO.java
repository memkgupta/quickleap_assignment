package com.quickleap_assignment.user_service.dtos.tokens;

import lombok.Data;

@Data
public class TokenDTO {
    private String accessToken;
    private String refreshToken;
}
