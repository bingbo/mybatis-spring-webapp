package com.ibingbo.dao.impl;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.ibingbo.dao.AccountDao;
import com.ibingbo.models.Account;

public class AccountDaoImpl extends SqlSessionDaoSupport implements AccountDao {

	@Override
	public List<Account> getAccounts() {
		// TODO Auto-generated method stub
		return (List)this.getSqlSession().selectList("com.ibingbo.mappers.account.getAccounts");
	}

}
