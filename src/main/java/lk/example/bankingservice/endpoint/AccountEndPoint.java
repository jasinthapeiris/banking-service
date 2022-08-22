/*
 *  @author Jasintha Peiris
 *  @version 0.0.1 2022/07/04
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

package lk.example.bankingservice.endpoint;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lk.example.bankingservice.exception.ResourceNotFoundException;
import lk.example.bankingservice.model.Account;
import lk.example.bankingservice.service.AccountService;
import lk.example.bankingservice.util.Constant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
/**
 * @author Jasintha Peiris
 */
@Slf4j 
@RestController
@RequestMapping("/account")
@RequiredArgsConstructor(onConstructor = @__({ @Autowired }))
public class AccountEndPoint {

	private final AccountService accountService;

	/**
	 * getAll
	 * @return responseEntity
	 */
	@GetMapping("/all")
	@ResponseBody
	public ResponseEntity<List<Account>> getAll() {
		log.info("AccountEndPoint getAllunits method calling.");
		List<Account> allAccounts = accountService.findAllAccounts();
		if (allAccounts.isEmpty()) {
			throw new ResourceNotFoundException(Constant.DATA_NOT_FOUND);
		}
		ResponseEntity<List<Account>> responseEntity = new ResponseEntity<>(allAccounts, HttpStatus.OK);
		return responseEntity;
	}

	/**
	 * getById 
	 * @param id
	 * @return responseEntity
	 */
	@GetMapping("/account/{id}")
	public ResponseEntity<Account> getById(@PathVariable int id) {
		log.info("AccountEndPoint getUnits method calling.");
		Account account = accountService.findByAccountId(id);
		if (account == null) {
			throw new ResourceNotFoundException(Constant.DATA_NOT_FOUND_FOR_ID + id);
		}
		ResponseEntity<Account> responseEntity = new ResponseEntity<>(account, HttpStatus.OK);
		return responseEntity;

	}

	/**
	 * saveAccount
	 * @param account
	 * @return responseEntity
	 * @throws Exception
	 */
	@PostMapping("/save")
	public ResponseEntity<Account> saveAccount(@RequestBody Account account) throws Exception {
			log.info("AccountEndPoint saveAccount method calling.");
		try {
			Account savedAccount = accountService.saveAccount(account);
			ResponseEntity<Account> responseEntity = new ResponseEntity<>(savedAccount, HttpStatus.CREATED);
			return responseEntity;
		} catch (DataIntegrityViolationException e) {
			log.error("error occurred by saveAccount in AccountEndPoint", e);
			throw new DataIntegrityViolationException(Constant.INVALIDE_INPUTS);
		} catch (Exception e) {
			log.error("error occurred by saveAccount in AccountEndPoint", e);
			throw new Exception(e);
		}

	}

	/**
	 * updateAccount
	 * @param account
	 * @return responseEntity
	 * @throws Exception
	 */
	@PutMapping("/update")
	public ResponseEntity<Account> updateAccount(@RequestBody Account account) throws Exception {
			log.info("AccountEndPoint updateAccount method calling.");
		try {
			Account updatedAccount = accountService.updateAccount(account);
			ResponseEntity<Account> responseEntity = new ResponseEntity<>(updatedAccount, HttpStatus.CREATED);
			return responseEntity;
		} catch (DataIntegrityViolationException e) {
			log.error("error occurred by updateAccount in AccountEndPoint", e);
			throw new DataIntegrityViolationException(Constant.INVALIDE_INPUTS);
		} catch (Exception e) {
			log.error("error occurred by updateAccount in AccountEndPoint", e);
			throw new Exception(e);
		}

	}

	/**
	 * deleteAccount
	 * @param id
	 * @return responseEntity
	 * @throws Exception
	 */
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteAccount(@PathVariable int id) throws Exception {
		log.info("AccountEndPoint deleteAccount method calling.");
		Account account = accountService.findByAccountId(id);
		if (account == null) {
			throw new ResourceNotFoundException(Constant.DATA_NOT_FOUND_FOR_ID + id);
		}
		try {
			String message = accountService.deleteAccount(id);
			ResponseEntity<String> responseEntity = new ResponseEntity<>(message, HttpStatus.OK);
			return responseEntity;
		} catch (Exception e) {
			log.error("error occurred by deleteAccount in AccountEndPoint", e);
			throw new Exception(e);
		}
	}
}