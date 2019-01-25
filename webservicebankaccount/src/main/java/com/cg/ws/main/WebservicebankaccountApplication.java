package com.cg.ws.main;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import com.cg.ws.main.account.CurrentAccount;
import com.cg.ws.main.account.SavingsAccount;
import com.cg.ws.main.repository.AccountRepository;

@EnableDiscoveryClient
@SpringBootApplication
public class WebservicebankaccountApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebservicebankaccountApplication.class, args);
	}

	@Bean
	public CommandLineRunner createAccounts(AccountRepository repo) {

		return (arg) -> {
			repo.save(new SavingsAccount(100, "jaffar", 1000.00, true));
			repo.save(new SavingsAccount(101, "Shiva", 2000.00, true));
			repo.save(new SavingsAccount(102, "prasanth", 3000.00, true));
			repo.save(new CurrentAccount(103, "hemalatha", 4000.0, 5000.0));

		};

	}

}
