package com.enigma.warungmakanbahariapi.service;

import com.enigma.warungmakanbahariapi.entity.UserCredential;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserCredential loadUserById(String userId);
}
