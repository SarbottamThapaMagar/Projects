package com.mcds5510.service;

import javax.jws.WebMethod;
import javax.jws.WebService;

import com.mcds5510.Main;

@WebService
public class TransWebServices {

	@WebMethod
	public void userTransactionType(String txnType) {
		Main.userTransactionSelection(txnType);
	}
	
}
