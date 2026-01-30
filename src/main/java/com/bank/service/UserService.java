package com.bank.service;

import com.bank.dto.RegisterRequest;
import com.bank.entity.User;

public interface UserService {
    User register(RegisterRequest request);
}
