package fr.example.authentservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.example.authentservice.repository.TokenRepository;

@Service
public class TokenServiceImpl implements TokenService {
    @Autowired
    TokenRepository repository;

    @Override
    public String getToken() {
        return repository.findAll().get(0).getToken_name();
    }
}
