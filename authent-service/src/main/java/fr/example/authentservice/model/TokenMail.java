package fr.example.authentservice.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenMail {
    private String email;
    private String tokenName;
}
