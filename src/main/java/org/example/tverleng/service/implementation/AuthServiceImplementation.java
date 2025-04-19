package org.example.tverleng.service.implementation;

import lombok.RequiredArgsConstructor;
import org.example.tverleng.model.entities.AppUser;
import org.example.tverleng.model.mapper.AppUserMapper;
import org.example.tverleng.model.request.AppUserRequest;
import org.example.tverleng.model.response.AppUserResponse;
import org.example.tverleng.repository.AppUserRepository;
import org.example.tverleng.service.AuthService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImplementation implements AuthService {
    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;
//    private final AppUserMapper appUserMapper;

    @Override
    public AppUserResponse register(AppUserRequest appUserRequest) {
        appUserRequest.setPassword(passwordEncoder.encode(appUserRequest.getPassword()));
        AppUser user = appUserRepository.register(appUserRequest);
        return new AppUserResponse(user.getUserId() , appUserRequest.getFullName(), appUserRequest.getEmail());
    }
}
