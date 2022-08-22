
package lk.example.bankingservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import lk.example.bankingservice.model.Account;

/**
 * Date :2022-08-20. This the interface AccountRepository
 *
 * @author Jasintha Peiris
 */
@Repository
public interface AccountRepository extends CrudRepository<Account, Integer> {

	Account findByAccountId(int id);


}
