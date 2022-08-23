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
import lk.example.bankingservice.model.CardDetail;
import lk.example.bankingservice.service.CardDetailService;
import lk.example.bankingservice.util.Constant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Jasintha Peiris
 */
@Slf4j
@RestController
@RequestMapping("/cardDetail")
@RequiredArgsConstructor(onConstructor = @__({ @Autowired }))
public class CardDetailEndPoint {

	private final CardDetailService CardDetailService;

	/**
	 * saveCardDetail
	 * 
	 * @param CardDetail
	 * @return responseEntity
	 * @throws Exception
	 */
	@PostMapping("/save")
	public ResponseEntity<CardDetail> saveCardDetail(@RequestBody CardDetail CardDetail) throws Exception {
		log.info("CardDetailEndPoint saveCardDetail method calling.");
		try {
			CardDetail savedCardDetail = CardDetailService.saveCardDetail(CardDetail);
			ResponseEntity<CardDetail> responseEntity = new ResponseEntity<>(savedCardDetail, HttpStatus.CREATED);
			return responseEntity;
		} catch (DataIntegrityViolationException e) {
			log.error("error occurred by saveCardDetail in CardDetailEndPoint", e);
			throw new DataIntegrityViolationException(Constant.INVALIDE_INPUTS);
		} catch (Exception e) {
			log.error("error occurred by saveCardDetail in CardDetailEndPoint", e);
			throw new Exception(e);
		}

	}

	/**
	 * activeInactive
	 * 
	 * @param id
	 * @return responseEntity
	 * @throws Exception
	 */
	@DeleteMapping("/activeInactive/{id}")
	public ResponseEntity<String> activeInactive(@PathVariable int id) throws Exception {
		log.info("CardDetailEndPoint deleteCardDetail method calling.");
		try {
			String message = CardDetailService.activeInactive(id);
			ResponseEntity<String> responseEntity = new ResponseEntity<>(message, HttpStatus.OK);
			return responseEntity;
		} catch (Exception e) {
			log.error("error occurred by activeInactiveCardDetail in CardDetailEndPoint", e);
			throw new Exception(e);
		}
	}
}