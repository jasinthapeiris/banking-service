
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
 * Date :2022-08-20 This class process the Account model class
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