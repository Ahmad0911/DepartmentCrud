package org.example.departmentcrud.Controller;

import org.example.departmentcrud.dto.LoginRequest;
import org.example.departmentcrud.dto.LoginResponse;
import org.example.departmentcrud.Utility.JwtTokenUtil;
import org.example.departmentcrud.Services.UserDetailsService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserDetailsService userDetailsService;

    public AuthController(AuthenticationManager authenticationManager,
                          JwtTokenUtil jwtTokenUtil,
                          UserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        try {
            // 1. Authenticate
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );

            // 2. Generate JWT
            final UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            final String jwt = jwtTokenUtil.generateToken(userDetails);

            // 3. Return response (now matches your LoginResponse constructor)
            return ResponseEntity.ok(new LoginResponse(jwt, userDetails.getUsername()));

        } catch (BadCredentialsException ex) {
            return ResponseEntity
                    .badRequest()
                    .body("Invalid username or password");
        }
    }
}