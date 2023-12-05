package fr.example.authentservice.security.jwt;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Optional;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class JwtUtils {
    private static final String JWT_KEY = "mNQmne72vhFLyWJownkAdCzn3oOwWxEW6BefD1FcfurEAI8q66hTGtmCTMK33C6l";
    private static final int JWT_EXPIRATION = 3600000;

    private JwtUtils() {
    }

    public static String generate(Authentication authentication) {
        SecretKey key = Keys.hmacShaKeyFor(JWT_KEY.getBytes(StandardCharsets.UTF_8));
        Date now = new Date();

        log.debug("Generating token for user {} ...", authentication.getName());

        return Jwts.builder()
                .setSubject(authentication.getName())
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + JWT_EXPIRATION))
                .signWith(key)
                .compact();
    }

    public static Optional<JwtClaim> getClaim(String token) {
        if (token == null || token.isBlank()) {
            return Optional.empty();
        }

        try {
            log.debug("Validating token then claim username ...");

            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(JWT_KEY.getBytes(StandardCharsets.UTF_8))
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            return Optional.ofNullable(
                    JwtClaim.builder()
                            .username(claims.getSubject())
                            .build());
        }

        catch (MalformedJwtException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
        }

        catch (ExpiredJwtException e) {
            log.error("JWT token is expired: {}", e.getMessage());
        }

        catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported: {}", e.getMessage());
        }

        catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {}", e.getMessage());
        }

        catch (SignatureException e) {
            log.error("JWT signature does not match: {}", e.getMessage());
        }

        return Optional.empty();
    }
}
