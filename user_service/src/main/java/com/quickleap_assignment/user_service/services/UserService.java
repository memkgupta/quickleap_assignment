package com.quickleap_assignment.user_service.services;


import com.quickleap_assignment.user_service.entities.User;
import com.quickleap_assignment.user_service.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder bCryptPasswordEncoder;


    public User getUserDetails(String id)
    {
        User user = userRepository.findById(id).orElse(null);
        return user;
    }
    public User getUserByEmail (String email)
    {
        return userRepository.findByEmail(email).orElse(null);
    }
    public User createUser(User user)
    {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
            return userRepository.save(user);
    }
    public boolean matchPassword(String password, String hashedPassword)
    {
        return bCryptPasswordEncoder.matches(password, hashedPassword);
    }

}
