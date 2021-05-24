package com.synergisticit.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Account")
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int accountId;
	private String payer;
	private int pointsBalance;
	

	public Account() {

	}


	public Account(int accountId, String payer, int pointsBalance) {
		super();
		this.accountId = accountId;
		this.payer = payer;
		this.pointsBalance = pointsBalance;
	}


	public int getAccountId() {
		return accountId;
	}


	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}


	public String getPayer() {
		return payer;
	}


	public void setPayer(String payer) {
		this.payer = payer;
	}


	public int getPointsBalance() {
		return pointsBalance;
	}


	public void setPointsBalance(int pointsBalance) {
		this.pointsBalance = pointsBalance;
	}


	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", payer=" + payer + ", pointsBalance=" + pointsBalance + "]";
	}
	
	
	

}
