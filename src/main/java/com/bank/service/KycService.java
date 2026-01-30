package com.bank.service;

import com.bank.entity.KycStatus;

public interface KycService {
	
	KycStatus verfyKyc(String email);

}
