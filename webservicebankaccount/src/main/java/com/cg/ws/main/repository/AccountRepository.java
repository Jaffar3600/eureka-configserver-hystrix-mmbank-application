package com.cg.ws.main.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cg.ws.main.account.Account;
@Repository
public interface AccountRepository extends MongoRepository<Account, Integer>{
	
}
