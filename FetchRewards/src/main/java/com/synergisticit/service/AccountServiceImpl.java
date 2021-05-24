package com.synergisticit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergisticit.domain.Account;
import com.synergisticit.repository.AccountRepository;
@Service
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	AccountRepository acctRepo;
	
	@Override
	public Account createAccount(Account acct) {
		
		return acctRepo.save(acct);
	}

	@Override
	public Account getAccountById(int acctId) {
		Account account = null;
		Optional<Account> optionalAcct = acctRepo.findById(acctId);
		if(optionalAcct.isPresent()) {
			account = optionalAcct.get();
		}
		return account;
	}

	@Override
	public void deleteAccountById(int acctId) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Account> getAllAccounts() {
		
		return acctRepo.findAll();
	}

	@Override
	public Account findByPayer(String searchText) {
		
		return acctRepo.findByPayer(searchText);
	}

}
