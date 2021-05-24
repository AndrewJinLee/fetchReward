package com.synergisticit.service;

import java.util.List;

import com.synergisticit.domain.Account;
import com.synergisticit.domain.Transaction;


public interface AccountService {
	
	public Account createAccount(Account acct);//Create
	
	public Account getAccountById(int  acctId);//read
	
	public void deleteAccountById(int acctId);//delete
	
	public List<Account> getAllAccounts();//Read All

	public Account findByPayer(String searchText); //Read
	
}
