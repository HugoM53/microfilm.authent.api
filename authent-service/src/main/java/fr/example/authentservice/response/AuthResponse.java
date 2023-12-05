package fr.example.authentservice.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AuthResponse {
    private boolean success;
    private String token;
}
