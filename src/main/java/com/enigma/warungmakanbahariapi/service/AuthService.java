package com.enigma.warungmakanbahariapi.service;

import com.enigma.warungmakanbahariapi.model.request.AuthRequest;
import com.enigma.warungmakanbahariapi.model.response.AuthResponse;

public interface AuthService {

    AuthResponse create(AuthRequest request);

    String login(AuthRequest request);

    AuthResponse createAdmin(AuthRequest request);


}
