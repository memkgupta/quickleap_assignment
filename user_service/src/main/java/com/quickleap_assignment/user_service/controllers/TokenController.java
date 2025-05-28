package com.quickleap_assignment.user_service.controllers;


import com.quickleap_assignment.user_service.dtos.tokens.TokenDTO;
import com.quickleap_assignment.user_service.services.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/token")
public class TokenController {

    private final TokenService tokenService;

    public TokenController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @GetMapping("/refresh-token")
    public ResponseEntity<TokenDTO> refreshAccessToken(HttpServletRequest request) {
        if(request.getHeader("X-REFRESH-TOKEN") == null) {
            throw new RuntimeException("X-REFRESH-TOKEN header is null");
        }
        String token = request.getHeader("X-REFRESH-TOKEN");
        String refreshToken = tokenService.refreshToken(token);
       TokenDTO tokenDTO = new TokenDTO();
       tokenDTO.setRefreshToken(refreshToken);
        return ResponseEntity.ok(tokenDTO);
    }

}
