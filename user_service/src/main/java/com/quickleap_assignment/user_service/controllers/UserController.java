package com.quickleap_assignment.user_service.controllers;


import com.quickleap_assignment.user_service.dtos.user.UserDTO;
import com.quickleap_assignment.user_service.entities.User;
import com.quickleap_assignment.user_service.exceptions.UserNotFoundException;
import com.quickleap_assignment.user_service.services.CustomUserDetailsService;
import com.quickleap_assignment.user_service.services.JWTService;
import com.quickleap_assignment.user_service.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class UserController {

    private final UserService userService;
    private final JWTService jwtService;
    private final CustomUserDetailsService customUserDetailsService;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, JWTService jwtService, CustomUserDetailsService customUserDetailsService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.customUserDetailsService = customUserDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/{id}")
    public UserDTO getUserDetails(@PathVariable String id)  {
        User user = userService.getUserDetails(id);
        if(user == null){
            throw new UserNotFoundException("User not found");
        }
        return user.userDTO();
    }



}
