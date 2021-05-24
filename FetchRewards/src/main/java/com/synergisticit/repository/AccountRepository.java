package com.synergisticit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.synergisticit.domain.Account;


public interface AccountRepository extends JpaRepository<Account, Integer> {
	
	@Query(value = "select * from Account where payer =:searchText", nativeQuery = true)
	Account findByPayer(String searchText);
	
}
