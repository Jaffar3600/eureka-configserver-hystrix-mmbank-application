package com.cg.ws.transaction.main.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.ws.transaction.main.repository.TransactionRepository;
import com.cg.ws.transaction.main.transaction.Transaction;
import com.cg.ws.transaction.main.transaction.TransactionType;

@Service
public class TransactionServiceImpl implements TransactionService{
	@Autowired
	private TransactionRepository repo;
		
	
	 public double deposit(int accountNumber, double amount, double currentBalance, String transactionDetails) {
		 
		 Transaction transaction =new Transaction();
		 transaction.setAccountNumber(accountNumber);
		 transaction.setAmount(amount);
		 currentBalance = currentBalance+amount;
		 System.out.println(currentBalance);
		 transaction.setCurrentBalance(currentBalance);
		 transaction.setTransactionDetails(transactionDetails);
		 transaction.setTransactionType(TransactionType.DEPOSIT);
		 transaction.setTransactionDate(LocalDateTime.now());
		 repo.save(transaction);
		 return currentBalance;
	 }


	@Override
	public Double withdraw(int accountNumber, double amount, double currentBalance, String transactionDetails) {
		Transaction transaction = new Transaction();
		transaction.setAccountNumber(accountNumber);
		 transaction.setAmount(amount);
		currentBalance = currentBalance-amount;
		transaction.setCurrentBalance(currentBalance);
		transaction.setTransactionDetails(transactionDetails);
		transaction.setTransactionType(TransactionType.WITHDRAW);
		transaction.setTransactionDate(LocalDateTime.now());
		repo.save(transaction);
		return currentBalance;
	}
	 
	@Override
	public List<Transaction> getStatement() {
		return repo.findAll();
	}
	
	
	
}
