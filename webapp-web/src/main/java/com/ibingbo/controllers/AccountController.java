package com.ibingbo.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ibingbo.models.Account;
import com.ibingbo.services.AccountService;

@Controller
@RequestMapping("/account")
public class AccountController {

	@Autowired
	private AccountService accountService;
	
	@RequestMapping("/list")
	@ResponseBody
	public List<Account> list(){
		return this.accountService.getAccounts();
		
	}
	
	@RequestMapping("/get/{id}")
	@ResponseBody
	public Map getAccount(@PathVariable("id") Integer id)throws Exception{
		Map result = new HashMap();
		result.put("errno", 0);
		result.put("errmsg", "success");
		try{
			Account account=this.accountService.getAccountById(id);
			result.put("data", account);
		}catch(Exception exception){
			exception.printStackTrace();
		}
		return result;
	}
}
