package com.quickleap_assignment.user_service.controllers;


import com.quickleap_assignment.user_service.dtos.user.*;
import com.quickleap_assignment.user_service.services.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;



    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO userDTO){

        return ResponseEntity.ok(authService.login(userDTO.getEmail(), userDTO.getPassword()));
    }
    @PostMapping("/register")
    public RegisterResponseDTO register(@RequestBody RegisterRequestDTO userDTO){
      RegisterResponseDTO response = authService.register(userDTO);

       UserDTO created= response.getUser();

      return response;
    }
    @GetMapping("/authenticate")
    public UserDTO authenticate(HttpServletRequest request){
        String token = request.getHeader("Authorization").split(" ")[1];
        return authService.authenticate(token);
    }
}
