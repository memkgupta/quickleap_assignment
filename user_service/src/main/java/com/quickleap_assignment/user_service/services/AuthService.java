package com.quickleap_assignment.user_service.services;

import com.quickleap_assignment.user_service.config.UserDetailsImpl;
import com.quickleap_assignment.user_service.dtos.tokens.TokenDTO;
import com.quickleap_assignment.user_service.dtos.user.LoginResponseDTO;
import com.quickleap_assignment.user_service.dtos.user.RegisterRequestDTO;
import com.quickleap_assignment.user_service.dtos.user.RegisterResponseDTO;
import com.quickleap_assignment.user_service.dtos.user.UserDTO;

import com.quickleap_assignment.user_service.entities.User;
import com.quickleap_assignment.user_service.exceptions.InvalidTokenException;
import com.quickleap_assignment.user_service.exceptions.UserAlreadyExistsException;
import com.quickleap_assignment.user_service.exceptions.UserNotFoundException;
import com.quickleap_assignment.user_service.repositories.TokenRepository;
import com.quickleap_assignment.user_service.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.sql.Timestamp;

@Service
@RequiredArgsConstructor
public class AuthService {


    private final JWTService jwtService;
    private final TokenService tokenService;

    private final TokenRepository tokenRepository;
    private final UserService userService;

    public RegisterResponseDTO register(RegisterRequestDTO registerRequestDTO) {


            User userAlreadyExists = userService.getUserByEmail(registerRequestDTO.getEmail());
            if (userAlreadyExists != null) {
                throw new UserAlreadyExistsException(registerRequestDTO.getEmail());
            }
            User user = new User();
            user.setPassword(registerRequestDTO.getPassword());
            user.setEmail(registerRequestDTO.getEmail());
            user.setUsername(registerRequestDTO.getUsername());

            userService.createUser(user);
            UserDTO resUserDTO = new UserDTO();
            resUserDTO.setEmail(registerRequestDTO.getEmail());
            resUserDTO.setUsername(registerRequestDTO.getUsername());
            resUserDTO.setName(registerRequestDTO.getUsername());
            resUserDTO.setId(user.getId())
            ;
            resUserDTO.setCreatedAt(new Timestamp(System.currentTimeMillis()));
            String accessToken = jwtService.generateToken(registerRequestDTO.getEmail());
            String refreshToken = tokenService.generateToken(user);
            RegisterResponseDTO response = new RegisterResponseDTO();
            TokenDTO tr = new TokenDTO();
                    tr.setAccessToken(accessToken);
                    tr.setRefreshToken(refreshToken);

            response.setTokens(
                    tr
            );
            response.setUser(resUserDTO);
            return response;
        }



    public LoginResponseDTO login(String email, String password) {
        try{
            User user = userService.getUserByEmail(email);
            if (user == null) {
                throw new RuntimeException("User not found");
            }
            if (!userService.matchPassword(password,user.getPassword())) {

                throw new BadCredentialsException("Bad credentials");
            }
            String accessToken = jwtService.generateToken(email);
            String refreshToken = tokenService.generateToken(user);
            UserDTO userDTO = new UserDTO();
            userDTO.setEmail(user.getEmail());
            userDTO.setUsername(user.getUsername());
            userDTO.setId(user.getId());
            userDTO.setCreatedAt(new Timestamp(System.currentTimeMillis()));
            LoginResponseDTO response  =new LoginResponseDTO();
            response.setUser(userDTO);
            TokenDTO tr = new TokenDTO();
            tr.setAccessToken(accessToken);
            tr.setRefreshToken(refreshToken);
            response.setTokens(tr);
            return response;
        }
        catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException("Internal server error");
        }
    }

    public UserDTO authenticate(String token)
    {
        try{
            String email = jwtService.extractUserName(token);
            User user =userService.getUserByEmail(email);
            if (user == null) {
            throw new UserNotFoundException("User not found");
            }
           if(jwtService.isTokenValid(token,new UserDetailsImpl(user)))
           {
               UserDTO userDTO = new UserDTO();
               userDTO.setEmail(user.getEmail());
               userDTO.setUsername(user.getUsername());
               userDTO.setId(user.getId());
               userDTO.setAvatar(user.getAvatar());
               userDTO.setBio(user.getBio());
               userDTO.setChannelId(user.getChannelId());
               userDTO.setName(user.getName());
                userDTO.setCreatedAt(new Timestamp(System.currentTimeMillis()));
               return userDTO;
           }
else{
    throw new InvalidTokenException("Invalid token");
           }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}