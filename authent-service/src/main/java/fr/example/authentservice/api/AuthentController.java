package fr.example.authentservice.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import fr.example.authentservice.request.UserRequest;
import fr.example.authentservice.response.AuthResponse;
import fr.example.authentservice.security.jwt.JwtUtils;

@RestController
@RequestMapping("/api/auth")
public class AuthentController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping
    public AuthResponse auth(@RequestBody UserRequest userRequest) {
        try {
            Authentication authentication = new UsernamePasswordAuthenticationToken(userRequest.getUsername(),
                    userRequest.getPassword());

            authentication = this.authenticationManager.authenticate(authentication);

            SecurityContextHolder.getContext().setAuthentication(authentication);

            return AuthResponse.builder()
                    .success(true)
                    .token(JwtUtils.generate(authentication))
                    .build();
        } catch (Exception e) {
            return AuthResponse.builder()
                    .success(false)
                    .token(e.getMessage())
                    .build();

        }
    }
}