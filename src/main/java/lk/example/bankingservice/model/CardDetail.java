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
package lk.example.bankingservice.model;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
/**
 * Date :2022-08-22 This class process the Account model class
 * 
 * @author Jasintha Peiris
 */
@Setter
@Getter
@Entity
@Table(name = "card_detail")
public class CardDetail{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "card_number",length=16)
	private Integer cardNumber;	
	
	@Column(name = "cardType")
	public String cardType;
	
	@Column(name = "cardName")
	public String cardName;

	@Column(name = "embed_name")
	private String embedName;
	
	@Column(name = "valid_thru_date")
	private String validThruDate;
	
	@Column(name = "card_verification_value_2")
	public String cardVerificationValue2;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "account_number")
	private Account account;
	
	@Column(name = "is_active")
	private Boolean isActive;

}