package com.bank.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.bank.dto.RegisterRequest;
import com.bank.entity.KycStatus;
import com.bank.entity.Role;
import com.bank.entity.User;
import com.bank.exception.UserAlreadyExistsException;
import com.bank.repository.UserRepository;
import com.bank.service.CibilService;
import com.bank.service.KycService;
import com.bank.service.UserService;

public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository repository;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	KycService kycService;
	@Autowired
	CibilService cibilService;
	

	@Override
	public User register(RegisterRequest request) {
		
		if(repository.existsByEmail(request.getEmail())) {
			throw new UserAlreadyExistsException("Email already registered");
		}
		User user=new User();
		user.setEmail(request.getEmail());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		user.setRole(Role.CUSTOMER);
		// that real time kyc and cibil logic
		KycStatus kycStatus=kycService.verfyKyc(request.getEmail());
		user.setKycStatus(kycStatus);
		if(kycStatus==kycStatus.VERIFIED) {
			user.setCibilScore(cibilService.fetchCibilScore(request.getEmail()));
			
		}else {
			user.setCibilScore(null);
		}
		
				
		return repository.save(user);
	}

}
