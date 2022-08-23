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

package lk.example.bankingservice.endpoint;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lk.example.bankingservice.model.Account;
import lk.example.bankingservice.model.Currency;
import lk.example.bankingservice.service.DepositService;
import lk.example.bankingservice.util.Constant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Jasintha Peiris
 */
@Slf4j
@RestController
@RequestMapping("/deposit")
@RequiredArgsConstructor(onConstructor = @__({ @Autowired }))
public class DepositEndPoint {

	private final DepositService depositService;

	/**
	 * updateAccount
	 * 
	 * @param account
	 * @return responseEntity
	 * @throws Exception
	 */
	@PutMapping("/add")
	public ResponseEntity<Account> depositAccount(@ModelAttribute Currency currency) throws Exception {
		log.info("AccountEndPoint updateAccount method calling.");
		try {
			Account deposit = depositService.deposit(currency);
			ResponseEntity<Account> responseEntity = new ResponseEntity<>(deposit, HttpStatus.CREATED);
			return responseEntity;
		} catch (DataIntegrityViolationException e) {
			log.error("error occurred by deposit in DepositEndPoint", e);
			throw new DataIntegrityViolationException(Constant.INVALIDE_INPUTS);
		} catch (Exception e) {
			log.error("error occurred by deposit in DepositEndPoint", e);
			throw new Exception(e);
		}
	}	
}