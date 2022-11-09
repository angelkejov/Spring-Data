package com.example.accountsystem.services;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

public interface AccountService {
    void withDrawMoney(BigDecimal money, Long id);
    void transferMoney(BigDecimal money, Long id);
}
