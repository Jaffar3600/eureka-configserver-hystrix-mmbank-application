package com.moneymoney.web.controller;

import java.util.ArrayList;
import java.util.List;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.moneymoney.web.entity.Transaction;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
@Service
@Controller
public class BankAppController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping("/DepositForm")
	public String depositForm() {
		return "DepositForm";
	}
	
	@HystrixCommand(defaultFallback = "depositFailure")
	@RequestMapping("/deposit")
	public String deposit(@ModelAttribute Transaction transaction,
			Model model) {
		
		restTemplate.postForEntity("http://localhost:9898/transactions/deposit", 
				transaction, null);
		
		model.addAttribute("message","Success!");
		return "DepositForm";
	}
	
	
	public String depositFailure() {
		return "DepositFailure";
	}

	@RequestMapping("/WithdrawForm")
	public String withdrawForm() {
		return "WithdrawForm";
	}
	
	
	@HystrixCommand(defaultFallback = "withdrawFailure")
	@RequestMapping("/withdraw")
	public String withdraw(@ModelAttribute Transaction transaction,
			Model model) 
	{
		
		restTemplate.postForEntity("http://localhost:9898/transactions/withdraw", 
				transaction, null);
		
		model.addAttribute("message","Success!");
		return "WithdrawForm";
	}
	
	

	public String withdrawFailure() {
		return "withdrawFailure";
	}
	
	
	
	@RequestMapping("/FundTransfer")
	public String FundTransfer() {
		return "FundTransfer";
	}
	
	@HystrixCommand(defaultFallback = "FundTransferFailure")
	@RequestMapping("/fundTransfer")
	public String fundTransfer(@RequestParam("sender") int senderAccountNumber, @RequestParam("receiver") int receiverAccountNumber, @ModelAttribute Transaction transaction,Model model) {
		transaction.setAccountNumber(senderAccountNumber);
		restTemplate.postForEntity("http://localhost:9898/transactions/withdraw", 
				transaction, null);
		transaction.setAccountNumber(receiverAccountNumber);
		restTemplate.postForEntity("http://localhost:9898/transactions/deposit", 
				transaction, null);
		model.addAttribute("message","Success!");
		return "FundTransfer";
	}
	
	public String FundTransferFailure() {
		return "FundTransferFailure";
	}
	
	@RequestMapping("/getStatements")
	public ModelAndView getStatement(@RequestParam("offset") int offset,@RequestParam("size") int size)
	{
		CurrentDataSet dataset = restTemplate.getForObject("http://localhost:9898/transactions/getstatements",CurrentDataSet.class);
		int currentSize = size ==0?5:size;
		int currentOffset = offset ==0?1:offset;
		Link next = linkTo(methodOn(BankAppController.class).getStatement(size+offset, currentSize)).withRel("next");
		Link previous = linkTo(methodOn(BankAppController.class).getStatement(offset-size, currentSize)).withRel("previous");
		List<Transaction> currentDataSet = new ArrayList<Transaction>();
		List<Transaction> transactions = dataset.getTransactions();
		for (int i = currentOffset - 1; i < currentSize + currentOffset - 1; i++) 
		{
			Transaction transaction = transactions.get(i);
			currentDataSet.add(transaction);
		}
		CurrentDataSet datasetList =  new CurrentDataSet(currentDataSet,next,previous);
		return new ModelAndView("statements","currentDataSet",datasetList);
	}
	
}
