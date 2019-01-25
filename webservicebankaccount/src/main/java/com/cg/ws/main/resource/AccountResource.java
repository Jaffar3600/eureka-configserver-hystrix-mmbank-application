package com.cg.ws.main.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.ws.main.account.Account;
import com.cg.ws.main.repository.AccountRepository;

@RestController
@RequestMapping("/accounts")
public class AccountResource {
	@Autowired
	private AccountRepository repo;

	@GetMapping
	public List<Account> getAllAccount() {
		return repo.findAll();
	}

	@GetMapping("/{accountNumber}")
	public Optional<Account> getAccountById(@PathVariable int accountNumber) {
		return repo.findById(accountNumber);
	}

	@GetMapping("/{accountNumber}/balance")
	public double getCurrentBalance(@PathVariable int accountNumber) {

		double currentBalance = repo.findById(accountNumber).get().getCurrentBalance();
		return currentBalance;
	}
	
	@PutMapping("/{accountNumber}")
	public void updateAccount(@PathVariable int accountNumber, @RequestParam("currentBalance") double currentBalance) {
		Account account = repo.findById(accountNumber).get();
		account.setCurrentBalance(currentBalance);
		repo.save(account);
	}
	

}
