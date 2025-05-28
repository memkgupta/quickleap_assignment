package com.quickleap_assignment.user_service.services;


import com.quickleap_assignment.user_service.config.UserDetailsImpl;
import com.quickleap_assignment.user_service.entities.User;
import com.quickleap_assignment.user_service.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username).orElse(null);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        return new UserDetailsImpl(user);
    }
}
