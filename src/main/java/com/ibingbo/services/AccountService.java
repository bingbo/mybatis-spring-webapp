package com.ibingbo.services;

import java.util.List;

import com.ibingbo.models.Account;

public interface AccountService {

	public List<Account> getAccounts();
	
	public Account getAccountById(Integer id);
}
