package com.synergisticit.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "fetchRewards")
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int rewardId;
	private String payer;
	private int points;
	private Date timestamp;

	public Transaction() {

	}

	public Transaction(int rewardId, String payer, int points, Date timestamp) {
		super();
		this.rewardId = rewardId;
		this.payer = payer;
		this.points = points;
		this.timestamp = timestamp;
	}

	public int getRewardId() {
		return rewardId;
	}

	public void setRewardId(int rewardId) {
		this.rewardId = rewardId;
	}

	public String getPayer() {
		return payer;
	}

	public void setPayer(String payer) {
		this.payer = payer;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "FetchRewards [rewardId=" + rewardId + ", payer=" + payer + ", points=" + points + ", timestamp="
				+ timestamp + "]";
	}

}
