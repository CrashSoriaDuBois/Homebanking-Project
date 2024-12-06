package com.app.homebanking;

import com.app.homebanking.enums.TransactionType;
import com.app.homebanking.models.Account;
import com.app.homebanking.models.Client;
import com.app.homebanking.models.Transaction;
import com.app.homebanking.repositories.AccountRepository;
import com.app.homebanking.repositories.ClientRepository;
import com.app.homebanking.repositories.TransactionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class HomebankingApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomebankingApplication.class, args);
	}
	@Bean
	public CommandLineRunner initData(ClientRepository clientRepository, AccountRepository accountRepository, TransactionRepository transactionRepository){
		return (args -> {

			Client client1 = new Client("Melba", "Morel", "melba@mindhub.com");
			Account account1 = new Account("VIN003", LocalDateTime.now(), 5000);
			Account account2 = new Account("VIN002", LocalDateTime.now().plusDays(1), 7500);
			Transaction transaction1 = new Transaction(TransactionType.CREDIT, 4500, "description", LocalDateTime.now());
			Transaction transaction2 = new Transaction(TransactionType.DEBIT, 500, "description2", LocalDateTime.now());

			client1.addAccounts(account1);
			client1.addAccounts(account2);
			account1.addTransactions(transaction1);
			account1.addTransactions(transaction2);

			clientRepository.save(client1);
			accountRepository.save(account1);
			accountRepository.save(account2);
			transactionRepository.save(transaction1);
			transactionRepository.save(transaction2);

			Client client2 = new Client("Ines", "Risotto", "inessirotto@gmail.com");
			Account account3 = new Account("VIN005", LocalDateTime.now(),2500 );
			Account account4 = new Account("VIN004", (LocalDateTime.now()).plusDays(1),1000 );
			client2.addAccounts(account3);
			client2.addAccounts(account4);
			clientRepository.save(client2);
			accountRepository.save(account3);
			accountRepository.save(account4);
		});
	}
}
