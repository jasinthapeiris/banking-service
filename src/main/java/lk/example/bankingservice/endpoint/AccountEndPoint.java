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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lk.dmg.rmsstandard.model.Account;
import lk.example.bankingservice.service.AccountService;
import lk.example.bankingservice.util.Constant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
/**
 * @author Jasintha Peiris
 * @version 0.0.1 2022/08/08 This class process the Account operation endpoint
 * class
 */
@Slf4j 
@RestController
@RequestMapping("/account")
@RequiredArgsConstructor(onConstructor = @__({ @Autowired }))
public class AccountEndPoint {

	private final AccountService standardService;

	/**
	 * getAllUnits
	 * 
	 * @return responseEntity
	 */
	@GetMapping("/Account")
	@ResponseBody
	public ResponseEntity<List<Account>> getAllUnits(@ModelAttribute Account Account) {
		if (log.isDebugEnabled()) {
			log.info("UnitController getAllunits method calling.");
		}
		List<Account> allUnits = standardService.findAllUnits(Account);
		if (allUnits.isEmpty()) {
			throw new ResourceNotFoundException(Constant.DATA_NOT_FOUND);
		}
		ResponseEntity<List<Account>> responseEntity = new ResponseEntity<>(allUnits, HttpStatus.OK);
		return responseEntity;
	}

	/**
	 * getAllActiveUnits
	 * 
	 * @return responseEntity
	 */
	@GetMapping("/units/active")
	@ResponseBody
	public ResponseEntity<List<Account>> getAllActiveunits(@ModelAttribute Account Account) {
		if (log.isDebugEnabled()) {
			log.info("UnitController getAllActiveUnits method calling.");
		}
		List<Account> allActiveUnits = unitService.findAllActiveUnits(Account);
		if (allActiveUnits.isEmpty()) {
			throw new ResourceNotFoundException(Constants.ACTIVE_DATA_NOT_FOUND);
		}
		ResponseEntity<List<Account>> responseEntity = new ResponseEntity<>(allActiveUnits, HttpStatus.OK);
		return responseEntity;

	}

	/**
	 * getUnits using Account id
	 * 
	 * @param id
	 * @return responseEntity
	 */
	@GetMapping("/Account/{id}")
	public ResponseEntity<Account> getUnits(@PathVariable int id) {
		if (log.isDebugEnabled()) {
			log.info("UnitController getUnits method calling.");
		}
		Account getUnit = unitService.findUnit(id);
		if (getUnit == null) {
			throw new ResourceNotFoundException(Constants.DATA_NOT_FOUND_FOR_ID + id);
		}
		ResponseEntity<Account> responseEntity = new ResponseEntity<>(getUnit, HttpStatus.OK);
		return responseEntity;

	}

	/**
	 * saveUnit
	 * 
	 * @param Account
	 * @return responseEntity
	 * @throws Exception
	 */
	@PostMapping("/Account")
	public ResponseEntity<Account> saveUnit(@Valid @RequestBody Account Account, Errors errors) throws Exception {
		if (log.isDebugEnabled()) {
			log.info("UnitController saveUnit method calling.");
		}

		try {
			Account savedUnit = unitService.saveUnit(Account);
			ResponseEntity<Account> responseEntity = new ResponseEntity<>(savedUnit, HttpStatus.CREATED);
			return responseEntity;
		} catch (DataIntegrityViolationException e) {
			log.error("error occurred by saveUnit in UnitController", e);
			throw new DataIntegrityViolationException(Constants.INVALIDE_INPUTS);
		} catch (Exception e) {
			log.error("error occurred by saveUnit in UnitController", e);
			throw new Exception(e);
		}

	}

	/**
	 * updateUnit
	 * 
	 * @param Account
	 * @return responseEntity
	 * @throws Exception
	 */
	@PutMapping("/Account")
	public ResponseEntity<Account> updateUnit(@RequestBody Account Account) throws Exception {
		if (log.isDebugEnabled()) {
			log.info("UnitController updateUnit method calling.");
		}

		Account getUnit = unitService.findUnit(Account.getUnitId());
		if (getUnit == null) {
			throw new ResourceNotFoundException(Constants.DATA_NOT_FOUND_FOR_ID + Account.getUnitId());
		}
		try {
			Account updatedUnit = unitService.updateUnit(Account);
			ResponseEntity<Account> responseEntity = new ResponseEntity<>(updatedUnit, HttpStatus.CREATED);
			return responseEntity;
		} catch (DataIntegrityViolationException e) {
			log.error("error occurred by updateUnit in UnitController", e);
			throw new DataIntegrityViolationException(Constants.INVALIDE_INPUTS);
		} catch (Exception e) {
			log.error("error occurred by updateUnit in UnitController", e);
			throw new Exception(e);
		}
	}

	/**
	 * deleteUnit
	 * 
	 * @param id
	 * @return responseEntity
	 * @throws Exception
	 */
	@DeleteMapping("/Account/{id}")
	public ResponseEntity<Account> deleteUnit(@PathVariable int id) throws Exception {
		if (log.isDebugEnabled()) {
			log.info("UnitController deleteUnit method calling.");
		}

		Account getUnit = unitService.findUnit(id);
		if (getUnit == null) {
			throw new ResourceNotFoundException(Constants.DATA_NOT_FOUND_FOR_ID + id);
		}
		try {
			Account deletedUnit = unitService.deleteUnit(id);
			ResponseEntity<Account> responseEntity = new ResponseEntity<>(deletedUnit, HttpStatus.OK);
			return responseEntity;
		} catch (Exception e) {
			log.error("error occurred by deleteUnit in UnitController", e);
			throw new Exception(e);
		}
	}
}