package org.example.tverleng.service;


import org.example.tverleng.model.request.AppUserRequest;
import org.example.tverleng.model.response.AppUserResponse;

public interface AuthService {
    AppUserResponse register(AppUserRequest appUserRequest);
}
