package com.ibingbo.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibingbo.dao.AccountDao;
import com.ibingbo.mapper.AccountMapper;
import com.ibingbo.models.Account;

@Repository
public class AccountDaoImpl implements AccountDao {

	@Autowired
	private AccountMapper accountMapper;
	
	@Override
	public List<Account> getAccounts() {
		// TODO Auto-generated method stub
		return this.accountMapper.getAccounts();
	}

	@Override
	public Account getAccountById(Integer id) {
		// TODO Auto-generated method stub
		return this.accountMapper.getAccountById(id);
	}
	
	

}
