package com.hosp.login.controls;

import com.hosp.login.models.Role;
import com.hosp.login.models.RoleName;
import com.hosp.login.models.User;
import com.hosp.login.models.UserDetailsImpl;
import com.hosp.login.records.LoginRequest;
import com.hosp.login.records.MessageResponse;
import com.hosp.login.records.SignupRequest;
import com.hosp.login.records.response.JwtResponse;
import com.hosp.login.repos.RoleRepository;
import com.hosp.login.repos.UserRepository;
import com.web.demo.utils.JwtTokenUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("auth")
//@CrossOrigin(origins = "*", maxAge = 3600)
@CrossOrigin(origins = "*")
public class AuthenticateRestController {

    AuthenticationManager authenticationManager;
    UserRepository userRepository;
    RoleRepository roleRepository;
    PasswordEncoder encoder;

    JwtTokenUtils jwtTokenUtils;

    @Autowired
    public AuthenticateRestController setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        return this;
    }

    @Autowired
    public AuthenticateRestController setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
        return this;
    }

    @Autowired
    public AuthenticateRestController setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
        return this;
    }

    @Autowired
    public AuthenticateRestController setEncoder(PasswordEncoder encoder) {
        this.encoder = encoder;
        return this;
    }

    @Autowired
    public AuthenticateRestController setJwtTokenUtils(JwtTokenUtils jwtTokenUtils) {
        this.jwtTokenUtils = jwtTokenUtils;
        return this;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(loginRequest.username(),
                                loginRequest.password()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", userDetails.getUsername());
        claims.put("email", userDetails.getEmail());
        claims.put("roles", userDetails.getAuthorities());
        String jwt = jwtTokenUtils.generateJwtToken(claims);

        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(
                new JwtResponse(jwt,
                        userDetails.getId(),
                        userDetails.getUsername(),
                        userDetails.getEmail(),
                        roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.username())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.email())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        User user = User.builder()
                .username(signUpRequest.username())
                .email(signUpRequest.email())
                .password(encoder.encode(signUpRequest.password()))
                .phone(signUpRequest.phone())
                .build();

        Set<String> strRoles = signUpRequest.role();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "patient":
                        Role patientRole = roleRepository.findByName(RoleName.ROLE_PATIENT)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(patientRole);

                        break;
                    case "doctor":
                        Role doctorRole = roleRepository.findByName(RoleName.ROLE_DOCTOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(doctorRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}
