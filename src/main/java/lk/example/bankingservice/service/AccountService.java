/*
 *  @author Jasintha Peiris
 *  @version 0.0.1 2022/08/22
 *  E-Mail jasinthaamakara@gmail.com
 *
 *  Copyright (c), Jasintha Peiris  All Rights Reserved.
 *  PROPRIETARY AND COPYRIGHT NOTICE.
 *  This software product contains information which is proprietary to
 *  and considered a trade secret Jasintha Peiris .
 *  It is expressly agreed that it shall not be reproduced in whole or part,
 *  disclosed, divulged or otherwise made available to any third party directly
 *  or indirectly.  Reproduction of this product for any purpose is prohibited
 *  without written authorization from the Jasintha Peiris
 *  All Rights Reserved.
 */
package lk.example.bankingservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import lk.example.bankingservice.exception.ResourceNotFoundException;
import lk.example.bankingservice.model.Account;
import lk.example.bankingservice.repository.AccountRepository;
import lk.example.bankingservice.util.Constant;

/**
 * Date :2022-08-222. This class process the crud AccountService class
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
		Account account = accountRepository.findByAccountNumber(id);
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

		if (StringUtils.isEmpty(account.getAccountName())) {
			throw new ResourceNotFoundException(Constant.ACCOUNT_NAME_NOT_FOUND);
		}
		if (StringUtils.isEmpty(account.getIdNumberType())) {
			throw new ResourceNotFoundException(Constant.ID_NUMBER_TYPE_NOT_FOUND);
		}
		if (StringUtils.isEmpty(account.getIdNumber())) {
			throw new ResourceNotFoundException(Constant.ID_NUMBER_NOT_FOUND);
		}
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
		Account existAccount = accountRepository.findByAccountNumber(account.getAccountNumber());
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
		Account account = accountRepository.findByAccountNumber(id);
		accountRepository.delete(account);
		return "Account deleted";
	}
	
	
	
	
	/**
	 * updateAccount
	 * 
	 * @param account
	 * @return updatedAccount
	 */
	public Account depositAccount(Account account) {
		log.debug("AccountService updateAccount method calling.");
		Account existAccount = accountRepository.findByAccountNumber(account.getAccountNumber());
		account.setCreatedDate(existAccount.getCreatedDate());
		account.setUpdatedDate(new Date());
		Account updatedAccount = accountRepository.save(account);
		return updatedAccount;

	}
	
	
	
	
	
	
	
	
}