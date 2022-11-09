package com.example.accountsystem.services;

import com.example.accountsystem.models.User;
import org.springframework.stereotype.Service;

public interface UserService {
    void registerUser(User user);
}
