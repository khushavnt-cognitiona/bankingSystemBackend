package com.bank.service;

import com.bank.dto.JwtResponse;
import com.bank.dto.LoginRequest;

public interface AuthService {
	
	JwtResponse login(LoginRequest loginRequest);
	
		
	

}
