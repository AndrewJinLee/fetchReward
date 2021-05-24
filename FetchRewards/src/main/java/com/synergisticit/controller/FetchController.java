package com.synergisticit.controller;

import java.text.SimpleDateFormat;

import java.util.Collections;
import java.util.Comparator;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.synergisticit.domain.Account;
import com.synergisticit.domain.Transaction;
import com.synergisticit.repository.TransactionRepository;
import com.synergisticit.service.AccountService;
import com.synergisticit.service.TransactionService;

@RestController
public class FetchController {

	@Autowired
	TransactionService fetchService;

	@Autowired
	AccountService acctService;

	@Autowired
	TransactionRepository fetchRepo;

	@RequestMapping(value = "/addTransaction", method = RequestMethod.POST)
	public ResponseEntity<String> addTransaction(@RequestBody Transaction addTransaction)
			throws JSONException, JsonProcessingException {

		System.out.println("@@FetchRewards.FetchController.addTransaction()...  addTransaction : " + addTransaction);
		JSONObject jsonObject = new JSONObject(addTransaction);
		fetchService.createRewards(addTransaction);
		Optional<Account> optAcct = Optional.ofNullable(acctService.findByPayer(jsonObject.getString("payer")));
		Account account = null;
		if (!optAcct.isPresent()) {
			account = new Account();
			account.setPayer(jsonObject.getString("payer"));
			account.setPointsBalance(jsonObject.getInt("points"));
			acctService.createAccount(account);
		} else {
			account = acctService.findByPayer(jsonObject.getString("payer"));
			account.setPointsBalance(account.getPointsBalance() + jsonObject.getInt("points"));
			acctService.createAccount(account);
		}

		return new ResponseEntity<String>(jsonObject.toString(), HttpStatus.OK);

	}

	@RequestMapping(value = "/spendPoints", method = RequestMethod.POST)
	public ResponseEntity<String> addTransaction(@RequestBody String spendPoints)
			throws JSONException, JsonProcessingException {
		System.out.println("@@FetchRewards.FetchController.createReward()...  reward : " + spendPoints);
		JSONObject spendJsonObject = new JSONObject(spendPoints);
		int spend = spendJsonObject.getInt("points");
		System.out.println("spend : " + spend);
		
		Map<String, Integer> subMap = new TreeMap<String, Integer>();
		List<Transaction> trs = fetchService.getAllRewards();

		Collections.sort(trs, new Comparator<Transaction>() {

			@Override
			public int compare(Transaction o1, Transaction o2) {

				return o1.getTimestamp().compareTo(o2.getTimestamp());
			}

		});


		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HHmmss");
		for (Transaction tr : trs) {
			String[] dateAndTime = sdf.format(tr.getTimestamp()).split(" ");
			int dateToInt = Integer.parseInt(dateAndTime[0]);
			int timeToInt = Integer.parseInt(dateAndTime[1]);

			System.out.println(dateToInt);
			String temp = dateAndTime[0] + tr.getPayer();
			System.out.println(temp);
			if (!subMap.containsKey(temp)) {
				subMap.put(temp, tr.getPoints());
			} else {
				subMap.replace(temp, tr.getPoints() + subMap.get(temp));
			}
		}
		System.out.println(subMap.toString());
		Iterator<String> iter = subMap.keySet().iterator();
		int diff = spend;
		while (spend > 0) {
			while (iter.hasNext()) {
				String key = iter.next();
				int value = subMap.get(key);

				System.out.println(key + " : " + value);
				if (value > 0 && spend > 0) {
					if (spend > value) {
						spend = spend - value;
						value = 0;
					}else {
						value = value - spend;
						spend = 0;
					}
					
					subMap.replace(key, value);
				}
			}

		}
		
		System.out.println(subMap.toString());
		iter = subMap.keySet().iterator();
		while (iter.hasNext()) {
			String key = iter.next();
			String nameKey =key.substring(8, key.length());
			System.out.println(nameKey);
			int value = subMap.get(key);
			Account account = acctService.findByPayer(nameKey);
			account.setPointsBalance(value);
			acctService.createAccount(account);
		}
		
		
		return new ResponseEntity<String>(spendJsonObject.toString(), HttpStatus.OK);

	}

	@RequestMapping(value = "/getAllRewards", method = RequestMethod.GET)
	public ResponseEntity<List<Transaction>> getAllRewards() {

		System.out.println("@@FetchRewards.FetchController.getAllRewards()...");

		List<Transaction> rewards = fetchService.getAllRewards();
		System.out.println(rewards.toString());
		return new ResponseEntity<List<Transaction>>(rewards, HttpStatus.OK);

	}

	@RequestMapping(value = "/pointsBalance", method = RequestMethod.GET)
	public ResponseEntity<List<Account>> pointsBalance() {

		System.out.println("@@FetchRewards.FetchController.pointsBalance()...");

		List<Account> rewards = acctService.getAllAccounts();
		System.out.println(rewards.toString());
		return new ResponseEntity<List<Account>>(rewards, HttpStatus.OK);

	}
	
	
	

}
