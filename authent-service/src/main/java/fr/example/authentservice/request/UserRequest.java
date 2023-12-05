package fr.example.authentservice.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {
    private String username;
    private String password;

    public UserRequest(String name, String email, String password) {
        this.username = name;
        this.password = password;
    }
}
