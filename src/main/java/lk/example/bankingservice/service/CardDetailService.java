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

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import lk.example.bankingservice.exception.ResourceNotFoundException;
import lk.example.bankingservice.model.CardDetail;
import lk.example.bankingservice.repository.CardDetailRepository;
import lk.example.bankingservice.util.Constant;

/**
 * Date :2022-08-222. This class process the crud CardDetailService class
 *
 * @author Jasintha Peiris
 */
@Slf4j
@org.springframework.stereotype.Component
@RequiredArgsConstructor(onConstructor = @__({ @Autowired }))
public class CardDetailService {

	private final CardDetailRepository cardDetailRepository;

	
	/**
	 * saveCardDetail
	 * 
	 * @param CardDetail
	 * @return savedCardDetail
	 */
	public CardDetail saveCardDetail(CardDetail cardDetail) {
		log.debug("CardDetailService saveCardDetail method calling.");
		if (StringUtils.isEmpty(cardDetail.getEmbedName())) {
			throw new ResourceNotFoundException(Constant.EMBED_NAME_NOT_FOUND);
		}
		String cvv=generatorCvv(cardDetail.getAccount().getAccountNumber());
		cardDetail.setCardVerificationValue2(cvv);
		cardDetail.setValidThruDate(generatorValidThruDate());
		CardDetail savedCardDetail = cardDetailRepository.save(cardDetail);
		return savedCardDetail;
	}

	/**
	 * activeInactive
	 * 
	 * @param id
	 * @return status
	 */
	public String activeInactive(int id) {
		log.debug("CardDetailService deleteCardDetail method calling.");
		CardDetail CardDetail = cardDetailRepository.findByCardNumber(id);
		String status="";
		if(CardDetail.getIsActive()) {
			CardDetail.setIsActive(false);
			status="Card Active";
		}else {
			CardDetail.setIsActive(true);
			status="Card Inactive";
		}
		cardDetailRepository.save(CardDetail);
		return status;
	}
	
	/**
	 * generatorCvv
	 * 
	 * @param integer
	 * @return String
	 */
	public String generatorCvv(Integer integer) {
	    // It will generate 6 digit random Number.
	    // from 0 to 999999
	    Random rnd = new Random();
	    int number = rnd.nextInt(integer);

	    // this will convert any number sequence into 6 character.
	    return String.format("%06d", number);
	}
	
	/**
	 * generatorValidThruDate
	 * 
	 * @return String
	 */
	public String generatorValidThruDate() {
		LocalDate today = LocalDate.now();
	    int month = today.getMonthValue();
		int year = today.getYear()+5;	
		String vtd=String.valueOf(month)+"/"+String.valueOf(year);
	    return vtd;
	}
}