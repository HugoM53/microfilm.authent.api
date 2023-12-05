package fr.example.authentservice.security.jwt;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class JwtClaim {
    private String username;
}
