package org.example.tverleng.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.javassist.NotFoundException;
import org.example.tverleng.base.APIResponse;
import org.example.tverleng.base.BaseController;
import org.example.tverleng.jwt.JwtService;
import org.example.tverleng.model.request.AppUserRequest;
import org.example.tverleng.model.request.AuthRequest;
import org.example.tverleng.model.response.AppUserResponse;
import org.example.tverleng.model.response.AuthResponse;
import org.example.tverleng.service.AppUserService;
import org.example.tverleng.service.AuthService;
import org.example.tverleng.service.implementation.AuthServiceImplementation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/v1/auths")
@RequiredArgsConstructor
public class AuthController extends BaseController {
    private final AppUserService appUserService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final AuthServiceImplementation authServiceImplementation;
    private void authenticate(String email, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody AuthRequest request) throws Exception {
        log.info("Attempting to authenticate user: {}", request.getEmail());
        try {
            authenticate(request.getEmail(), request.getPassword());
        } catch (Exception e) {
            log.error("Authentication failed: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Authentication failed");
        }
        final UserDetails userDetails = appUserService.loadUserByUsername(request.getEmail());
        final String token = jwtService.generateToken(userDetails);
        AuthResponse authResponse = new AuthResponse(token);
        return response(APIResponse.builder()
                .message("Login successfully!!!")
                .success(true)
                .status(HttpStatus.CREATED)
                .payload(authResponse)
                .build());
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid AppUserRequest request){
        return response(APIResponse.builder()
                .message("Register successfully!!!")
                .success(true)
                .status(HttpStatus.CREATED)
                .payload(authServiceImplementation.register(request))
                .build());
    }
}