package com.enigma.warungmakanbahariapi.service.impl;

import com.enigma.warungmakanbahariapi.constan.ERole;
import com.enigma.warungmakanbahariapi.entity.Customer;
import com.enigma.warungmakanbahariapi.entity.Role;
import com.enigma.warungmakanbahariapi.entity.UserCredential;
import com.enigma.warungmakanbahariapi.model.request.AuthRequest;
import com.enigma.warungmakanbahariapi.model.response.AuthResponse;
import com.enigma.warungmakanbahariapi.repository.UserCredentialRepository;
import com.enigma.warungmakanbahariapi.security.JwtUtil;
import com.enigma.warungmakanbahariapi.service.AuthService;
import com.enigma.warungmakanbahariapi.service.CustomerService;
import com.enigma.warungmakanbahariapi.service.RoleService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserCredentialRepository userCredentialRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final CustomerService customerService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;


    @PostConstruct
    public void initSuperAdmin() {
        Optional<UserCredential> optionalUserCredential = userCredentialRepository.findByEmail("superadmin@gmail.com");
        if (optionalUserCredential.isPresent())return;

        Role roleCustomer = roleService.getOrSave(ERole.ROLE_CUSTOMER);
        Role roleAdmin = roleService.getOrSave(ERole.ROLE_ADMIN);
        Role roleSuperAdmin = roleService.getOrSave(ERole.ROLE_SUPER_ADMIN);

        String hashPassword = passwordEncoder.encode("password");
        UserCredential buildUserCredential = UserCredential.builder()
                .email("superadmin@gmail.com")
                .password(hashPassword)
                .roles(List.of(roleCustomer,roleAdmin,roleSuperAdmin))
                .build();

        UserCredential userCredential = userCredentialRepository.saveAndFlush(buildUserCredential);

        Customer buildCustomer = Customer.builder()
                .userCredential(userCredential)
                .build();
        customerService.create(buildCustomer);


    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AuthResponse create(AuthRequest request) {
        Optional<UserCredential> optionalUserCredential = userCredentialRepository.findByEmail(request.getEmial());
        if (optionalUserCredential.isPresent())throw new ResponseStatusException(HttpStatus.CONFLICT,"email existed");

        Role roleCustomer = roleService.getOrSave(ERole.ROLE_CUSTOMER);

        String hashPassword = passwordEncoder.encode(request.getPassword());
        UserCredential buildUserCredential = UserCredential.builder()
                .email(request.getEmial())
                .password(hashPassword)
                .roles(List.of(roleCustomer))
                .build();

        UserCredential userCredential = userCredentialRepository.saveAndFlush(buildUserCredential);
        List<String> listRole = userCredential.getRoles().stream().map(role -> role.getRole().name()).toList();


        Customer buildCustomer = Customer.builder()
                .userCredential(userCredential)
                .build();
        customerService.create(buildCustomer);


        return AuthResponse.builder()
                .email(userCredential.getEmail())
                .roles(listRole)
                .build();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AuthResponse createAdmin(AuthRequest request) {
        Optional<UserCredential> optionalUserCredential = userCredentialRepository.findByEmail(request.getEmial());
        if (optionalUserCredential.isPresent())throw new ResponseStatusException(HttpStatus.CONFLICT,"email existed");

        Role roleCustomer = roleService.getOrSave(ERole.ROLE_CUSTOMER);
        Role roleAdmin = roleService.getOrSave(ERole.ROLE_ADMIN);

        String hashPassword = passwordEncoder.encode(request.getPassword());
        UserCredential buildUserCredential = UserCredential.builder()
                .email(request.getEmial())
                .password(hashPassword)
                .roles(List.of(roleCustomer,roleAdmin))
                .build();

        UserCredential userCredential = userCredentialRepository.saveAndFlush(buildUserCredential);
        List<String> listRole = userCredential.getRoles().stream().map(role -> role.getRole().name()).toList();

        Customer buildCustomer = Customer.builder()
                .userCredential(userCredential)
                .build();
        customerService.create(buildCustomer);

        return AuthResponse.builder()
                .email(userCredential.getEmail())
                .roles(listRole)
                .build();
    }

    @Override
    public String login(AuthRequest request) {

        // login dari spring security untuk authentication
        Authentication authenticationToken = new UsernamePasswordAuthenticationToken(request.getEmial(),request.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        // simpan sesi user di database spring boot sementara, bertujuan untuk mengakses resource tertentu di kemudian
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // mengambil principal dan di jadikan UserCredential untuk di generate dijadikan token
        UserCredential userCredential = (UserCredential) authentication.getPrincipal();

        return jwtUtil.generateToken(userCredential);

    }


}
