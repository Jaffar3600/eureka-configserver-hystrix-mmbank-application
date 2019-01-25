package com.cg.ws.transaction.main.service;

import java.util.List;

import com.cg.ws.transaction.main.transaction.Transaction;

public interface TransactionService {
	 double deposit(int accountNumber, double amount, double currentBalance,
			 String transactionDetails);
	Double withdraw(int accountNumber, double amount, double currentBalance, String transactionDetails);
	List<Transaction> getStatement();
		 
}