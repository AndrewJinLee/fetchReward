package com.synergisticit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergisticit.domain.Transaction;
import com.synergisticit.repository.TransactionRepository;
@Service
public class TransactionServiceImpl implements TransactionService {
	
	@Autowired
	TransactionRepository fetchRepo;
	
	@Override
	public Transaction createRewards(Transaction rewards) {
		
		return fetchRepo.save(rewards);
	}
	
	@Override
	public Transaction addTransaction(Transaction rewards) {
		
		return fetchRepo.save(rewards);
	}

	@Override
	public Transaction getRewardsById(int rewardsId) {
		Transaction fetch = null;
		Optional<Transaction> optionalFetch = fetchRepo.findById(rewardsId);
		if(optionalFetch.isPresent()) {
			fetch = optionalFetch.get();
		}
		return fetch;
	}

	@Override
	public void deleteRewardsById(int rewardsId) {
		fetchRepo.deleteById(rewardsId);
		
	}

	@Override
	public List<Transaction> getAllRewards() {
		
		return fetchRepo.findAll();
	}

	

}
