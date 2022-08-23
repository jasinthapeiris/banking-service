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
package lk.example.bankingservice.util;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import javax.money.Monetary;
import javax.money.MonetaryAmount;
import javax.money.NumberValue;
import javax.money.convert.CurrencyConversion;
import javax.money.convert.MonetaryConversions;
import org.springframework.beans.factory.annotation.Autowired;

import lk.example.bankingservice.model.Currency;

/**
 * Date :2022-08-22. This class process the crud CardDetailService class
 *
 * @author Jasintha Peiris
 */
@Slf4j
@org.springframework.stereotype.Component
@RequiredArgsConstructor(onConstructor = @__({ @Autowired }))
public class CurrencyConverted {
	/**
	 * convertedAmountUSD
	 * 
	 * @param currency
	 * @return double value
	 */
	/**
	 * convertedAmountUSD
	 * 
	 * @param currency
	 * @return double value
	 */
	public double convertedAmountUSD(Currency currency) {
		log.debug("CurrencyConverted convertedAmountUSD method calling.");
		MonetaryAmount otherCurrency = Monetary.getDefaultAmountFactory().setCurrency(currency.getCurrencyCode())
				.setNumber(currency.getAmount()).create();
		CurrencyConversion conversionUSD = MonetaryConversions.getConversion("USD");
		MonetaryAmount convertedAmountOtherToUSD = otherCurrency.with(conversionUSD);
		NumberValue value = convertedAmountOtherToUSD.getNumber();
		return Double.parseDouble(value.toString());
	}
}