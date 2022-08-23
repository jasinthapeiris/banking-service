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
package lk.example.bankingservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import lk.example.bankingservice.model.CardDetail;

/**
 * Date :2022-08-22. This the interface CardDetailRepository
 *
 * @author Jasintha Peiris
 */
@Repository
public interface CardDetailRepository extends CrudRepository<CardDetail, Integer> {
	CardDetail findByCardNumber(int cardNumber);
}
