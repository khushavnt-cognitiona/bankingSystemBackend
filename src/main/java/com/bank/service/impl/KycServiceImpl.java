package com.bank.service.impl;

import java.util.Random;

import com.bank.entity.KycStatus;
import com.bank.service.KycService;

public class KycServiceImpl implements KycService {

	@Override
	public KycStatus verfyKyc(String email) {
		
		int result=new Random().nextInt(10);
		
		if(result<7) {
			return KycStatus.VERIFIED;
			
		}else {
			return KycStatus.REJECTED;
			
		}
			
		
	}

}
