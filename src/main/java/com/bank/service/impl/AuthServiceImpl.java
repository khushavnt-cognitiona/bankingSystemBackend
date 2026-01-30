package com.bank.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.bank.dto.JwtResponse;
import com.bank.dto.LoginRequest;
import com.bank.entity.KycStatus;
import com.bank.entity.User;
import com.bank.exception.InvalidCredentialsException;
import com.bank.exception.KycNotVerifiedException;
import com.bank.repository.UserRepository;
import com.bank.security.JwtUtil;
import com.bank.service.AuthService;

public class AuthServiceImpl implements AuthService {
	@Autowired
	UserRepository userRepository;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	JwtUtil jwtUtil;

	@Override
	public JwtResponse login(LoginRequest loginRequest) {
		
		User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() ->
                        new InvalidCredentialsException("Invalid email or password"));
		  if(!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
			  throw new  InvalidCredentialsException("Invalid email or password");
		  }
		  if(user.getKycStatus()!=KycStatus.VERIFIED) {
			  throw new KycNotVerifiedException(
					  "KYC not verified. Login not allowed."
					  
					  );
			  
		  }
		  String token=jwtUtil.generateToken(user.getEmail());
		return new JwtResponse(token);
	}

}
