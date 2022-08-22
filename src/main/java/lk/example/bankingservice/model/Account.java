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
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;

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
@Table(name = "account")
public class Account{
	@Id
	@Column(name = "account_id")
	private Integer accountId;	
	
	@Column(name = "account_name")
	public String accountName;
	
	@Column(name = "accountType")
	public String accountType;
	
	@Column(name = "id_number")
	private Integer orderNumber;

	@Column(name = "description")
	public String description;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date")
	private Date createdDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_date")
	private Date updatedDate;

	@Column(name = "is_active")
	private Boolean isActive;

	@Column(name = "is_delete")
	private Boolean isDelete;
}