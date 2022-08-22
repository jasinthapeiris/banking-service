
package lk.example.bankingservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import lk.example.bankingservice.model.Account;
import lk.example.bankingservice.repository.AccountRepository;

/**
 * Date :2022-08-20. This class process the crud AccountService class
 *
 * @author Jasintha Peiris
 */
@Slf4j
@org.springframework.stereotype.Component
@RequiredArgsConstructor(onConstructor = @__({ @Autowired }))
public class AccountService {

	private final AccountRepository accountRepository;

	/**
	 * findAllAccounts
	 * 
	 * @return accountList
	 */
	public List<Account> findAllAccounts() {
		log.debug("AccountService findAllAccounts method calling.");
		List<Account> accountList = (List<Account>) accountRepository.findAll();
		return accountList;
	}

	/**
	 * findAccount
	 * 
	 * @param id
	 * @return account
	 */
	public Account findByAccountId(int id) {
		log.debug("AccountService findAccount method calling.");
		Account account = accountRepository.findByAccountId(id);
		return account;
	}

	/**
	 * saveAccount
	 * 
	 * @param account
	 * @return savedAccount
	 */
	public Account saveAccount(Account account) {
		log.debug("AccountService saveAccount method calling.");
		account.setCreatedDate(new Date());
		account.setUpdatedDate(new Date());
		Account savedAccount = accountRepository.save(account);
		return savedAccount;
	}

	/**
	 * updateAccount
	 * 
	 * @param account
	 * @return updatedAccount
	 */
	public Account updateAccount(Account account) {
		log.debug("AccountService updateAccount method calling.");
		Account existAccount = accountRepository.findByAccountId(account.getAccountId());
		account.setCreatedDate(existAccount.getCreatedDate());
		account.setUpdatedDate(new Date());
		Account updatedAccount = accountRepository.save(account);
		return updatedAccount;

	}

	/**
	 * deleteAccount
	 * 
	 * @param id
	 * @return message
	 */
	public String deleteAccount(int id) {
		log.debug("AccountService deleteAccount method calling.");
		Account account = accountRepository.findByAccountId(id);
		accountRepository.delete(account);
		return "Account deleted";
	}
}