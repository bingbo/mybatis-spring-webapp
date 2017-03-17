package com.ibingbo.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibingbo.dao.AccountDao;
import com.ibingbo.models.Account;
import com.ibingbo.services.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountDao accountDao;
	
	@Override
	public List<Account> getAccounts() {
		// TODO Auto-generated method stub
		return this.accountDao.getAccounts();
	}

	@Override
	public Account getAccountById(Integer id) {
		// TODO Auto-generated method stub
		return this.accountDao.getAccountById(id);
	}

}
