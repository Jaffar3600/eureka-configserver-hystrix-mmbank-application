package com.cg.ws.transaction.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.ws.transaction.main.transaction.Transaction;
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer>{

}
