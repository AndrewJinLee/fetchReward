package com.synergisticit.service;

import java.util.List;

import com.synergisticit.domain.Transaction;


public interface TransactionService {
	
	public Transaction createRewards(Transaction rewards);//Create
	
	public Transaction getRewardsById(int  rewardsId);//read
	
	public void deleteRewardsById(int rewardsId);//delete
	
	public List<Transaction> getAllRewards();//Read All

	Transaction addTransaction(Transaction rewards);
	
	
}
