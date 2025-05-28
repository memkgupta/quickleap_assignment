package com.quickleap_assignment.user_service.dtos.user;

import com.quickleap_assignment.user_service.dtos.tokens.TokenDTO;
import lombok.Data;

@Data
public class LoginResponseDTO {
    private TokenDTO tokens;
    private UserDTO user;
}
