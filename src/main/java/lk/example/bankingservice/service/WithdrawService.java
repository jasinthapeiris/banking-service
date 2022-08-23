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
import javax.money.Monetary;
import javax.money.MonetaryAmount;
import javax.money.NumberValue;
import javax.money.convert.CurrencyConversion;
import javax.money.convert.MonetaryConversions;
import org.springframework.beans.factory.annotation.Autowired;

import lk.example.bankingservice.exception.ResourceNotFoundException;
import lk.example.bankingservice.model.Account;
import lk.example.bankingservice.model.CardDetail;
import lk.example.bankingservice.model.Currency;
import lk.example.bankingservice.repository.AccountRepository;
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
public class WithdrawService {

	private final AccountRepository accountRepository;
	private final CardDetailRepository cardDetailRepository;

	/**
	 * withdraw
	 * 
	 * @param currency
	 * @return accountBalance
	 */
	public Account withdraw(Currency currency) {
		log.debug("WithdrawService withdraw method calling.");
		double amount = convertedAmountUSD(currency);
		CardDetail cardDetail = cardDetailRepository.findByCardNumber(currency.getNumericCode());
		Account account = cardDetail.getAccount();
		double existAmount = Double.parseDouble(account.getAmount());
		if(existAmount>amount) {
			double total = existAmount - amount;
			account.setAmount(String.valueOf(total));
			account = accountRepository.save(account);
		}else {
			throw new ResourceNotFoundException(Constant.BALANCE_NOT_ENOUGH);
		}
		return account;
	}

	/**
	 * convertedAmountUSD
	 * 
	 * @param currency
	 * @return double value
	 */
	public double convertedAmountUSD(Currency currency) {
		MonetaryAmount oneDollar = Monetary.getDefaultAmountFactory().setCurrency("USD").setNumber(currency.getAmount())
				.create();
		CurrencyConversion conversionEUR = MonetaryConversions.getConversion(currency.getCurrencyCode());
		MonetaryAmount convertedAmountUSDtoOther = oneDollar.with(conversionEUR);
		NumberValue value = convertedAmountUSDtoOther.getNumber();
		return Double.parseDouble(value.toString());
	}
}