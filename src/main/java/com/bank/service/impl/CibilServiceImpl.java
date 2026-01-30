package com.bank.service.impl;

import java.util.Random;

import com.bank.service.CibilService;

public class CibilServiceImpl implements CibilService {

	@Override
	public Integer fetchCibilScore(String email) {
		
		
		return 300+new Random().nextInt(600);
	}
 
}
