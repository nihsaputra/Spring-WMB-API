package com.enigma.warungmakanbahariapi.controller;

import com.enigma.warungmakanbahariapi.model.request.AuthRequest;
import com.enigma.warungmakanbahariapi.model.response.AuthResponse;
import com.enigma.warungmakanbahariapi.model.response.WebResponse;
import com.enigma.warungmakanbahariapi.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping(path = "/register")
    public ResponseEntity<?> register(@RequestBody AuthRequest request){
        AuthResponse authResponse = authService.create(request);

        WebResponse<AuthResponse> response = WebResponse.<AuthResponse>builder()
                .status(HttpStatus.CREATED.getReasonPhrase())
                .message("successfuly create new account")
                .data(authResponse)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping(path = "/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request){
        String login = authService.login(request);

        WebResponse<String> response = WebResponse.<String>builder()
                .status(HttpStatus.CREATED.getReasonPhrase())
                .message("successfuly create new account")
                .data(login)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
