/*
 *     Copyright (c) 1995-2022,  The Data Management Group Ltd   All Rights Reserved.
 *     *  PROPRIETARY AND COPYRIGHT NOTICE.
 *
 *        This software product contains information which is proprietary to
 *        and considered a trade secret The Data management Group Ltd .
 *        It is expressly agreed that it shall not be reproduced in whole or part,
 *        disclosed, divulged or otherwise made available to any third party directly
 *        or indirectly.  Reproduction of this product for any purpose is prohibited
 *        without written authorisation from the The Data Management Group Ltd
 *        All Rights Reserved.
 *
 *        E-Mail andyj@datam.co.uk
 *        URL : www.datam.co.uk
 *        Created By :Jasintha Peiris
 */
package lk.example.bankingservice.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import lk.dmg.rmsstandard.model.Account;


/**
 * Date :2022-04-06. This class process the crud operation Service class
 *
 * @author Jasintha Peiris
 */
@Repository
public interface AccountRepository extends CrudRepository<Account, Integer> {

	Account findByStandardId(Integer standardId);

	List<Account> findByCentreIdOrderByOrderNumberAsc(String centreId);

	List<Account> findAllByOrderByOrderNumberAsc();
	
	List<Account> findByCentreIdAndParentStandardId(String centreId,Integer parentStandardId);

}
