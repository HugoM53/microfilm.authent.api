package fr.example.authentservice.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangePassword {
    private String password;
    private String username;
}