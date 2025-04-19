package org.example.tverleng.service.implementation;

import lombok.RequiredArgsConstructor;
import org.example.tverleng.model.entities.AppUser;
import org.example.tverleng.repository.AppUserRepository;
import org.example.tverleng.service.AppUserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppUserServiceImplementation implements AppUserService {
    private final AppUserRepository appUserRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return appUserRepository.getUserByEmail(email);
    }
}
